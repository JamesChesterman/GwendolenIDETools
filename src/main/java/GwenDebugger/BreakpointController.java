package GwenDebugger;

import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.xdebugger.XDebuggerManager;
import com.intellij.xdebugger.XDebuggerUtil;
import com.intellij.xdebugger.breakpoints.XBreakpointManager;

import java.io.File;

public class BreakpointController {

    final String fileURL = "C:\\Users\\chest\\mcapl-mcapl2023\\src\\classes\\ail\\semantics\\AILAgent.java";
    final int lineNum = 2034;

    Project project;
    VirtualFile virtualFile;
    XBreakpointManager breakpointManager;
    GwenLineBreakpointType gwenLineBreakpointType;
    GwenBreakpointProperties gwenBreakpointProperties;

    public void addBreakpoint(){
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

    public BreakpointController(Project project){
        breakpointManager = XDebuggerManager.getInstance(project).getBreakpointManager();
        gwenLineBreakpointType = new GwenLineBreakpointType();
        gwenBreakpointProperties = new GwenBreakpointProperties();
        this.project = project;
    }

}