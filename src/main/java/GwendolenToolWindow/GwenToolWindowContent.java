package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import GwenDebugger.JavaBreakpointListener;
import com.intellij.execution.ExecutionException;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.components.JBTabbedPane;
import com.intellij.xdebugger.*;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();
    private JSlider slider;
    private final BreakpointController breakpointController;
    private boolean isStepping;

    private final Project project;
    private XDebugSession debugSession;
    private BGIViewer bgiViewer;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        breakpointController = new BreakpointController(project);
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);
    }

    //Get debug session and start breakpoint listener (to see when execution is paused)
    private void startTools() throws ExecutionException {
        debugSession = XDebuggerManager.getInstance(project).getCurrentSession();
        if(debugSession != null){
            JavaBreakpointListener breakpointListener = new JavaBreakpointListener(debugSession, this);
            debugSession.addSessionListener(breakpointListener);
        }
    }

    //Call the updateWindow method of each of the windows.
    public void updateDebugTreeValues(XDebuggerTree newDebugTree){
        bgiViewer.updateWindow(newDebugTree);

    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));

        //Stepping checkbox
        JCheckBox steppingCheckBox = new JCheckBox("Stepping Mode Enabled");
        isStepping = breakpointController.checkBreakpoint();
        steppingCheckBox.setSelected(isStepping);
        steppingCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                isStepping = !isStepping;
                breakpointController.toggleBreakpoint();
            }
        });

        //Start tools button
        JButton startToolsButton = new JButton("Start Tools");
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

        //Next cycle button
        JButton nextCycleButton = new JButton("Next Cycle");
        nextCycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                breakpointController.goToNextCycle(debugSession);
            }
        });

        //Pane containing the information windows
        JBTabbedPane tabbedPane = new JBTabbedPane();
        bgiViewer = new BGIViewer();
        tabbedPane.addTab("BGIViewer", bgiViewer);

        controlsPanel.add(steppingCheckBox);
        controlsPanel.add(startToolsButton);
        controlsPanel.add(nextCycleButton);
        makeSlider(controlsPanel);
        controlsPanel.add(tabbedPane);
        return controlsPanel;
    }

    private void makeSlider(JPanel controlsPanel){
        slider = new JSlider(JSlider.HORIZONTAL, 0, 50, 1);
        slider.setMinorTickSpacing(5);
        slider.setMajorTickSpacing(20);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        JLabel sliderLabel = new JLabel("Cycle Number: ");
        JTextField sliderText = new JTextField(5);

        controlsPanel.add(slider);
        controlsPanel.add(sliderLabel);
        controlsPanel.add(sliderText);
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
