package GwendolenToolWindow;

import GwenDebugger.AgentInspector;
import GwenDebugger.BreakpointController;
import GwenDebugger.DebugSessionController;
import com.intellij.codeInsight.template.impl.Variable;
import com.intellij.debugger.ui.DebuggerContentInfo;
import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.execution.executors.DefaultDebugExecutor;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.runners.ExecutionEnvironmentBuilder;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.content.Content;
import com.intellij.util.keyFMap.KeyFMap;
import com.intellij.xdebugger.*;
import com.intellij.xdebugger.evaluation.XDebuggerEvaluator;
import com.intellij.xdebugger.frame.*;
import com.intellij.xdebugger.impl.XDebugSessionImpl;
import com.intellij.xdebugger.impl.XDebuggerUtilImpl;
import com.intellij.xdebugger.impl.evaluate.XDebuggerEvaluationDialog;
import com.intellij.xdebugger.impl.frame.XFramesView;
import com.intellij.xdebugger.impl.ui.XDebugSessionData;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XStackFrameNode;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.ui.ComboBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.plaf.ComponentUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();
    private final BreakpointController breakpointController;
    private final AgentInspector agentInspector;
    private RunManager runManager;
    private boolean isStepping;
    private ComboBox<String> runConfigSelect = new ComboBox<String>();
    private String currentRunConfig;

    private Project project;
    private List<RunConfiguration> runConfigList;

    public GwenToolWindowContent(Project project, ToolWindow toolWindow){
        this.project = project;
        breakpointController = new BreakpointController(project);
        agentInspector = new AgentInspector(project);
        runManager = RunManager.getInstance(project);
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
        /*
        String runConfigStr = runConfigSelect.getSelectedItem().toString();
        int index = -1;
        for(int i=0; i<runConfigList.size(); i++){
            RunConfiguration runConfig = runConfigList.get(i);
            if(runConfigStr.equals(runConfig.getName())){
                index = i;
            }
        }
        if(index != -1){
            //Executor executor = DefaultDebugExecutor.getDebugExecutorInstance();
            //ExecutionEnvironmentBuilder
            //        .create(project, executor, runConfigList.get(index))
            //        .buildAndExecute();
            XDebugSession[] debugSessionList = XDebuggerManager.getInstance(project).getDebugSessions();
            XDebugSession debugSesh = XDebuggerManager.getInstance(project).getCurrentSession();
            System.out.println(debugSessionList.length);
            XDebugSession debugSession = debugSessionList[0];
            System.out.println(debugSession.getSessionName());

        }
         */

        XDebugSession debugSession = XDebuggerManager.getInstance(project).getCurrentSession();

        RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
        JComponent component = runContentDescriptor.getComponent();
        XDebuggerTree tree;

        tree = getDebugTree(component);
        XDebuggerTreeNode rootNode = tree.getRoot();
        List<XDebuggerTreeNode> child1 = (List<XDebuggerTreeNode>) rootNode.getChildren();
        List<XDebuggerTreeNode> child2 = (List<XDebuggerTreeNode>) child1.get(0).getChildren();

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

        controlsPanel.add(steppingCheckBox);
        controlsPanel.add(startToolsButton);
        agentInspector.seeAgent();
        return controlsPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
