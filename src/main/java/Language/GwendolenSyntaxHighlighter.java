package Language;

import Grammar.GwendolenIntelliJLexer;
import Language.psi.GwendolenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

import java.util.Objects;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GwendolenSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey GWENDOLEN =
            createTextAttributesKey("GWENDOLEN_GWENDOLEN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey GOAL =
            createTextAttributesKey("GWENDOLEN_GOAL", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey BELIEFS =
            createTextAttributesKey("GWENDOLEN_BELIEFS", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey UNKNOWN =
            createTextAttributesKey("GWENDOLEN_UNKNOWN", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey[] GWENDOLEN_KEYS = new TextAttributesKey[]{GWENDOLEN};
    private static final TextAttributesKey[] GOAL_KEYS = new TextAttributesKey[]{GOAL};
    private static final TextAttributesKey[] BELIEFS_KEYS = new TextAttributesKey[]{BELIEFS};
    private static final TextAttributesKey[] UNKNOWN_KEYS = new TextAttributesKey[]{UNKNOWN};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private Project project;
    private VirtualFile virtualFile;

    public void setProject(Project project){
        this.project = project;
    }

    public void setVirtualFile(VirtualFile virtualFile){
        this.virtualFile = virtualFile;
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer(){
        CharSequence empty = "";
        FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
        CharSequence charSequence = (fileDocumentManager.getDocument(virtualFile)).getText();
        if(charSequence.isEmpty()){
            return new GwendolenIntelliJLexer(empty);
        }else{
            return new GwendolenIntelliJLexer(charSequence);
        }


    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType){
        if(tokenType.equals(GwendolenTypes.GWENDOLEN)){
            return GWENDOLEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GOAL)){
            return GOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.BELIEFS)){
            return BELIEFS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.UNKNOWN)){
            return UNKNOWN_KEYS;
        }
        return EMPTY_KEYS;
    }

}





