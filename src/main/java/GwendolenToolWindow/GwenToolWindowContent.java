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
    private JButton continueButton;
    private JButton stopButton;
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
    private BreakpointsViewer breakpointsViewer;

    //Word 'cycles' is inaccurate. In GUI have renamed anything 'cycle' related to 'step'
    private int cyclesDone;

    private boolean continueMode;
    private JavaBreakpointListener breakpointListener;
    private int stepToSkipTo;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        cyclesDone = 0;
        stepToSkipTo = 0;
        continueMode = false;
        breakpointController = new BreakpointController(project);
        contentPanel.setLayout(new BorderLayout(0, 0));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);

        isStartToolsEnabled();

    }

    public JSlider getSlider(){
        return slider;
    }


    //Call the updateWindow method of each of the windows.
    public void updateDebugTreeValues(XDebuggerTree newDebugTree, boolean skipped){
        if(skipped){
            bgiViewer.skipStep();
        }else{
            bgiViewer.updateWindow(newDebugTree);
        }
    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new GridBagLayout());

        makeSteppingCheckbox();
        makeStartToolsButton();
        makeNextCycleButton();
        makeContinueButton();
        makeStopButton();
        makeTabbedPane();

        addComponent(controlsPanel, steppingCheckBox, 0, 0, 1, 1);
        addComponent(controlsPanel, startToolsButton, 1, 0, 1, 1);
        addComponent(controlsPanel, nextCycleButton, 0, 1, 1, 1);
        addComponent(controlsPanel, continueButton, 0, 2, 1, 1);
        addComponent(controlsPanel, stopButton, 1, 2, 1, 1);

        makeSlider(controlsPanel);

        addComponent(controlsPanel, tabbedPane, 0, 7, 2, 4);
        setComponentsEnabled(false);
        return controlsPanel;
    }


    //Add component to grid bag layout.
    private void addComponent(Container container, Component component, int column, int row, int width, int height){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,5,5,5);
        container.add(component, constraints);
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
            breakpointListener = new JavaBreakpointListener(debugSession, this);
            debugSession.addSessionListener(breakpointListener);
            //This will allow BGIViewer to have the first cycle recorded.
            breakpointListener.updateDebugInfo();
            setComponentsEnabled(true);
            //Need to wait for first lot of data to come back to enable the next cycle button
            nextCycleButton.setEnabled(false);
            continueButton.setEnabled(false);
            stopButton.setEnabled(false);
            cyclesDone = 0;
            slider.setValue(0);
            //Set all variables back to default in bgiViewer so you can run the program again.
            bgiViewer.initVars();
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
                continueButton.setEnabled(false);
                breakpointController.goToNextCycle(debugSession);
            }
        });
    }

    private void makeContinueButton(){
        continueButton = new JButton("Continue");
        //Advances execution to next custom breakpoint set in the breakpoints viewer
        continueButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                //Sets continueMode to true and performs first 'next step' operation
                //When continueMode is true, whenever a step is completed
                //Breakpoints are iterated through to see if they are true
                //If ALL breakpoints are false, then initiate next step
                continueMode = true;
                nextCycleButton.setEnabled(false);
                continueButton.setEnabled(false);
                stopButton.setEnabled(true);
                breakpointController.goToNextCycle(debugSession);
            }
        });
    }

    private void makeStopButton(){
        stopButton = new JButton("Stop");
        //Sets continueMode to false. So only used after you've pressed continue
        stopButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                continueMode = false;
                breakpointListener.setSkipMode(false);
                stopButton.setEnabled(false);
            }
        });
    }

    private void makeSlider(JPanel controlsPanel){
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);

        slider = new JSlider(JSlider.HORIZONTAL, 1, 50, 1);
        slider.setMinorTickSpacing(1);
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

        addComponent(controlsPanel, separator, 0, 3, 2, 1);

        addComponent(controlsPanel, sliderLabel, 0, 4, 1, 1);
        addComponent(controlsPanel, sliderText, 1, 4, 1, 1);
        addComponent(controlsPanel, slider, 0, 5, 2, 1);
        addComponent(controlsPanel, changeCycleNumber, 0, 6, 1, 1);
        addComponent(controlsPanel, warningLabel, 1, 6, 1, 1);
    }

    private void makeTabbedPane(){
        //Pane containing the information windows
        tabbedPane = new JBTabbedPane();

        breakpointsViewer = new BreakpointsViewer(this);
        bgiViewer = new BGIViewer(this, breakpointsViewer);
        SkipControls skipControls = new SkipControls(this);
        JBScrollPane scrollPane = new JBScrollPane(bgiViewer);
        scrollPane.setPreferredSize(new Dimension(200, 300));

        JBScrollPane scrollPaneBreakpoints = new JBScrollPane(breakpointsViewer);
        scrollPane.setPreferredSize(new Dimension(200,300));
        tabbedPane.addTab("BGIViewer", scrollPane);
        tabbedPane.addTab("Breakpoints", scrollPaneBreakpoints);
        tabbedPane.addTab("Skip Controls", skipControls);
    }


    //Want every component apart from stepping mode checkbox to be disabled at the start
    private void setComponentsEnabled(boolean enabled){
        //This manages when each component should become enabled
        //Everything starts off as disabled except for stepping mode checkbox
        JComponent[] arrayOfComponents = new JComponent[]{startToolsButton, nextCycleButton, continueButton,
                stopButton, tabbedPane, slider, sliderLabel, sliderText, changeCycleNumber, warningLabel, bgiViewer,
                breakpointsViewer};

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
            public void run() {
                slider.setMaximum(cyclesDone);
                slider.setMinorTickSpacing(cyclesDone / 10);
                slider.setMajorTickSpacing(cyclesDone / 10);
                if(cyclesDone > 10){
                    slider.setLabelTable(slider.createStandardLabels(cyclesDone / 10));
                }
                slider.setValue(cyclesDone);
                sliderText.setText(String.valueOf(cyclesDone));
                //Also means that all values are loaded, so can re-enable 'Next Cycle' button
                nextCycleButton.setEnabled(true);
                continueButton.setEnabled(true);
                stopButton.setEnabled(false);
                checkStepNumForSkip();
                checkBreakpoints();
            }
        });



    }

    //Will check if the cyclesDone is equal to stepToSkipTo - 1
    //Will then initiate going to next step
    //So there will be values in BGIViewer for the step you want to skip to
    //For skipping
    private void checkStepNumForSkip(){
        if(stepToSkipTo != 0){
            if(cyclesDone == stepToSkipTo - 1){
                continueMode = false;
                breakpointListener.setSkipMode(false);

                //Initiate next step
                nextCycleButton.setEnabled(false);
                continueButton.setEnabled(false);
                stopButton.setEnabled(true);
                breakpointController.goToNextCycle(debugSession);
            }
        }
    }

    //If there are any breakpoints that are true, need to stop execution
    //If all breakpoints are false, will initiate next step
    private void checkBreakpoints(){
        if(continueMode){
            List<String[]> breakpointsList = breakpointsViewer.getBreakpointsList();
            for(int i=0; i<breakpointsList.size(); i++){
                String[] breakpointArray = breakpointsList.get(i);
                //Index 1 in every array indicates the attribute we're looking for (e.g. numOfSteps)
                if(breakpointArray[1].equals("numOfSteps")){
                    if(checkNumOfStepsBreakpoint(breakpointArray[0], breakpointArray[2])){
                        continueMode = false;
                        breakpointListener.setSkipMode(false);
                    }
                }
            }

            if(continueMode){
                //If continueMode hasn't been set false after having checked through all breakpoints,
                //Initiate next step
                nextCycleButton.setEnabled(false);
                continueButton.setEnabled(false);
                stopButton.setEnabled(true);
                breakpointController.goToNextCycle(debugSession);
            }
        }
    }

    //If the current agent is equal to the one specified in the breakpoint
    //AND the current numOfSteps is equal to the one specified in the breakpoint
    //Then return true
    private boolean checkNumOfStepsBreakpoint(String breakpointAgent, String breakpointNumOfSteps){
        if(bgiViewer.getCurrentAgent().equals(breakpointAgent)){
            if(bgiViewer.getCurrentNumOfSteps().equals(breakpointNumOfSteps)){
                return true;
            }
        }
        return false;
    }

    //Called from SkipControls. Will skip to cyclesDone (steps already completed) +
    //Number specified in SkipControls - 1
    //Will then go to next step again, therefore getting the information for cyclesDone + numOfStepsToSkip
    //So there will be information in BGIViewer at the end of the skip.
    public void skip(int numOfSteps){
        continueMode = true;
        breakpointListener.setSkipMode(true);
        nextCycleButton.setEnabled(false);
        continueButton.setEnabled(false);
        stopButton.setEnabled(true);

        //Will stop at this number of steps
        stepToSkipTo = cyclesDone + numOfSteps;

        breakpointController.goToNextCycle(debugSession);
    }
}
