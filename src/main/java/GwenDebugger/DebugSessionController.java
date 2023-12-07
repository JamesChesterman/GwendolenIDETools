package GwenDebugger;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;

import java.util.List;

public class DebugSessionController {
    Project project;

    public DebugSessionController(Project project){
        this.project = project;
    }

    public List<RunConfiguration> listRunConfigs(){
        RunManager runManager = RunManager.getInstance(project);
        return runManager.getAllConfigurationsList().stream().toList();
    }

    public void makeDebugSession(){

    }
}
