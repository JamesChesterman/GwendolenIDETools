package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import GwenDebugger.JavaBreakpointListener;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.xdebugger.*;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();

    private JCheckBox steppingCheckBox;
    private JButton startToolsButton;
    private JButton nextCycleButton;
    private JBTabbedPane tabbedPane;
    private JSlider slider;
    private JLabel sliderLabel;
    private JTextField sliderText;
    private JButton changeCycleNumber;
    private JLabel warningLabel;

    private final BreakpointController breakpointController;
    private boolean isStepping;

    private final Project project;
    private XDebugSession debugSession;
    private BGIViewer bgiViewer;

    //Word 'cycles' is inaccurate. In GUI have renamed anything 'cycle' related to 'step'
    private int cyclesDone;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        cyclesDone = 0;
        breakpointController = new BreakpointController(project);
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);

        isStartToolsEnabled();

    }

    public JSlider getSlider(){
        return slider;
    }


    //Call the updateWindow method of each of the windows.
    public void updateDebugTreeValues(XDebuggerTree newDebugTree){
        bgiViewer.updateWindow(newDebugTree);
    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        makeSteppingCheckbox();
        makeStartToolsButton();
        makeNextCycleButton();
        makeTabbedPane();


        controlsPanel.add(steppingCheckBox);
        controlsPanel.add(startToolsButton);
        controlsPanel.add(nextCycleButton);
        makeSlider(controlsPanel);
        controlsPanel.add(tabbedPane);
        setComponentsEnabled(false);
        return controlsPanel;
    }


    //Checks every period of time whether the startTools button should be enabled or not
    private void isStartToolsEnabled(){
        //Want stepping mode to be enabled AND debug session to be started AND cycle number = 0
        debugSession = XDebuggerManager.getInstance(project).getCurrentSession();
        if(isStepping && debugSession != null){
            startToolsButton.setEnabled(true);
        }else{
            setComponentsEnabled(false);
        }
        //Wait period of time then check again
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //This is called after the wait
            isStartToolsEnabled();
        }, 200, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    //Get debug session and start breakpoint listener (to see when execution is paused)
    private void startTools() throws ExecutionException {
        debugSession = XDebuggerManager.getInstance(project).getCurrentSession();
        if(debugSession != null){
            JavaBreakpointListener breakpointListener = new JavaBreakpointListener(debugSession, this);
            debugSession.addSessionListener(breakpointListener);
            //This will allow BGIViewer to have the first cycle recorded.
            breakpointListener.updateDebugInfo();
            setComponentsEnabled(true);
            //Need to wait for first lot of data to come back to enable the next cycle button
            nextCycleButton.setEnabled(false);
            cyclesDone = 0;
            slider.setValue(0);
        }
    }

    private void makeSteppingCheckbox(){
        //Stepping checkbox
        steppingCheckBox = new JCheckBox("Stepping Mode Enabled");
        isStepping = breakpointController.checkBreakpoint();
        steppingCheckBox.setSelected(isStepping);
        steppingCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                isStepping = !isStepping;
                breakpointController.toggleBreakpoint();
            }
        });
    }

    private void makeStartToolsButton(){
        //Start tools button
        startToolsButton = new JButton("Start Tools");
        startToolsButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    startTools();
                } catch (ExecutionException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    private void makeNextCycleButton(){
        //Next cycle button
        nextCycleButton = new JButton("Next Step");
        nextCycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Also want to disable the next cycle button, need to wait for values to be loaded before pressing it again
                nextCycleButton.setEnabled(false);
                breakpointController.goToNextCycle(debugSession);
            }
        });
    }

    private void makeSlider(JPanel controlsPanel){
        slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setValue(1);

        sliderLabel = new JLabel("Step Number: ");
        sliderLabel.setEnabled(false);
        sliderText = new JTextField(5);
        sliderText.setText("1");
        warningLabel = new JLabel("");

        slider.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e){
                int cycleNum = slider.getValue();
                sliderText.setText(String.valueOf(cycleNum));
            }
        });

        sliderText.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent e){
               try{
                   int cycleNum = Integer.parseInt(sliderText.getText());
                   if(cycleNum >= slider.getMinimum() && cycleNum <= slider.getMaximum()){
                       slider.setValue(cycleNum);
                       warningLabel.setText("");
                   }else{
                       warningLabel.setText("WARNING - please enter step number between: " + slider.getMinimum() + " and "
                               + slider.getMaximum());
                   }
               }catch(NumberFormatException ex){
                    warningLabel.setText("WARNING - please enter step number as an integer");
               }
           }

        });

        changeCycleNumber = new JButton("Change Step Number");
        changeCycleNumber.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                bgiViewer.updateCycleNumber(slider.getValue());
            }
        });

        controlsPanel.add(sliderLabel);
        controlsPanel.add(sliderText);
        controlsPanel.add(slider);
        controlsPanel.add(changeCycleNumber);
        controlsPanel.add(warningLabel);
    }

    private void makeTabbedPane(){
        //Pane containing the information windows
        tabbedPane = new JBTabbedPane();
        bgiViewer = new BGIViewer(this);
        JBScrollPane scrollPane = new JBScrollPane(bgiViewer);
        scrollPane.setPreferredSize(new Dimension(200, 400));
        tabbedPane.addTab("BGIViewer", scrollPane);
    }


    //Want every component apart from stepping mode checkbox to be disabled at the start
    private void setComponentsEnabled(boolean enabled){
        //This manages when each component should become enabled
        //Everything starts off as disabled except for stepping mode checkbox
        JComponent[] arrayOfComponents = new JComponent[]{startToolsButton, nextCycleButton, tabbedPane, slider,
                sliderLabel, sliderText, changeCycleNumber, warningLabel, bgiViewer};

        for(JComponent component : arrayOfComponents){
            component.setEnabled(enabled);
        }
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }

    //Called from BGIViewer when values for cycle are loaded. Increments cyclesDone
    public void cycleComplete(){
        cyclesDone += 1;
        //Need to update the Swing component on an Event-Dispatch Thread
        //Otherwise the program crashes
        SwingUtilities.invokeLater(new Runnable() {
           public void run(){
               slider.setMaximum(cyclesDone);
               slider.setValue(cyclesDone);
               sliderText.setText(String.valueOf(cyclesDone));
               //Also means that all values are loaded, so can re-enable 'Next Cycle' button
               nextCycleButton.setEnabled(true);
           }
        });

    }
}
