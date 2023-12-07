package GwenDebugger;

import com.intellij.openapi.project.Project;
import com.intellij.xdebugger.XDebugSession;
import com.intellij.xdebugger.XDebuggerManager;

public class AgentInspector {
    Project project;
    public void seeAgent(){
        //Maybe have to make the session through the GwenToolWindow!

        XDebugSession debugSession = XDebuggerManager.getInstance(project).getCurrentSession();
    }

    public AgentInspector(Project project){
        this.project = project;
    }
}
