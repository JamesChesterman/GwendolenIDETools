package psi;

import Grammar.GwendolenElementType;
import com.intellij.mock.MockPsiElement;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;

public interface GwendolenTypes {

    /*
    private static final String[] _SYMBOLIC_NAMES = {
		null, "GWENDOLEN", "NAME", "CONST", "COMMENT", "LINE_COMMENT", "NEWLINE",
		"WS", "BELIEFS", "BELIEFRULES", "GOAL_IB", "IB_COMMENT", "IB_LINE_COMMENT",
		"IB_NEWLINE", "IB_WS", "BELIEF_BLOCK", "GOAL_RR", "RR_COMMENT", "RR_LINE_COMMENT",
		"RR_NEWLINE", "RR_WS", "RR_BLOCK", "PLANS", "GL_COMMENT", "GL_LINE_COMMENT",
		"GL_NEWLINE", "GL_WS", "GL_ACHIEVEGOAL", "GL_PERFORMGOAL", "GL_SQOPEN",
		"GL_SQCLOSE", "GOAL_BLOCK", "NAME_PM", "PL_COMMENT", "PL_LINE_COMMENT",
		"PL_NEWLINE", "PL_WS", "SEND", "RECEIVED", "BELIEVE", "GOAL", "SENT",
		"LOCK", "PL_ACHIEVEGOAL", "PL_PERFORMGOAL", "PL_SQOPEN", "PL_SQCLOSE",
		"PL_BAR", "NOT", "COLON", "CURLYOPEN", "CURLYCLOSE", "COMMA", "SEMI",
		"TELL", "SHRIEK", "OPEN", "CLOSE", "MULT", "PLUS", "MINUS", "LESS", "EQUAL",
		"IDPUNCT", "RULEARROW", "TRUE", "PL_CONST", "PL_VAR", "NUMBER", "QUOTED_STRING"
	};

     */

    IElementType GWENDOLEN = new GwendolenElementType("GWENDOLEN");
    IElementType COMMENT = new GwendolenElementType("COMMENT");
    IElementType LINE_COMMENT = new GwendolenElementType("LINE_COMMENT");

    IElementType UNKNOWN = new GwendolenElementType("UNKNOWN");

    /*
    class Factory{
        public static PsiElement createElement(ASTNode node){
            try{
                IElementType type = node.getElementType();
                PsiElement psiElement = new MockPsiElement();

            }catch(AssertionError e){
                e.printStackTrace();
            }
        }
    }

     */


}
