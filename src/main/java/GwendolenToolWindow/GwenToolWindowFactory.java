package GwendolenToolWindow;

import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

final class GwenToolWindowFactory implements ToolWindowFactory, DumbAware {

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow){
        GwenToolWindowContent gwenToolWindowContent = new GwenToolWindowContent(project, toolWindow);
        JBScrollPane scrollPane = new JBScrollPane(gwenToolWindowContent.getContentPanel());
        Content content = ContentFactory.getInstance().createContent(scrollPane, "", false);
        toolWindow.getContentManager().addContent(content);
    }

}
