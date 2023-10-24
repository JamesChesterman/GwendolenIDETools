package Language;

import Grammar.GwendolenIntelliJLexer;
import psi.GwendolenTypes;
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

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GwendolenSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey GWENDOLEN =
            createTextAttributesKey("GWENDOLEN_GWENDOLEN", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("GWENDOLEN_COMMENT", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey UNKNOWN =
            createTextAttributesKey("GWENDOLEN_UNKNOWN", DefaultLanguageHighlighterColors.STRING);

    private static final TextAttributesKey[] GWENDOLEN_KEYS = new TextAttributesKey[]{GWENDOLEN};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] UNKNOWN_KEYS = new TextAttributesKey[]{UNKNOWN};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    private Project project;

    public void setProject(Project project){
        this.project = project;
    }

    @NotNull
    @Override
    public Lexer getHighlightingLexer(){
        FileEditorManager fileEditorManager = FileEditorManager.getInstance(project);
        VirtualFile[] selectedFiles = fileEditorManager.getSelectedFiles();
        CharSequence empty = "";
        if(selectedFiles.length > 0){
            VirtualFile virtualFile = selectedFiles[0];
            FileDocumentManager fileDocumentManager = FileDocumentManager.getInstance();
            CharSequence charSequence = (fileDocumentManager.getDocument(virtualFile)).getText();
            System.out.println(charSequence);
            if(charSequence.isEmpty()){
                return new GwendolenIntelliJLexer(empty);
            }else{
                return new GwendolenIntelliJLexer(charSequence);
            }
        }else{
            return new GwendolenIntelliJLexer(empty);
        }
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType){
        if(tokenType.equals(GwendolenTypes.GWENDOLEN)){
            return GWENDOLEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.COMMENT)){
            return COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.LINE_COMMENT)){
            return LINE_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.UNKNOWN)){
            return UNKNOWN_KEYS;
        }
        return EMPTY_KEYS;
    }

}


