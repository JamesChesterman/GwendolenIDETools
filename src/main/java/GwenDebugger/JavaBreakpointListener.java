package GwenDebugger;

import GwendolenToolWindow.GwenToolWindowContent;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.DataManager;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.xdebugger.impl.frame.XDebuggerFramesList;
import com.intellij.xdebugger.impl.ui.XDebugSessionTab;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueContainerNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JavaBreakpointListener implements XDebugSessionListener {

    XDebugSession debugSession;
    GwenToolWindowContent gwenToolWindow;

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

    //Called when the debug session is paused (when a breakpoint is hit in the Java code
    @Override
    public void sessionPaused(){
        XDebugSessionListener.super.sessionPaused();
        updateDebugInfo();
    }

    public void updateDebugInfo(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //This is executed after the wait (time specified below)
            RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
            JComponent component = runContentDescriptor.getComponent();
            XDebuggerTree tree = getDebugTree(component);
            //Pass the new tree back to gwenToolWindow
            gwenToolWindow.updateDebugTreeValues(tree);
        }, 500, TimeUnit.MILLISECONDS);
        executorService.shutdown();
    }

    public JavaBreakpointListener(XDebugSession debugSession, GwenToolWindowContent gwenToolWindow){
        super();
        this.debugSession = debugSession;
        this.gwenToolWindow = gwenToolWindow;
    }

}
