package Language;

import Language.psi.GwendolenTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.HighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileEditor.FileDocumentManager;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import com.intellij.openapi.project.Project;

import java.util.Arrays;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

public class GwendolenSyntaxHighlighter extends SyntaxHighlighterBase {
    public static final TextAttributesKey GROUP1 =
            createTextAttributesKey("GROUP1", DefaultLanguageHighlighterColors.STATIC_METHOD);
    public static final TextAttributesKey GROUP2 =
            createTextAttributesKey("GROUP2", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey GROUP3 =
            createTextAttributesKey("GROUP3", DefaultLanguageHighlighterColors.FUNCTION_DECLARATION);
    public static final TextAttributesKey GROUP4 =
            createTextAttributesKey("GROUP4", DefaultLanguageHighlighterColors.KEYWORD);
    public static final TextAttributesKey GROUP5 =
            createTextAttributesKey("GROUP5", DefaultLanguageHighlighterColors.MARKUP_ATTRIBUTE);
    public static final TextAttributesKey GROUP6 =
            createTextAttributesKey("GROUP6", DefaultLanguageHighlighterColors.CONSTANT);
    public static final TextAttributesKey GROUP7 =
            createTextAttributesKey("GROUP7", DefaultLanguageHighlighterColors.STRING);
    public static final TextAttributesKey GROUP8 =
            createTextAttributesKey("GROUP8", DefaultLanguageHighlighterColors.NUMBER);
    public static final TextAttributesKey GROUP9 =
            createTextAttributesKey("GROUP9", DefaultLanguageHighlighterColors.METADATA);
    public static final TextAttributesKey BAD_CHARACTER =
            createTextAttributesKey("GWENDOLEN_BAD_CHARACTER", HighlighterColors.BAD_CHARACTER);



    private static final TextAttributesKey[] GROUP1_KEYS = new TextAttributesKey[]{GROUP1};
    private static final TextAttributesKey[] GROUP2_KEYS = new TextAttributesKey[]{GROUP2};
    private static final TextAttributesKey[] GROUP3_KEYS = new TextAttributesKey[]{GROUP3};
    private static final TextAttributesKey[] GROUP4_KEYS = new TextAttributesKey[]{GROUP4};
    private static final TextAttributesKey[] GROUP5_KEYS = new TextAttributesKey[]{GROUP5};
    private static final TextAttributesKey[] GROUP6_KEYS = new TextAttributesKey[]{GROUP6};
    private static final TextAttributesKey[] GROUP7_KEYS = new TextAttributesKey[]{GROUP7};
    private static final TextAttributesKey[] GROUP8_KEYS = new TextAttributesKey[]{GROUP8};
    private static final TextAttributesKey[] GROUP9_KEYS = new TextAttributesKey[]{GROUP9};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};

    private static final TextAttributesKey[] EMPTY_KEYS = new TextAttributesKey[0];

    @NotNull
    @Override
    public Lexer getHighlightingLexer(){
        return new GwendolenLexerAdapter();
    }

    @Override
    public TextAttributesKey @NotNull [] getTokenHighlights(IElementType tokenType){
        IElementType[] group1Types = {GwendolenTypes.GWENDOLEN, GwendolenTypes.NAME, GwendolenTypes.BELIEFS,
                GwendolenTypes.BELIEFRULES, GwendolenTypes.GOAL_IB, GwendolenTypes.GOAL_RR, GwendolenTypes.PLANS,
                GwendolenTypes.NAME_PM};
        IElementType[] group2Types = {GwendolenTypes.COMMENT, GwendolenTypes.LINE_COMMENT, GwendolenTypes.IB_COMMENT};
        IElementType[] group3Types = {GwendolenTypes.PL_BAR, GwendolenTypes.NOT, GwendolenTypes.COLON,
                GwendolenTypes.COMMA, GwendolenTypes.SEMI, GwendolenTypes.SHRIEK, GwendolenTypes.MULT,
                GwendolenTypes.PLUS, GwendolenTypes.MINUS, GwendolenTypes.LESS, GwendolenTypes.EQUAL,
                GwendolenTypes.IDPUNCT, GwendolenTypes.RULEARROW};
        IElementType[] group4Types = {GwendolenTypes.GL_ACHIEVEGOAL, GwendolenTypes.GL_PERFORMGOAL,
                GwendolenTypes.SEND, GwendolenTypes.RECEIVED, GwendolenTypes.SENT, GwendolenTypes.LOCK,
                GwendolenTypes.BELIEVE, GwendolenTypes.GOAL, GwendolenTypes.PL_ACHIEVEGOAL,
                GwendolenTypes.PL_PERFORMGOAL, GwendolenTypes.TELL};
        IElementType[] group5Types = {GwendolenTypes.BELIEF_BLOCK, GwendolenTypes.RR_BLOCK, GwendolenTypes.GOAL_BLOCK};
        IElementType[] group6Types = {GwendolenTypes.CONST, GwendolenTypes.PL_CONST, GwendolenTypes.PL_VAR,
                GwendolenTypes.TRUE};
        IElementType[] group7Types = {GwendolenTypes.QUOTED_STRING};
        IElementType[] group8Types = {GwendolenTypes.NUMBER};
        IElementType[] group9Types = {GwendolenTypes.GL_SQOPEN, GwendolenTypes.GL_SQCLOSE, GwendolenTypes.PL_SQOPEN,
                GwendolenTypes.PL_SQCLOSE, GwendolenTypes.CURLYOPEN, GwendolenTypes.CURLYCLOSE, GwendolenTypes.OPEN,
                GwendolenTypes.CLOSE};
        if(Arrays.asList(group1Types).contains(tokenType)){
            return GROUP1_KEYS;
        }
        if(Arrays.asList(group2Types).contains(tokenType)){
            return GROUP2_KEYS;
        }
        if(Arrays.asList(group3Types).contains(tokenType)){
            return GROUP3_KEYS;
        }
        if(Arrays.asList(group4Types).contains(tokenType)){
            return GROUP4_KEYS;
        }
        if(Arrays.asList(group5Types).contains(tokenType)){
            return GROUP5_KEYS;
        }
        if(Arrays.asList(group6Types).contains(tokenType)){
            return GROUP6_KEYS;
        }
        if(Arrays.asList(group7Types).contains(tokenType)){
            return GROUP7_KEYS;
        }
        if(Arrays.asList(group8Types).contains(tokenType)){
            return GROUP8_KEYS;
        }
        if(Arrays.asList(group9Types).contains(tokenType)){
            return GROUP9_KEYS;
        }
        if(tokenType.equals(TokenType.BAD_CHARACTER)){
            return BAD_CHAR_KEYS;
        }
        return EMPTY_KEYS;
    }

}







