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
    public static final TextAttributesKey ACTION =
            createTextAttributesKey("GWENDOLEN_ACTION", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey AGENTNAMETERM =
            createTextAttributesKey("GWENDOLEN_AGENTNAMETERM", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey CONST_VAR =
            createTextAttributesKey("GWENDOLEN_CONST_VAR", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey COMMENT =
            createTextAttributesKey("GWENDOLEN_COMMENT", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey DEED =
            createTextAttributesKey("GWENDOLEN_DEED", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey EVENT =
            createTextAttributesKey("GWENDOLEN_EVENT", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey FOF_EXPR =
            createTextAttributesKey("GWENDOLEN_FOF_EXPR", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey GLIST =
            createTextAttributesKey("GWENDOLEN_GLIST", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey GOAL =
            createTextAttributesKey("GWENDOLEN_GOAL", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey GUARD_ATOM =
            createTextAttributesKey("GWENDOLEN_GUARD_ATOM", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey GWENDOLENAGENT =
            createTextAttributesKey("GWENDOLEN_GWENDOLENAGENT", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey INITIAL_GOAL =
            createTextAttributesKey("GWENDOLEN_INITIAL_GOAL", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey OPER =
            createTextAttributesKey("GWENDOLEN_OPER", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey PERFORMATIVE =
            createTextAttributesKey("GWENDOLEN_PERFORMATIVE", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey PLAN =
            createTextAttributesKey("GWENDOLEN_PLAN", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey WAITFOR =
            createTextAttributesKey("GWENDOLEN_WAITFOR", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey BELIEFRULES =
            createTextAttributesKey("GWENDOLEN_BELIEFRULES", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey BELIEFS =
            createTextAttributesKey("GWENDOLEN_BELIEFS", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey BELIEF_BLOCK =
            createTextAttributesKey("GWENDOLEN_BELIEF_BLOCK", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey BELIEVE =
            createTextAttributesKey("GWENDOLEN_BELIEVE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey CLOSE =
            createTextAttributesKey("GWENDOLEN_CLOSE", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey COLON =
            createTextAttributesKey("GWENDOLEN_COLON", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey COMMA =
            createTextAttributesKey("GWENDOLEN_COMMA", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey CONST =
            createTextAttributesKey("GWENDOLEN_CONST", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey CURLYCLOSE =
            createTextAttributesKey("GWENDOLEN_CURLYCLOSE", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey CURLYOPEN =
            createTextAttributesKey("GWENDOLEN_CURLYOPEN", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey EQUAL =
            createTextAttributesKey("GWENDOLEN_EQUAL", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey GL_ACHIEVEGOAL =
            createTextAttributesKey("GWENDOLEN_GL_ACHIEVEGOAL", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey GL_PERFORMGOAL =
            createTextAttributesKey("GWENDOLEN_GL_PERFORMGOAL", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey GL_SQCLOSE =
            createTextAttributesKey("GWENDOLEN_GL_SQCLOSE", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey GL_SQOPEN =
            createTextAttributesKey("GWENDOLEN_GL_SQOPEN", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey GOAL_BLOCK =
            createTextAttributesKey("GWENDOLEN_GOAL_BLOCK", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey GOAL_IB =
            createTextAttributesKey("GWENDOLEN_GOAL_IB", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey GOAL_RR =
            createTextAttributesKey("GWENDOLEN_GOAL_RR", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey GWENDOLEN =
            createTextAttributesKey("GWENDOLEN_GWENDOLEN", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey IDPUNCT =
            createTextAttributesKey("GWENDOLEN_IDPUNCT", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey LESS =
            createTextAttributesKey("GWENDOLEN_LESS", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_LINE_COMMENT", DefaultLanguageHighlighterColors.LABEL);
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
    public static final TextAttributesKey NEWLINE =
            createTextAttributesKey("GWENDOLEN_NEWLINE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey NOT =
            createTextAttributesKey("GWENDOLEN_NOT", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey NUMBER =
            createTextAttributesKey("GWENDOLEN_NUMBER", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey OPEN =
            createTextAttributesKey("GWENDOLEN_OPEN", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey PLANS =
            createTextAttributesKey("GWENDOLEN_PLANS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey PLUS =
            createTextAttributesKey("GWENDOLEN_PLUS", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey PL_ACHIEVEGOAL =
            createTextAttributesKey("GWENDOLEN_PL_ACHIEVEGOAL", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey PL_BAR =
            createTextAttributesKey("GWENDOLEN_PL_BAR", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PL_CONST =
            createTextAttributesKey("GWENDOLEN_PL_CONST", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey PL_PERFORMGOAL =
            createTextAttributesKey("GWENDOLEN_PL_PERFORMGOAL", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey PL_SQCLOSE =
            createTextAttributesKey("GWENDOLEN_PL_SQCLOSE", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey PL_SQOPEN =
            createTextAttributesKey("GWENDOLEN_PL_SQOPEN", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey PL_VAR =
            createTextAttributesKey("GWENDOLEN_PL_VAR", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey QUOTED_STRING =
            createTextAttributesKey("GWENDOLEN_QUOTED_STRING", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey RECEIVED =
            createTextAttributesKey("GWENDOLEN_RECEIVED", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey RR_BLOCK =
            createTextAttributesKey("GWENDOLEN_RR_BLOCK", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey RR_NEWLINE =
            createTextAttributesKey("GWENDOLEN_RR_NEWLINE", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey RULEARROW =
            createTextAttributesKey("GWENDOLEN_RULEARROW", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey SEMI =
            createTextAttributesKey("GWENDOLEN_SEMI", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey SEND =
            createTextAttributesKey("GWENDOLEN_SEND", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey SENT =
            createTextAttributesKey("GWENDOLEN_SENT", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey SHRIEK =
            createTextAttributesKey("GWENDOLEN_SHRIEK", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey TELL =
            createTextAttributesKey("GWENDOLEN_TELL", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey TRUE =
            createTextAttributesKey("GWENDOLEN_TRUE", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey UNKNOWN =
            createTextAttributesKey("GWENDOLEN_UNKNOWN", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey WS =
            createTextAttributesKey("GWENDOLEN_WS", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey IB_COMMENT =
            createTextAttributesKey("GWENDOLEN_IB_COMMENT", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey IB_LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_IB_LINE_COMMENT", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey IB_NEWLINE =
            createTextAttributesKey("GWENDOLEN_IB_NEWLINE", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey IB_WS =
            createTextAttributesKey("GWENDOLEN_IB_WS", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey RR_COMMENT =
            createTextAttributesKey("GWENDOLEN_RR_COMMENT", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey RR_LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_RR_LINE_COMMENT", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey RR_WS =
            createTextAttributesKey("GWENDOLEN_RR_WS", DefaultLanguageHighlighterColors.CLASS_NAME);
    public static final TextAttributesKey GL_COMMENT =
            createTextAttributesKey("GWENDOLEN_GL_COMMENT", DefaultLanguageHighlighterColors.SEMICOLON);
    public static final TextAttributesKey GL_LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_GL_LINE_COMMENT", DefaultLanguageHighlighterColors.LABEL);
    public static final TextAttributesKey GL_NEWLINE =
            createTextAttributesKey("GWENDOLEN_GL_NEWLINE", DefaultLanguageHighlighterColors.PARAMETER);
    public static final TextAttributesKey GL_WS =
            createTextAttributesKey("GWENDOLEN_GL_WS", DefaultLanguageHighlighterColors.PARENTHESES);
    public static final TextAttributesKey PL_COMMENT =
            createTextAttributesKey("GWENDOLEN_PL_COMMENT", DefaultLanguageHighlighterColors.OPERATION_SIGN);
    public static final TextAttributesKey PL_LINE_COMMENT =
            createTextAttributesKey("GWENDOLEN_PL_LINE_COMMENT", DefaultLanguageHighlighterColors.BRACKETS);
    public static final TextAttributesKey PL_NEWLINE =
            createTextAttributesKey("GWENDOLEN_PL_NEWLINE", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey PL_WS =
            createTextAttributesKey("GWENDOLEN_PL_WS", DefaultLanguageHighlighterColors.CLASS_NAME);




    private static final TextAttributesKey[] ACTION_KEYS = new TextAttributesKey[]{ACTION};
    private static final TextAttributesKey[] AGENTNAMETERM_KEYS = new TextAttributesKey[]{AGENTNAMETERM};
    private static final TextAttributesKey[] CONST_VAR_KEYS = new TextAttributesKey[]{CONST_VAR};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] DEED_KEYS = new TextAttributesKey[]{DEED};
    private static final TextAttributesKey[] EVENT_KEYS = new TextAttributesKey[]{EVENT};
    private static final TextAttributesKey[] FOF_EXPR_KEYS = new TextAttributesKey[]{FOF_EXPR};
    private static final TextAttributesKey[] GLIST_KEYS = new TextAttributesKey[]{GLIST};
    private static final TextAttributesKey[] GOAL_KEYS = new TextAttributesKey[]{GOAL};
    private static final TextAttributesKey[] GUARD_ATOM_KEYS = new TextAttributesKey[]{GUARD_ATOM};
    private static final TextAttributesKey[] GWENDOLENAGENT_KEYS = new TextAttributesKey[]{GWENDOLENAGENT};
    private static final TextAttributesKey[] INITIAL_GOAL_KEYS = new TextAttributesKey[]{INITIAL_GOAL};
    private static final TextAttributesKey[] OPER_KEYS = new TextAttributesKey[]{OPER};
    private static final TextAttributesKey[] PERFORMATIVE_KEYS = new TextAttributesKey[]{PERFORMATIVE};
    private static final TextAttributesKey[] PLAN_KEYS = new TextAttributesKey[]{PLAN};
    private static final TextAttributesKey[] WAITFOR_KEYS = new TextAttributesKey[]{WAITFOR};
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
    private static final TextAttributesKey[] LINE_COMMENT_KEYS = new TextAttributesKey[]{LINE_COMMENT};
    private static final TextAttributesKey[] LOCK_KEYS = new TextAttributesKey[]{LOCK};
    private static final TextAttributesKey[] MINUS_KEYS = new TextAttributesKey[]{MINUS};
    private static final TextAttributesKey[] MULT_KEYS = new TextAttributesKey[]{MULT};
    private static final TextAttributesKey[] NAME_KEYS = new TextAttributesKey[]{NAME};
    private static final TextAttributesKey[] NAME_PM_KEYS = new TextAttributesKey[]{NAME_PM};
    private static final TextAttributesKey[] NEWLINE_KEYS = new TextAttributesKey[]{NEWLINE};
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
    private static final TextAttributesKey[] WS_KEYS = new TextAttributesKey[]{WS};
    private static final TextAttributesKey[] IB_COMMENT_KEYS = new TextAttributesKey[]{IB_COMMENT};
    private static final TextAttributesKey[] IB_LINE_COMMENT_KEYS = new TextAttributesKey[]{IB_LINE_COMMENT};
    private static final TextAttributesKey[] IB_NEWLINE_KEYS = new TextAttributesKey[]{IB_NEWLINE};
    private static final TextAttributesKey[] IB_WS_KEYS = new TextAttributesKey[]{IB_WS};
    private static final TextAttributesKey[] RR_COMMENT_KEYS = new TextAttributesKey[]{RR_COMMENT};
    private static final TextAttributesKey[] RR_LINE_COMMENT_KEYS = new TextAttributesKey[]{RR_LINE_COMMENT};
    private static final TextAttributesKey[] RR_WS_KEYS = new TextAttributesKey[]{RR_WS};
    private static final TextAttributesKey[] GL_COMMENT_KEYS = new TextAttributesKey[]{GL_COMMENT};
    private static final TextAttributesKey[] GL_LINE_COMMENT_KEYS = new TextAttributesKey[]{GL_LINE_COMMENT};
    private static final TextAttributesKey[] GL_NEWLINE_KEYS = new TextAttributesKey[]{GL_NEWLINE};
    private static final TextAttributesKey[] GL_WS_KEYS = new TextAttributesKey[]{GL_WS};
    private static final TextAttributesKey[] PL_COMMENT_KEYS = new TextAttributesKey[]{PL_COMMENT};
    private static final TextAttributesKey[] PL_LINE_COMMENT_KEYS = new TextAttributesKey[]{PL_LINE_COMMENT};
    private static final TextAttributesKey[] PL_NEWLINE_KEYS = new TextAttributesKey[]{PL_NEWLINE};
    private static final TextAttributesKey[] PL_WS_KEYS = new TextAttributesKey[]{PL_WS};
    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer(){
        return new GwendolenLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType){
        if(tokenType.equals(GwendolenTypes.ACTION)){
            return ACTION_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.AGENTNAMETERM)){
            return AGENTNAMETERM_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.CONST_VAR)){
            return CONST_VAR_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.COMMENT)){
            return COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.DEED)){
            return DEED_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.EVENT)){
            return EVENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.FOF_EXPR)){
            return FOF_EXPR_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GLIST)){
            return GLIST_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GOAL)){
            return GOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GUARD_ATOM)){
            return GUARD_ATOM_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GWENDOLENAGENT)){
            return GWENDOLENAGENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.INITIAL_GOAL)){
            return INITIAL_GOAL_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.OPER)){
            return OPER_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PERFORMATIVE)){
            return PERFORMATIVE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PLAN)){
            return PLAN_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.WAITFOR)){
            return WAITFOR_KEYS;
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
        if(tokenType.equals(GwendolenTypes.LINE_COMMENT)){
            return LINE_COMMENT_KEYS;
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
        if(tokenType.equals(GwendolenTypes.NEWLINE)){
            return NEWLINE_KEYS;
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
        if(tokenType.equals(GwendolenTypes.WS)){
            return WS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.IB_COMMENT)){
            return IB_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.IB_LINE_COMMENT)){
            return IB_LINE_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.IB_NEWLINE)){
            return IB_NEWLINE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.IB_WS)){
            return IB_WS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RR_COMMENT)){
            return RR_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RR_LINE_COMMENT)){
            return RR_LINE_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.RR_WS)){
            return RR_WS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_COMMENT)){
            return GL_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_LINE_COMMENT)){
            return GL_LINE_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_NEWLINE)){
            return GL_NEWLINE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.GL_WS)){
            return GL_WS_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_COMMENT)){
            return PL_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_LINE_COMMENT)){
            return PL_LINE_COMMENT_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_NEWLINE)){
            return PL_NEWLINE_KEYS;
        }
        if(tokenType.equals(GwendolenTypes.PL_WS)){
            return PL_WS_KEYS;
        }
        return EMPTY_KEYS;
    }

}







