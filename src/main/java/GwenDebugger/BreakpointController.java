package GwenDebugger;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.ui.RunContentDescriptor;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebugProcess;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.breakpoints.*;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.List;

public class BreakpointController {

    Project project;
    VirtualFile virtualFile;
    XBreakpointManager breakpointManager;
    GwenLineBreakpointType gwenLineBreakpointType;
    GwenBreakpointProperties gwenBreakpointProperties;

    public void toggleBreakpoint(String fileURL, int lineNum){
        Runnable runnable = () -> breakpointManager.addLineBreakpoint(
                gwenLineBreakpointType,
                fileURL,
                lineNum,
                gwenBreakpointProperties
        );

        WriteCommandAction.runWriteCommandAction(project, runnable);

        //Toggle the breakpoint to active
        virtualFile = LocalFileSystem.getInstance().findFileByIoFile(new File(fileURL));
        XDebuggerUtil.getInstance().toggleLineBreakpoint(project, virtualFile, lineNum);
    }

    public boolean checkBreakpoint(String fileURL, int lineNum){
        virtualFile = LocalFileSystem.getInstance().findFileByIoFile(new File(fileURL));

        XBreakpoint<?>[] breakpoints = breakpointManager.getAllBreakpoints();
        for(XBreakpoint<?> breakpoint : breakpoints){
            if(breakpoint.getSourcePosition() != null){
                VirtualFile fileFound = breakpoint.getSourcePosition().getFile();
                int line = breakpoint.getSourcePosition().getLine();
                if(fileFound.equals(virtualFile) && line == lineNum){
                    return true;
                }
            }
        }
        return false;
    }

    public void goToNextCycle(XDebugSession debugSession){
        if(debugSession == null){
            System.out.println("ERROR1");
        }else{
            debugSession.resume();
        }
    }

    public BreakpointController(Project project){
        breakpointManager = XDebuggerManager.getInstance(project).getBreakpointManager();
        gwenLineBreakpointType = new GwenLineBreakpointType();
        gwenBreakpointProperties = new GwenBreakpointProperties();

        this.project = project;
    }

}