package Language;

import Language.psi.GwendolenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GwendolenSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey GOAL =
            createTextAttributesKey("GWENDOLEN_GOAL", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BELIEFRULES =
            createTextAttributesKey("GWENDOLEN_BELIEFRULES", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BELIEFS =
            createTextAttributesKey("GWENDOLEN_BELIEFS", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BELIEF_BLOCK =
            createTextAttributesKey("GWENDOLEN_BELIEF_BLOCK", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey BELIEVE =
            createTextAttributesKey("GWENDOLEN_BELIEVE", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey CLOSE =
            createTextAttributesKey("GWENDOLEN_CLOSE", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey COLON =
            createTextAttributesKey("GWENDOLEN_COLON", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("GWENDOLEN_COMMA", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey CONST =
            createTextAttributesKey("GWENDOLEN_CONST", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey CURLYCLOSE =
            createTextAttributesKey("GWENDOLEN_CURLYCLOSE", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey CURLYOPEN =
            createTextAttributesKey("GWENDOLEN_CURLYOPEN", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey EQUAL =
            createTextAttributesKey("GWENDOLEN_EQUAL", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey GL_ACHIEVEGOAL =
            createTextAttributesKey("GWENDOLEN_GL_ACHIEVEGOAL", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey GL_PERFORMGOAL =
            createTextAttributesKey("GWENDOLEN_GL_PERFORMGOAL", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey GL_SQCLOSE =
            createTextAttributesKey("GWENDOLEN_GL_SQCLOSE", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey GL_SQOPEN =
            createTextAttributesKey("GWENDOLEN_GL_SQOPEN", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey GOAL_BLOCK =
            createTextAttributesKey("GWENDOLEN_GOAL_BLOCK", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey GOAL_IB =
            createTextAttributesKey("GWENDOLEN_GOAL_IB", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey GOAL_RR =
            createTextAttributesKey("GWENDOLEN_GOAL_RR", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey GWENDOLEN =
            createTextAttributesKey("GWENDOLEN_GWENDOLEN", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey IDPUNCT =
            createTextAttributesKey("GWENDOLEN_IDPUNCT", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey LESS =
            createTextAttributesKey("GWENDOLEN_LESS", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey LOCK =
            createTextAttributesKey("GWENDOLEN_LOCK", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey MINUS =
            createTextAttributesKey("GWENDOLEN_MINUS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey MULT =
            createTextAttributesKey("GWENDOLEN_MULT", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey NAME =
            createTextAttributesKey("GWENDOLEN_NAME", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey NAME_PM =
            createTextAttributesKey("GWENDOLEN_NAME_PM", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey NOT =
            createTextAttributesKey("GWENDOLEN_NOT", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("GWENDOLEN_NUMBER", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey OPEN =
            createTextAttributesKey("GWENDOLEN_OPEN", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey PLANS =
            createTextAttributesKey("GWENDOLEN_PLANS", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey PLUS =
            createTextAttributesKey("GWENDOLEN_PLUS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey PL_ACHIEVEGOAL =
            createTextAttributesKey("GWENDOLEN_PL_ACHIEVEGOAL", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey PL_BAR =
            createTextAttributesKey("GWENDOLEN_PL_BAR", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey PL_CONST =
            createTextAttributesKey("GWENDOLEN_PL_CONST", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PL_PERFORMGOAL =
            createTextAttributesKey("GWENDOLEN_PL_PERFORMGOAL", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey PL_SQCLOSE =
            createTextAttributesKey("GWENDOLEN_PL_SQCLOSE", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey PL_SQOPEN =
            createTextAttributesKey("GWENDOLEN_PL_SQOPEN", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey PL_VAR =
            createTextAttributesKey("GWENDOLEN_PL_VAR", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey QUOTED_STRING =
            createTextAttributesKey("GWENDOLEN_QUOTED_STRING", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey RECEIVED =
            createTextAttributesKey("GWENDOLEN_RECEIVED", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey RR_BLOCK =
            createTextAttributesKey("GWENDOLEN_RR_BLOCK", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey RR_NEWLINE =
            createTextAttributesKey("GWENDOLEN_RR_NEWLINE", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey RULEARROW =
            createTextAttributesKey("GWENDOLEN_RULEARROW", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey SEMI =
            createTextAttributesKey("GWENDOLEN_SEMI", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey SEND =
            createTextAttributesKey("GWENDOLEN_SEND", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey SENT =
            createTextAttributesKey("GWENDOLEN_SENT", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey SHRIEK =
            createTextAttributesKey("GWENDOLEN_SHRIEK", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey TELL =
            createTextAttributesKey("GWENDOLEN_TELL", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey TRUE =
            createTextAttributesKey("GWENDOLEN_TRUE", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey UNKNOWN =
            createTextAttributesKey("GWENDOLEN_UNKNOWN", DefaultLanguageHighlighterColors.NUMBER);




    private static final TextAttributesKey[] GOAL_KEYS = new TextAttributesKey[]{GOAL};
    private static final TextAttributesKey[] BELIEFRULES_KEYS = new TextAttributesKey[]{BELIEFRULES};
    private static final TextAttributesKey[] BELIEFS_KEYS = new TextAttributesKey[]{BELIEFS};
    private static final TextAttributesKey[] BELIEF_BLOCK_KEYS = new TextAttributesKey[]{BELIEF_BLOCK};
    private static final TextAttributesKey[] BELIEVE_KEYS = new TextAttributesKey[]{BELIEVE};
    private static final TextAttributesKey[] CLOSE_KEYS = new TextAttributesKey[]{CLOSE};
    private static final TextAttributesKey[] COLON_KEYS = new TextAttributesKey[]{COLON};
    private static final TextAttributesKey[] COMMA_KEYS = new TextAttributesKey[]{COMMA};
    private static final TextAttributesKey[] CONST_KEYS = new TextAttributesKey[]{CONST};
    private static final TextAttributesKey[] CURLYCLOSE_KEYS = new TextAttributesKey[]{CURLYCLOSE};
    private static final TextAttributesKey[] CURLYOPEN_KEYS = new TextAttributesKey[]{CURLYOPEN};
    private static final TextAttributesKey[] EQUAL_KEYS = new TextAttributesKey[]{EQUAL};
    private static final TextAttributesKey[] GL_ACHIEVEGOAL_KEYS = new TextAttributesKey[]{GL_ACHIEVEGOAL};
    private static final TextAttributesKey[] GL_PERFORMGOAL_KEYS = new TextAttributesKey[]{GL_PERFORMGOAL};
    private static final TextAttributesKey[] GL_SQCLOSE_KEYS = new TextAttributesKey[]{GL_SQCLOSE};
    private static final TextAttributesKey[] GL_SQOPEN_KEYS = new TextAttributesKey[]{GL_SQOPEN};
    private static final TextAttributesKey[] GOAL_BLOCK_KEYS = new TextAttributesKey[]{GOAL_BLOCK};
    private static final TextAttributesKey[] GOAL_IB_KEYS = new TextAttributesKey[]{GOAL_IB};
    private static final TextAttributesKey[] GOAL_RR_KEYS = new TextAttributesKey[]{GOAL_RR};
    private static final TextAttributesKey[] GWENDOLEN_KEYS = new TextAttributesKey[]{GWENDOLEN};
    private static final TextAttributesKey[] IDPUNCT_KEYS = new TextAttributesKey[]{IDPUNCT};
    private static final TextAttributesKey[] LESS_KEYS = new TextAttributesKey[]{LESS};
    private static final TextAttributesKey[] LOCK_KEYS = new TextAttributesKey[]{LOCK};
    private static final TextAttributesKey[] MINUS_KEYS = new TextAttributesKey[]{MINUS};
    private static final TextAttributesKey[] MULT_KEYS = new TextAttributesKey[]{MULT};
    private static final TextAttributesKey[] NAME_KEYS = new TextAttributesKey[]{NAME};
    private static final TextAttributesKey[] NAME_PM_KEYS = new TextAttributesKey[]{NAME_PM};
    private static final TextAttributesKey[] NOT_KEYS = new TextAttributesKey[]{NOT};
    private static final TextAttributesKey[] NUMBER_KEYS = new TextAttributesKey[]{NUMBER};
    private static final TextAttributesKey[] OPEN_KEYS = new TextAttributesKey[]{OPEN};
    private static final TextAttributesKey[] PLANS_KEYS = new TextAttributesKey[]{PLANS};
    private static final TextAttributesKey[] PLUS_KEYS = new TextAttributesKey[]{PLUS};
    private static final TextAttributesKey[] PL_ACHIEVEGOAL_KEYS = new TextAttributesKey[]{PL_ACHIEVEGOAL};
    private static final TextAttributesKey[] PL_BAR_KEYS = new TextAttributesKey[]{PL_BAR};
    private static final TextAttributesKey[] PL_CONST_KEYS = new TextAttributesKey[]{PL_CONST};
    private static final TextAttributesKey[] PL_PERFORMGOAL_KEYS = new TextAttributesKey[]{PL_PERFORMGOAL};
    private static final TextAttributesKey[] PL_SQCLOSE_KEYS = new TextAttributesKey[]{PL_SQCLOSE};
    private static final TextAttributesKey[] PL_SQOPEN_KEYS = new TextAttributesKey[]{PL_SQOPEN};
    private static final TextAttributesKey[] PL_VAR_KEYS = new TextAttributesKey[]{PL_VAR};
    private static final TextAttributesKey[] QUOTED_STRING_KEYS = new TextAttributesKey[]{QUOTED_STRING};
    private static final TextAttributesKey[] RECEIVED_KEYS = new TextAttributesKey[]{RECEIVED};
    private static final TextAttributesKey[] RR_BLOCK_KEYS = new TextAttributesKey[]{RR_BLOCK};
    private static final TextAttributesKey[] RR_NEWLINE_KEYS = new TextAttributesKey[]{RR_NEWLINE};
    private static final TextAttributesKey[] RULEARROW_KEYS = new TextAttributesKey[]{RULEARROW};
    private static final TextAttributesKey[] SEMI_KEYS = new TextAttributesKey[]{SEMI};
    private static final TextAttributesKey[] SEND_KEYS = new TextAttributesKey[]{SEND};
    private static final TextAttributesKey[] SENT_KEYS = new TextAttributesKey[]{SENT};
    private static final TextAttributesKey[] SHRIEK_KEYS = new TextAttributesKey[]{SHRIEK};
    private static final TextAttributesKey[] TELL_KEYS = new TextAttributesKey[]{TELL};
    private static final TextAttributesKey[] TRUE_KEYS = new TextAttributesKey[]{TRUE};
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
        if(tokenType.equals(GwendolenTypes.GOAL)){
            return GOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.BELIEFRULES)){
            return BELIEFRULES_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.BELIEFS)){
            return BELIEFS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.BELIEF_BLOCK)){
            return BELIEF_BLOCK_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.BELIEVE)){
            return BELIEVE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.CLOSE)){
            return CLOSE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.COLON)){
            return COLON_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.COMMA)){
            return COMMA_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.COMMENT)){
            return COMMENT;
        }
        if(tokenType.equals(GwendolenTypes.CONST)){
            return CONST_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.CURLYCLOSE)){
            return CURLYCLOSE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.CURLYOPEN)){
            return CURLYOPEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.EQUAL)){
            return EQUAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_ACHIEVEGOAL)){
            return GL_ACHIEVEGOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_PERFORMGOAL)){
            return GL_PERFORMGOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_SQCLOSE)){
            return GL_SQCLOSE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_SQOPEN)){
            return GL_SQOPEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GOAL_BLOCK)){
            return GOAL_BLOCK_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GOAL_IB)){
            return GOAL_IB_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GOAL_RR)){
            return GOAL_RR_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GWENDOLEN)){
            return GWENDOLEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.IDPUNCT)){
            return IDPUNCT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.LESS)){
            return LESS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.LOCK)){
            return LOCK_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.MINUS)){
            return MINUS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.MULT)){
            return MULT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.NAME)){
            return NAME_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.NAME_PM)){
            return NAME_PM_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.NOT)){
            return NOT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.NUMBER)){
            return NUMBER_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.OPEN)){
            return OPEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PLANS)){
            return PLANS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PLUS)){
            return PLUS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_ACHIEVEGOAL)){
            return PL_ACHIEVEGOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_BAR)){
            return PL_BAR_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_CONST)){
            return PL_CONST_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_PERFORMGOAL)){
            return PL_PERFORMGOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_SQCLOSE)){
            return PL_SQCLOSE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_SQOPEN)){
            return PL_SQOPEN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_VAR)){
            return PL_VAR_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.QUOTED_STRING)){
            return QUOTED_STRING_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RECEIVED)){
            return RECEIVED_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RR_BLOCK)){
            return RR_BLOCK_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RR_NEWLINE)){
            return RR_NEWLINE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RULEARROW)){
            return RULEARROW_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.SEMI)){
            return SEMI_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.SEND)){
            return SEND_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.SENT)){
            return SENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.SHRIEK)){
            return SHRIEK_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.TELL)){
            return TELL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.TRUE)){
            return TRUE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.UNKNOWN)){
            return UNKNOWN_KEYS;
        }
        return EMPTY_KEYS;
    }

}





