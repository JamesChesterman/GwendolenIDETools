package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.xdebugger.*;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();
    private final BreakpointController breakpointController;
    private boolean isStepping;

    private final Project project;
    private XDebugSession debugSession;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        breakpointController = new BreakpointController(project);
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);
    }


    //Get the debug tree for the debug session's tool window (from RunContentDescriptor)
    private XDebuggerTree getDebugTree(Component component) {
        if (component instanceof XDebuggerTree) {
            return (XDebuggerTree) component;
        }

        if (component instanceof Container) {
            Component[] children = ((Container) component).getComponents();
            for (Component child : children) {
                XDebuggerTree tree = getDebugTree(child);
                if (tree != null) {
                    return tree;
                }
            }
        }

        return null;
    }

    private void startTools() throws ExecutionException {

        debugSession = XDebuggerManager.getInstance(project).getCurrentSession();

        RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
        JComponent component = runContentDescriptor.getComponent();
        XDebuggerTree tree;

        tree = getDebugTree(component);
        XDebuggerTreeNode rootNode = tree.getRoot();
        List<XDebuggerTreeNode> child1 = (List<XDebuggerTreeNode>) rootNode.getChildren();
        List<XDebuggerTreeNode> child2 = (List<XDebuggerTreeNode>) child1.get(0).getChildren();
        XValueNodeImpl val = (XValueNodeImpl) child2.get(2);
        String str = val.getRawValue();

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

        controlsPanel.add(steppingCheckBox);
        controlsPanel.add(startToolsButton);
        controlsPanel.add(nextCycleButton);
        return controlsPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
