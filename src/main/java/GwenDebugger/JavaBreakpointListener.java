package GwenDebugger;

import GwendolenToolWindow.GwenToolWindowContent;
import GwendolenToolWindow.PlansViewer;
import Settings.GwenSettingsState;
import com.intellij.execution.impl.ConsoleViewImpl;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.ide.DataManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebugSessionListener;
import com.intellij.xdebugger.XSourcePosition;
import com.intellij.xdebugger.evaluation.XDebuggerEditorsProvider;
import com.intellij.xdebugger.frame.XStackFrame;
import com.intellij.xdebugger.impl.XSourcePositionImpl;
import com.intellij.xdebugger.impl.frame.XDebuggerFramesList;
import com.intellij.xdebugger.impl.ui.XDebugSessionTab;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTreeListener;
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
    PlansViewer plansViewer;
    private boolean skipMode;
    //private static String ailAgentFileURL = "C:\\Users\\chest\\mcapl-mcapl2023\\src\\classes\\ail\\semantics\\AILAgent.java";
    //private static int ailAgentLineNum = 2034;

    //private static String planLibraryFileURL = "C:\\Users\\chest\\mcapl-mcapl2023\\src\\classes\\ail\\syntax\\PlanLibrary.java";
    //private static int planLibraryLineNum = 174;
    private boolean planMode;
    private static int TIMETOGETTREE = 100;
    private static GwenSettingsState gwenSettingsState;

    public JavaBreakpointListener(XDebugSession debugSession, GwenToolWindowContent gwenToolWindow, PlansViewer plansViewer){
        super();
        this.debugSession = debugSession;
        this.gwenToolWindow = gwenToolWindow;
        this.plansViewer = plansViewer;
        skipMode = false;
        planMode = false;

        //gwenSettingsState = GwenSettingsState.getInstance();
        //System.out.println(gwenSettingsState.ailAgentFilePath);
    }

    public static String getAilAgentFileURL(){
        return GwenSettingsState.getInstance().ailAgentFilePath;
    }

    public static int getAilAgentLineNum(){
        String lineNumStr = GwenSettingsState.getInstance().getAilAgentLineNum();
        if(lineNumStr != null){
            return Integer.parseInt(lineNumStr);
        }else{
            return -1;
        }
    }

    public static String getPlanLibraryFileURL(){
        return GwenSettingsState.getInstance().planLibraryFilePath;
    }

    public static int getPlanLibraryLineNum(){
        String lineNumStr = GwenSettingsState.getInstance().getPlanLibraryLineNum();
        if(lineNumStr != null){
            return Integer.parseInt(lineNumStr);
        }else{
            return -1;
        }
    }

    public void setSkipMode(boolean skipMode) {
        this.skipMode = skipMode;
    }
    public void setPlanMode(boolean planMode){
        this.planMode = planMode;
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

    //Called when the debug session is paused (when a breakpoint is hit in the Java code
    @Override
    public void sessionPaused(){
        XDebugSessionListener.super.sessionPaused();
        getDebugPos();
    }

    //Get the file the debug session has been paused at
    //So you know which method to return the debug tree to
    private void getDebugPos(){
        XSourcePosition position = debugSession.getCurrentPosition();
        //1 = AILAgent. 2 = PlanLibrary
        int fileNum = 0;
        if(position != null){
            VirtualFile file = position.getFile();
            String fileURL = file.getPresentableUrl();
            int fileLineNum = position.getLine();
            //When no input is given for line numbers
            //These cases will just be skipped over
            if(fileURL.equals(getAilAgentFileURL()) && fileLineNum == getAilAgentLineNum()){
                fileNum = 1;
            }else if(fileURL.equals(getPlanLibraryFileURL()) && fileLineNum == getPlanLibraryLineNum()){
                fileNum = 2;
            }
        }
        if(fileNum == 1 || fileNum == 2){
            updateDebugInfo(fileNum);
        }
    }


    public void updateDebugInfo(int fileNum){
        if(planMode) {
            //In plans mode, will skip over breakpoints in AILAgent
            //Will get debug tree when breakpoint hit in PlanLibrary
            if (fileNum == 1) {
                //Breakpoint hit in AILAgent. Do a skip in gwenToolContent
                gwenToolWindow.updateDebugTreeValues(null, true, true);
            } else {
                //Breakpoint hit in PlanLibrary. Send debug tree to PlansViewer to handle
                sendDebugTreePlansViewer();
            }
        }else{
            if(skipMode){
                //In skip mode (when not in plans mode)
                //Will skip over any plans breakpoints and skip over any AILAgent breakpoints
                if(fileNum == 1){
                    gwenToolWindow.updateDebugTreeValues(null, true, false);
                }else{
                    //Skip over plans breakpoint
                    plansViewer.updateDebugTreeValues(null, true);
                }
            }else{
                //When not in skip mode (and not plans mode)
                //Will skip over any plans breakpoints
                //Will stop for AILAgent breakpoints
                if(fileNum == 1){
                    sendDebugTreeGwenToolWindow();
                }else{
                    //Skip over plans breakpoint
                    plansViewer.updateDebugTreeValues(null, true);
                }
            }
        }
    }


    //Tries to get the debug tree
    //If it's null, then call the method again after a delay
    //If it's not null, send it to gwenToolWindow
    private void sendDebugTreeGwenToolWindow(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //This is executed after the wait (time specified below)
            RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
            JComponent component = runContentDescriptor.getComponent();
            XDebuggerTree tree = getDebugTree(component);

            if(tree == null){
                //Start wait again and see if the tree has loaded yet.
                sendDebugTreeGwenToolWindow();
            }else{
                //Pass the new tree to right place
                gwenToolWindow.updateDebugTreeValues(tree, false, false);
            }
        }, TIMETOGETTREE, TimeUnit.MILLISECONDS);
        executorService.shutdown();

    }




    //Tries to get the debug tree
    //If it's null, then call the method again after a delay
    //If it's not null, send it to plansViewer
    private void sendDebugTreePlansViewer(){
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //This is executed after the wait (time specified below)
            RunContentDescriptor runContentDescriptor = debugSession.getRunContentDescriptor();
            JComponent component = runContentDescriptor.getComponent();
            XDebuggerTree tree = getDebugTree(component);

            if(tree == null){
                //Start wait again and see if the tree has loaded yet.
                sendDebugTreePlansViewer();
            }else{
                //Pass the new tree to right place
                plansViewer.updateDebugTreeValues(tree, false);
            }
        }, TIMETOGETTREE, TimeUnit.MILLISECONDS);
        executorService.shutdown();

    }


}
