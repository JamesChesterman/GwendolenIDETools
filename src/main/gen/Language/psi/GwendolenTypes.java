// This is a generated file. Not intended for manual editing.
package Language.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import Language.psi.impl.*;
import Grammar.GwendolenElementType;
import Grammar.GwendolenTokenType;

public interface GwendolenTypes {

  IElementType ACTION = new GwendolenElementType("ACTION");
  IElementType AGENTNAMETERM = new GwendolenElementType("AGENTNAMETERM");
  IElementType CONST_VAR = new GwendolenElementType("CONST_VAR");
  IElementType DEED = new GwendolenElementType("DEED");
  IElementType EVENT = new GwendolenElementType("EVENT");
  IElementType FOF_EXPR = new GwendolenElementType("FOF_EXPR");
  IElementType GLIST = new GwendolenElementType("GLIST");
  IElementType GOAL = new GwendolenElementType("GOAL");
  IElementType GUARD_ATOM = new GwendolenElementType("GUARD_ATOM");
  IElementType GWENDOLENAGENT = new GwendolenElementType("GWENDOLENAGENT");
  IElementType INITIAL_GOAL = new GwendolenElementType("INITIAL_GOAL");
  IElementType OPER = new GwendolenElementType("OPER");
  IElementType PERFORMATIVE = new GwendolenElementType("PERFORMATIVE");
  IElementType PLAN = new GwendolenElementType("PLAN");
  IElementType WAITFOR = new GwendolenElementType("WAITFOR");

  IElementType BELIEFRULES = new GwendolenTokenType("BELIEFRULES");
  IElementType BELIEFS = new GwendolenTokenType("BELIEFS");
  IElementType BELIEF_BLOCK = new GwendolenTokenType("BELIEF_BLOCK");
  IElementType BELIEVE = new GwendolenTokenType("BELIEVE");
  IElementType CLOSE = new GwendolenTokenType("CLOSE");
  IElementType COLON = new GwendolenTokenType("COLON");
  IElementType COMMA = new GwendolenTokenType("COMMA");
  IElementType CONST = new GwendolenTokenType("CONST");
  IElementType CURLYCLOSE = new GwendolenTokenType("CURLYCLOSE");
  IElementType CURLYOPEN = new GwendolenTokenType("CURLYOPEN");
  IElementType EQUAL = new GwendolenTokenType("EQUAL");
  IElementType GL_ACHIEVEGOAL = new GwendolenTokenType("GL_ACHIEVEGOAL");
  IElementType GL_PERFORMGOAL = new GwendolenTokenType("GL_PERFORMGOAL");
  IElementType GL_SQCLOSE = new GwendolenTokenType("GL_SQCLOSE");
  IElementType GL_SQOPEN = new GwendolenTokenType("GL_SQOPEN");
  IElementType GOAL_BLOCK = new GwendolenTokenType("GOAL_BLOCK");
  IElementType GOAL_IB = new GwendolenTokenType("GOAL_IB");
  IElementType GOAL_RR = new GwendolenTokenType("GOAL_RR");
  IElementType GWENDOLEN = new GwendolenTokenType("GWENDOLEN");
  IElementType IDPUNCT = new GwendolenTokenType("IDPUNCT");
  IElementType LESS = new GwendolenTokenType("LESS");
  IElementType LOCK = new GwendolenTokenType("LOCK");
  IElementType MINUS = new GwendolenTokenType("MINUS");
  IElementType MULT = new GwendolenTokenType("MULT");
  IElementType NAME = new GwendolenTokenType("NAME");
  IElementType NAME_PM = new GwendolenTokenType("NAME_PM");
  IElementType NOT = new GwendolenTokenType("NOT");
  IElementType NUMBER = new GwendolenTokenType("NUMBER");
  IElementType OPEN = new GwendolenTokenType("OPEN");
  IElementType PLANS = new GwendolenTokenType("PLANS");
  IElementType PLUS = new GwendolenTokenType("PLUS");
  IElementType PL_ACHIEVEGOAL = new GwendolenTokenType("PL_ACHIEVEGOAL");
  IElementType PL_BAR = new GwendolenTokenType("PL_BAR");
  IElementType PL_CONST = new GwendolenTokenType("PL_CONST");
  IElementType PL_PERFORMGOAL = new GwendolenTokenType("PL_PERFORMGOAL");
  IElementType PL_SQCLOSE = new GwendolenTokenType("PL_SQCLOSE");
  IElementType PL_SQOPEN = new GwendolenTokenType("PL_SQOPEN");
  IElementType PL_VAR = new GwendolenTokenType("PL_VAR");
  IElementType QUOTED_STRING = new GwendolenTokenType("QUOTED_STRING");
  IElementType RECEIVED = new GwendolenTokenType("RECEIVED");
  IElementType RR_BLOCK = new GwendolenTokenType("RR_BLOCK");
  IElementType RR_NEWLINE = new GwendolenTokenType("RR_NEWLINE");
  IElementType RULEARROW = new GwendolenTokenType("RULEARROW");
  IElementType SEMI = new GwendolenTokenType("SEMI");
  IElementType SEND = new GwendolenTokenType("SEND");
  IElementType SENT = new GwendolenTokenType("SENT");
  IElementType SHRIEK = new GwendolenTokenType("SHRIEK");
  IElementType TELL = new GwendolenTokenType("TELL");
  IElementType TRUE = new GwendolenTokenType("TRUE");
  IElementType UNKNOWN = new GwendolenTokenType("UNKNOWN");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ACTION) {
        return new GwendolenActionImpl(node);
      }
      else if (type == AGENTNAMETERM) {
        return new GwendolenAgentnametermImpl(node);
      }
      else if (type == CONST_VAR) {
        return new GwendolenConstVarImpl(node);
      }
      else if (type == DEED) {
        return new GwendolenDeedImpl(node);
      }
      else if (type == EVENT) {
        return new GwendolenEventImpl(node);
      }
      else if (type == FOF_EXPR) {
        return new GwendolenFofExprImpl(node);
      }
      else if (type == GLIST) {
        return new GwendolenGlistImpl(node);
      }
      else if (type == GOAL) {
        return new GwendolenGoalImpl(node);
      }
      else if (type == GUARD_ATOM) {
        return new GwendolenGuardAtomImpl(node);
      }
      else if (type == GWENDOLENAGENT) {
        return new GwendolenGwendolenagentImpl(node);
      }
      else if (type == INITIAL_GOAL) {
        return new GwendolenInitialGoalImpl(node);
      }
      else if (type == OPER) {
        return new GwendolenOperImpl(node);
      }
      else if (type == PERFORMATIVE) {
        return new GwendolenPerformativeImpl(node);
      }
      else if (type == PLAN) {
        return new GwendolenPlanImpl(node);
      }
      else if (type == WAITFOR) {
        return new GwendolenWaitforImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
