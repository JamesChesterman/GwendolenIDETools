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
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();
    private final BreakpointController breakpointController;
    private boolean isStepping;

    private final Project project;
    private XDebugSession debugSession;

    private List<XDebuggerTree> listOfTrees;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        breakpointController = new BreakpointController(project);
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);
    }


    private void startTools() throws ExecutionException {
        debugSession = XDebuggerManager.getInstance(project).getCurrentSession();
        if(debugSession != null){
            JavaBreakpointListener breakpointListener = new JavaBreakpointListener(debugSession);
            debugSession.addSessionListener(breakpointListener);
        }
    }

    //Store the values received in JavaBreakpointListener (whenever the program pauses)
    //Store them in this class as this class has access to the tabs BGIViewer, Breakpoints etc
    private void updateDebugTreeValues(XDebuggerTree newDebugTree){
        listOfTrees.add(newDebugTree);
    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        controlsPanel.setLayout(new BoxLayout(controlsPanel, BoxLayout.Y_AXIS));
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

        JButton nextCycleButton = new JButton("Next Cycle");
        nextCycleButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                breakpointController.goToNextCycle(debugSession);
            }
        });

        JBTabbedPane tabbedPane = new JBTabbedPane();
        JPanel panel1 = new JPanel();
        panel1.add(new JLabel("Content of tab1"));
        tabbedPane.addTab("Tab 1", panel1);

        controlsPanel.add(steppingCheckBox);
        controlsPanel.add(startToolsButton);
        controlsPanel.add(nextCycleButton);
        controlsPanel.add(tabbedPane);
        return controlsPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
