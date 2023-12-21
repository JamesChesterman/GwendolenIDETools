package GwenDebugger;

import GwendolenToolWindow.GwenToolWindowContent;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
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

    @Override
    public void sessionPaused(){
        XDebugSessionListener.super.sessionPaused();

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //This is executed after the wait
            RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
            JComponent component = runContentDescriptor.getComponent();
            XDebuggerTree tree = getDebugTree(component);
            //Pass the new tree back to gwenToolWindow
            gwenToolWindow.updateDebugTreeValues(tree);
        }, 500, TimeUnit.MILLISECONDS);
        executorService.shutdown();



        /*
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
            JComponent component = runContentDescriptor.getComponent();
            XDebuggerTree tree;

            tree = getDebugTree(component);
            XDebuggerTreeNode rootNode = tree.getRoot();
            List<XDebuggerTreeNode> child1 = (List<XDebuggerTreeNode>) rootNode.getChildren();
            List<XDebuggerTreeNode> child2 = (List<XDebuggerTreeNode>) child1.get(0).getChildren();
            XValueNodeImpl val = (XValueNodeImpl) child2.get(2);
            List<XDebuggerTreeNode> layer1 = (List<XDebuggerTreeNode>) rootNode.getChildren();
            int stageIndex = -1;
            for(int i=0; i<layer1.size(); i++){
                if(layer1.get(i).toString().equals("stage")){
                    stageIndex = i;
                    break;
                }
            }
            if(stageIndex == -1){
                System.out.println("ERROR - STAGE VALUE NOT FOUND");
            }else{
                layer1.get(stageIndex).getChildCount();
                final XDebuggerTreeNode nodeLayer1 = layer1.get(stageIndex);
                //This triggers calculation of children for that node
                int numOfChildren = nodeLayer1.getChildCount();
                ScheduledExecutorService executorService1 = Executors.newScheduledThreadPool(1);
                executorService1.schedule(() -> {
                    int stageNameIndex = -1;
                    List<XDebuggerTreeNode> layer2 = (List<XDebuggerTreeNode>) nodeLayer1.getChildren();
                    for(int i=0; i<layer2.size(); i++){
                        if(layer2.get(i).toString().equals("name")){
                            stageNameIndex = i;
                            break;
                        }
                    }
                    if(stageNameIndex == -1){
                        System.out.println("ERROR - STAGE NAME VALUE NOT FOUND");
                    }else{
                        XValueNodeImpl val1 = (XValueNodeImpl) layer2.get(stageNameIndex);
                        System.out.println("HERE " + val1.getRawValue());

                        System.out.println(layer2.get(stageNameIndex).toString());
                    }
                }, 5, TimeUnit.SECONDS);
                executorService1.shutdown();

            }

            String str = val.getRawValue();
        }, 5, TimeUnit.SECONDS);
        executorService.shutdown();


         */



    }

    public JavaBreakpointListener(XDebugSession debugSession, GwenToolWindowContent gwenToolWindow){
        super();
        this.debugSession = debugSession;
        this.gwenToolWindow = gwenToolWindow;
    }

}
