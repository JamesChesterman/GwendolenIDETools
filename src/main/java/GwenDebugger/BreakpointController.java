package GwenDebugger;

import com.google.common.base.Supplier;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.breakpoints.*;
import com.intellij.xdebugger.impl.breakpoints.LineBreakpointState;
import org.apache.tools.ant.taskdefs.Java;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Collection;
import java.util.List;

public class BreakpointController {

    final String fileURL = "C:\\Users\\chest\\mcapl-mcapl2023\\src\\classes\\ail\\semantics\\AILAgent.java";
    final int lineNum = 2034;

    Project project;
    VirtualFile virtualFile;
    XBreakpointManager breakpointManager;
    GwenLineBreakpointType gwenLineBreakpointType;
    GwenBreakpointProperties gwenBreakpointProperties;

    public void toggleBreakpoint(){
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

    public boolean checkBreakpoint(){
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

    public BreakpointController(Project project){
        breakpointManager = XDebuggerManager.getInstance(project).getBreakpointManager();
        gwenLineBreakpointType = new GwenLineBreakpointType();
        gwenBreakpointProperties = new GwenBreakpointProperties();
        this.project = project;
    }

}