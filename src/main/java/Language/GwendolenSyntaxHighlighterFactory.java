package Language;

import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

public class GwendolenSyntaxHighlighterFactory extends SyntaxHighlighterFactory {
    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile){
        //Use the VirtualFile when looking at the contents of the file, project would cause an error to occur when
        //there are no selected files.
        GwendolenSyntaxHighlighter gwenSyntaxHighlighter = new GwendolenSyntaxHighlighter();
        gwenSyntaxHighlighter.setVirtualFile(virtualFile);
        gwenSyntaxHighlighter.setProject(project);
        //System.out.println(gwenSyntaxHighlighter.)
        return gwenSyntaxHighlighter;
    }
}




