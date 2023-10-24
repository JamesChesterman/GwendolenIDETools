package Grammar;

import Language.psi.GwendolenTypes;
import com.intellij.psi.tree.TokenSet;


public interface GwendolenTokenSets {

    TokenSet ACTION = TokenSet.create(GwendolenTypes.ACTION);
    TokenSet AGENTNAMETERM  = TokenSet.create(GwendolenTypes.AGENTNAMETERM);
    TokenSet CONST_VAR  = TokenSet.create(GwendolenTypes.CONST_VAR);
    TokenSet DEED  = TokenSet.create(GwendolenTypes.DEED);
    TokenSet EVENT  = TokenSet.create(GwendolenTypes.EVENT);
    TokenSet FOF_EXPR  = TokenSet.create(GwendolenTypes.FOF_EXPR);
    TokenSet GLIST  = TokenSet.create(GwendolenTypes.GLIST);
    TokenSet GOAL  = TokenSet.create(GwendolenTypes.GOAL);
    TokenSet GUARD_ATOM  = TokenSet.create(GwendolenTypes.GUARD_ATOM);
    TokenSet GWENDOLENAGENT  = TokenSet.create(GwendolenTypes.GWENDOLENAGENT);
    TokenSet INITIAL_GOAL  = TokenSet.create(GwendolenTypes.INITIAL_GOAL);
    TokenSet OPER  = TokenSet.create(GwendolenTypes.OPER);
    TokenSet PERFORMATIVE  = TokenSet.create(GwendolenTypes.PERFORMATIVE);
    TokenSet PLAN  = TokenSet.create(GwendolenTypes.PLAN);
    TokenSet WAITFOR  = TokenSet.create(GwendolenTypes.WAITFOR);
    TokenSet BELIEFRULES  = TokenSet.create(GwendolenTypes.BELIEFRULES);
    TokenSet BELIEFS  = TokenSet.create(GwendolenTypes.BELIEFS);
    TokenSet BELIEF_BLOCK  = TokenSet.create(GwendolenTypes.BELIEF_BLOCK);
    TokenSet BELIEVE  = TokenSet.create(GwendolenTypes.BELIEVE);
    TokenSet CLOSE  = TokenSet.create(GwendolenTypes.CLOSE);
    TokenSet COLON  = TokenSet.create(GwendolenTypes.COLON);
    TokenSet COMMA  = TokenSet.create(GwendolenTypes.COMMA);
    TokenSet CONST  = TokenSet.create(GwendolenTypes.CONST);
    TokenSet CURLYCLOSE  = TokenSet.create(GwendolenTypes.CURLYCLOSE);
    TokenSet CURLYOPEN  = TokenSet.create(GwendolenTypes.CURLYOPEN);
    TokenSet EQUAL  = TokenSet.create(GwendolenTypes.EQUAL);
    TokenSet GL_ACHIEVEGOAL  = TokenSet.create(GwendolenTypes.GL_ACHIEVEGOAL);
    TokenSet GL_PERFORMGOAL  = TokenSet.create(GwendolenTypes.GL_PERFORMGOAL);
    TokenSet GL_SQCLOSE  = TokenSet.create(GwendolenTypes.GL_SQCLOSE);
    TokenSet GL_SQOPEN  = TokenSet.create(GwendolenTypes.GL_SQOPEN);
    TokenSet GOAL_BLOCK  = TokenSet.create(GwendolenTypes.GOAL_BLOCK);
    TokenSet GOAL_IB  = TokenSet.create(GwendolenTypes.GOAL_IB);
    TokenSet GOAL_RR  = TokenSet.create(GwendolenTypes.GOAL_RR);
    TokenSet GWENDOLEN  = TokenSet.create(GwendolenTypes.GWENDOLEN);
    TokenSet IDPUNCT  = TokenSet.create(GwendolenTypes.IDPUNCT);
    TokenSet LESS  = TokenSet.create(GwendolenTypes.LESS);
    TokenSet LOCK  = TokenSet.create(GwendolenTypes.LOCK);
    TokenSet MINUS  = TokenSet.create(GwendolenTypes.MINUS);
    TokenSet MULT  = TokenSet.create(GwendolenTypes.MULT);
    TokenSet NAME  = TokenSet.create(GwendolenTypes.NAME);
    TokenSet NAME_PM  = TokenSet.create(GwendolenTypes.NAME_PM);
    TokenSet NOT  = TokenSet.create(GwendolenTypes.NOT);
    TokenSet NUMBER  = TokenSet.create(GwendolenTypes.NUMBER);
    TokenSet OPEN  = TokenSet.create(GwendolenTypes.OPEN);
    TokenSet PLANS  = TokenSet.create(GwendolenTypes.PLANS);
    TokenSet PLUS  = TokenSet.create(GwendolenTypes.PLUS);

}
