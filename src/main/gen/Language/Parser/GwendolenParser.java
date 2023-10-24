// This is a generated file. Not intended for manual editing.
package Language.Parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static Language.psi.GwendolenTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class GwendolenParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {

    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return mas(b, l + 1);
  }

  /* ********************************************************** */
  // (SEND OPEN fof_expr COMMA performative COMMA fof_expr CLOSE) |
  //     fof_expr
  public static boolean action(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ACTION, "<action>");
    r = action_0(b, l + 1);
    if (!r) r = fof_expr(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // SEND OPEN fof_expr COMMA performative COMMA fof_expr CLOSE
  private static boolean action_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "action_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SEND, OPEN);
    r = r && fof_expr(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && performative(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && fof_expr(b, l + 1);
    r = r && consumeToken(b, CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // PL_CONST | PL_VAR
  public static boolean agentnameterm(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "agentnameterm")) return false;
    if (!nextTokenIs(b, "<agentnameterm>", PL_CONST, PL_VAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, AGENTNAMETERM, "<agentnameterm>");
    r = consumeToken(b, PL_CONST);
    if (!r) r = consumeToken(b, PL_VAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // PL_CONST | PL_VAR
  public static boolean const_var(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "const_var")) return false;
    if (!nextTokenIs(b, "<const var>", PL_CONST, PL_VAR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, CONST_VAR, "<const var>");
    r = consumeToken(b, PL_CONST);
    if (!r) r = consumeToken(b, PL_VAR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // ((PLUS (fof_expr | SHRIEK goal | LOCK))
  //                 |
  //             (MINUS (fof_expr | SHRIEK goal | LOCK)))
  //                 |
  //             action |
  //             waitfor
  public static boolean deed(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, DEED, "<deed>");
    r = deed_0(b, l + 1);
    if (!r) r = action(b, l + 1);
    if (!r) r = waitfor(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (PLUS (fof_expr | SHRIEK goal | LOCK))
  //                 |
  //             (MINUS (fof_expr | SHRIEK goal | LOCK))
  private static boolean deed_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = deed_0_0(b, l + 1);
    if (!r) r = deed_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // PLUS (fof_expr | SHRIEK goal | LOCK)
  private static boolean deed_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && deed_0_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | SHRIEK goal | LOCK
  private static boolean deed_0_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr(b, l + 1);
    if (!r) r = deed_0_0_1_1(b, l + 1);
    if (!r) r = consumeToken(b, LOCK);
    exit_section_(b, m, null, r);
    return r;
  }

  // SHRIEK goal
  private static boolean deed_0_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SHRIEK);
    r = r && goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MINUS (fof_expr | SHRIEK goal | LOCK)
  private static boolean deed_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    r = r && deed_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | SHRIEK goal | LOCK
  private static boolean deed_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr(b, l + 1);
    if (!r) r = deed_0_1_1_1(b, l + 1);
    if (!r) r = consumeToken(b, LOCK);
    exit_section_(b, m, null, r);
    return r;
  }

  // SHRIEK goal
  private static boolean deed_0_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "deed_0_1_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SHRIEK);
    r = r && goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (PLUS
  //                 (RECEIVED OPEN performative COMMA fof_expr CLOSE |
  //                     (fof_expr | SHRIEK goal)
  //                 )
  //             ) |
  //             (MINUS
  //                 (fof_expr | SHRIEK goal)
  //             )
  public static boolean event(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event")) return false;
    if (!nextTokenIs(b, "<event>", MINUS, PLUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EVENT, "<event>");
    r = event_0(b, l + 1);
    if (!r) r = event_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PLUS
  //                 (RECEIVED OPEN performative COMMA fof_expr CLOSE |
  //                     (fof_expr | SHRIEK goal)
  //                 )
  private static boolean event_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PLUS);
    r = r && event_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RECEIVED OPEN performative COMMA fof_expr CLOSE |
  //                     (fof_expr | SHRIEK goal)
  private static boolean event_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = event_0_1_0(b, l + 1);
    if (!r) r = event_0_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // RECEIVED OPEN performative COMMA fof_expr CLOSE
  private static boolean event_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, RECEIVED, OPEN);
    r = r && performative(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && fof_expr(b, l + 1);
    r = r && consumeToken(b, CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | SHRIEK goal
  private static boolean event_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr(b, l + 1);
    if (!r) r = event_0_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SHRIEK goal
  private static boolean event_0_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_0_1_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SHRIEK);
    r = r && goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // MINUS
  //                 (fof_expr | SHRIEK goal)
  private static boolean event_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MINUS);
    r = r && event_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | SHRIEK goal
  private static boolean event_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr(b, l + 1);
    if (!r) r = event_1_1_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SHRIEK goal
  private static boolean event_1_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "event_1_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SHRIEK);
    r = r && goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // ((MINUS)? NUMBER | PL_VAR) (oper ((MINUS)? NUMBER | PL_VAR))? |
  //             (const_var (IDPUNCT const_var)* (OPEN (fof_expr | QUOTED_STRING) (COMMA (fof_expr | QUOTED_STRING))* CLOSE)?) |
  //             PL_SQOPEN (fof_expr (COMMA fof_expr)* (PL_BAR PL_VAR)?)? PL_SQCLOSE |
  //             OPEN fof_expr CLOSE
  public static boolean fof_expr(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FOF_EXPR, "<fof expr>");
    r = fof_expr_0(b, l + 1);
    if (!r) r = fof_expr_1(b, l + 1);
    if (!r) r = fof_expr_2(b, l + 1);
    if (!r) r = fof_expr_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ((MINUS)? NUMBER | PL_VAR) (oper ((MINUS)? NUMBER | PL_VAR))?
  private static boolean fof_expr_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr_0_0(b, l + 1);
    r = r && fof_expr_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)? NUMBER | PL_VAR
  private static boolean fof_expr_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr_0_0_0(b, l + 1);
    if (!r) r = consumeToken(b, PL_VAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)? NUMBER
  private static boolean fof_expr_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr_0_0_0_0(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)?
  private static boolean fof_expr_0_0_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_0_0_0")) return false;
    consumeToken(b, MINUS);
    return true;
  }

  // (oper ((MINUS)? NUMBER | PL_VAR))?
  private static boolean fof_expr_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_1")) return false;
    fof_expr_0_1_0(b, l + 1);
    return true;
  }

  // oper ((MINUS)? NUMBER | PL_VAR)
  private static boolean fof_expr_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = oper(b, l + 1);
    r = r && fof_expr_0_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)? NUMBER | PL_VAR
  private static boolean fof_expr_0_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr_0_1_0_1_0(b, l + 1);
    if (!r) r = consumeToken(b, PL_VAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)? NUMBER
  private static boolean fof_expr_0_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr_0_1_0_1_0_0(b, l + 1);
    r = r && consumeToken(b, NUMBER);
    exit_section_(b, m, null, r);
    return r;
  }

  // (MINUS)?
  private static boolean fof_expr_0_1_0_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_0_1_0_1_0_0")) return false;
    consumeToken(b, MINUS);
    return true;
  }

  // const_var (IDPUNCT const_var)* (OPEN (fof_expr | QUOTED_STRING) (COMMA (fof_expr | QUOTED_STRING))* CLOSE)?
  private static boolean fof_expr_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = const_var(b, l + 1);
    r = r && fof_expr_1_1(b, l + 1);
    r = r && fof_expr_1_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (IDPUNCT const_var)*
  private static boolean fof_expr_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fof_expr_1_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fof_expr_1_1", c)) break;
    }
    return true;
  }

  // IDPUNCT const_var
  private static boolean fof_expr_1_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDPUNCT);
    r = r && const_var(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (OPEN (fof_expr | QUOTED_STRING) (COMMA (fof_expr | QUOTED_STRING))* CLOSE)?
  private static boolean fof_expr_1_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2")) return false;
    fof_expr_1_2_0(b, l + 1);
    return true;
  }

  // OPEN (fof_expr | QUOTED_STRING) (COMMA (fof_expr | QUOTED_STRING))* CLOSE
  private static boolean fof_expr_1_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN);
    r = r && fof_expr_1_2_0_1(b, l + 1);
    r = r && fof_expr_1_2_0_2(b, l + 1);
    r = r && consumeToken(b, CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | QUOTED_STRING
  private static boolean fof_expr_1_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2_0_1")) return false;
    boolean r;
    r = fof_expr(b, l + 1);
    if (!r) r = consumeToken(b, QUOTED_STRING);
    return r;
  }

  // (COMMA (fof_expr | QUOTED_STRING))*
  private static boolean fof_expr_1_2_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fof_expr_1_2_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fof_expr_1_2_0_2", c)) break;
    }
    return true;
  }

  // COMMA (fof_expr | QUOTED_STRING)
  private static boolean fof_expr_1_2_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && fof_expr_1_2_0_2_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // fof_expr | QUOTED_STRING
  private static boolean fof_expr_1_2_0_2_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_1_2_0_2_0_1")) return false;
    boolean r;
    r = fof_expr(b, l + 1);
    if (!r) r = consumeToken(b, QUOTED_STRING);
    return r;
  }

  // PL_SQOPEN (fof_expr (COMMA fof_expr)* (PL_BAR PL_VAR)?)? PL_SQCLOSE
  private static boolean fof_expr_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PL_SQOPEN);
    r = r && fof_expr_2_1(b, l + 1);
    r = r && consumeToken(b, PL_SQCLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (fof_expr (COMMA fof_expr)* (PL_BAR PL_VAR)?)?
  private static boolean fof_expr_2_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1")) return false;
    fof_expr_2_1_0(b, l + 1);
    return true;
  }

  // fof_expr (COMMA fof_expr)* (PL_BAR PL_VAR)?
  private static boolean fof_expr_2_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = fof_expr(b, l + 1);
    r = r && fof_expr_2_1_0_1(b, l + 1);
    r = r && fof_expr_2_1_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA fof_expr)*
  private static boolean fof_expr_2_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!fof_expr_2_1_0_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "fof_expr_2_1_0_1", c)) break;
    }
    return true;
  }

  // COMMA fof_expr
  private static boolean fof_expr_2_1_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && fof_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (PL_BAR PL_VAR)?
  private static boolean fof_expr_2_1_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1_0_2")) return false;
    fof_expr_2_1_0_2_0(b, l + 1);
    return true;
  }

  // PL_BAR PL_VAR
  private static boolean fof_expr_2_1_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_2_1_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, PL_BAR, PL_VAR);
    exit_section_(b, m, null, r);
    return r;
  }

  // OPEN fof_expr CLOSE
  private static boolean fof_expr_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "fof_expr_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPEN);
    r = r && fof_expr(b, l + 1);
    r = r && consumeToken(b, CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GWENDOLEN gwendolenagent+
  public static boolean glist(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "glist")) return false;
    if (!nextTokenIs(b, GWENDOLEN)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GWENDOLEN);
    r = r && glist_1(b, l + 1);
    exit_section_(b, m, GLIST, r);
    return r;
  }

  // gwendolenagent+
  private static boolean glist_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "glist_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = gwendolenagent(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!gwendolenagent(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "glist_1", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // fof_expr PL_SQOPEN (PL_ACHIEVEGOAL | PL_PERFORMGOAL) PL_SQCLOSE
  public static boolean goal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "goal")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GOAL, "<goal>");
    r = fof_expr(b, l + 1);
    r = r && consumeToken(b, PL_SQOPEN);
    r = r && goal_2(b, l + 1);
    r = r && consumeToken(b, PL_SQCLOSE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // PL_ACHIEVEGOAL | PL_PERFORMGOAL
  private static boolean goal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "goal_2")) return false;
    boolean r;
    r = consumeToken(b, PL_ACHIEVEGOAL);
    if (!r) r = consumeToken(b, PL_PERFORMGOAL);
    return r;
  }

  /* ********************************************************** */
  // (NOT)? (BELIEVE fof_expr | GOAL goal | fof_expr
  //     | SENT OPEN agentnameterm (COMMA agentnameterm COMMA)? performative COMMA fof_expr CLOSE)
  //     | TRUE
  public static boolean guard_atom(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GUARD_ATOM, "<guard atom>");
    r = guard_atom_0(b, l + 1);
    if (!r) r = consumeToken(b, TRUE);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (NOT)? (BELIEVE fof_expr | GOAL goal | fof_expr
  //     | SENT OPEN agentnameterm (COMMA agentnameterm COMMA)? performative COMMA fof_expr CLOSE)
  private static boolean guard_atom_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = guard_atom_0_0(b, l + 1);
    r = r && guard_atom_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (NOT)?
  private static boolean guard_atom_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_0")) return false;
    consumeToken(b, NOT);
    return true;
  }

  // BELIEVE fof_expr | GOAL goal | fof_expr
  //     | SENT OPEN agentnameterm (COMMA agentnameterm COMMA)? performative COMMA fof_expr CLOSE
  private static boolean guard_atom_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = guard_atom_0_1_0(b, l + 1);
    if (!r) r = guard_atom_0_1_1(b, l + 1);
    if (!r) r = fof_expr(b, l + 1);
    if (!r) r = guard_atom_0_1_3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // BELIEVE fof_expr
  private static boolean guard_atom_0_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BELIEVE);
    r = r && fof_expr(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // GOAL goal
  private static boolean guard_atom_0_1_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, GOAL);
    r = r && goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // SENT OPEN agentnameterm (COMMA agentnameterm COMMA)? performative COMMA fof_expr CLOSE
  private static boolean guard_atom_0_1_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1_3")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, SENT, OPEN);
    r = r && agentnameterm(b, l + 1);
    r = r && guard_atom_0_1_3_3(b, l + 1);
    r = r && performative(b, l + 1);
    r = r && consumeToken(b, COMMA);
    r = r && fof_expr(b, l + 1);
    r = r && consumeToken(b, CLOSE);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA agentnameterm COMMA)?
  private static boolean guard_atom_0_1_3_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1_3_3")) return false;
    guard_atom_0_1_3_3_0(b, l + 1);
    return true;
  }

  // COMMA agentnameterm COMMA
  private static boolean guard_atom_0_1_3_3_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "guard_atom_0_1_3_3_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && agentnameterm(b, l + 1);
    r = r && consumeToken(b, COMMA);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // (GWENDOLEN)?
  //     (NAME | NAME_PM) CONST
  //     BELIEFS (BELIEF_BLOCK)*
  //     (BELIEFRULES (RR_NEWLINE)* (RR_BLOCK)*)?
  //     (GOAL_IB | GOAL_RR) (initial_goal)*
  //     PLANS (plan)+
  public static boolean gwendolenagent(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, GWENDOLENAGENT, "<gwendolenagent>");
    r = gwendolenagent_0(b, l + 1);
    r = r && gwendolenagent_1(b, l + 1);
    r = r && consumeTokens(b, 0, CONST, BELIEFS);
    r = r && gwendolenagent_4(b, l + 1);
    r = r && gwendolenagent_5(b, l + 1);
    r = r && gwendolenagent_6(b, l + 1);
    r = r && gwendolenagent_7(b, l + 1);
    r = r && consumeToken(b, PLANS);
    r = r && gwendolenagent_9(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (GWENDOLEN)?
  private static boolean gwendolenagent_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_0")) return false;
    consumeToken(b, GWENDOLEN);
    return true;
  }

  // NAME | NAME_PM
  private static boolean gwendolenagent_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_1")) return false;
    boolean r;
    r = consumeToken(b, NAME);
    if (!r) r = consumeToken(b, NAME_PM);
    return r;
  }

  // (BELIEF_BLOCK)*
  private static boolean gwendolenagent_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, BELIEF_BLOCK)) break;
      if (!empty_element_parsed_guard_(b, "gwendolenagent_4", c)) break;
    }
    return true;
  }

  // (BELIEFRULES (RR_NEWLINE)* (RR_BLOCK)*)?
  private static boolean gwendolenagent_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_5")) return false;
    gwendolenagent_5_0(b, l + 1);
    return true;
  }

  // BELIEFRULES (RR_NEWLINE)* (RR_BLOCK)*
  private static boolean gwendolenagent_5_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_5_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BELIEFRULES);
    r = r && gwendolenagent_5_0_1(b, l + 1);
    r = r && gwendolenagent_5_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (RR_NEWLINE)*
  private static boolean gwendolenagent_5_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_5_0_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, RR_NEWLINE)) break;
      if (!empty_element_parsed_guard_(b, "gwendolenagent_5_0_1", c)) break;
    }
    return true;
  }

  // (RR_BLOCK)*
  private static boolean gwendolenagent_5_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_5_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!consumeToken(b, RR_BLOCK)) break;
      if (!empty_element_parsed_guard_(b, "gwendolenagent_5_0_2", c)) break;
    }
    return true;
  }

  // GOAL_IB | GOAL_RR
  private static boolean gwendolenagent_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_6")) return false;
    boolean r;
    r = consumeToken(b, GOAL_IB);
    if (!r) r = consumeToken(b, GOAL_RR);
    return r;
  }

  // (initial_goal)*
  private static boolean gwendolenagent_7(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_7")) return false;
    while (true) {
      int c = current_position_(b);
      if (!gwendolenagent_7_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "gwendolenagent_7", c)) break;
    }
    return true;
  }

  // (initial_goal)
  private static boolean gwendolenagent_7_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_7_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = initial_goal(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (plan)+
  private static boolean gwendolenagent_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_9")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = gwendolenagent_9_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!gwendolenagent_9_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "gwendolenagent_9", c)) break;
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // (plan)
  private static boolean gwendolenagent_9_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "gwendolenagent_9_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = plan(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // GOAL_BLOCK GL_SQOPEN (GL_ACHIEVEGOAL | GL_PERFORMGOAL) GL_SQCLOSE
  public static boolean initial_goal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initial_goal")) return false;
    if (!nextTokenIs(b, GOAL_BLOCK)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeTokens(b, 0, GOAL_BLOCK, GL_SQOPEN);
    r = r && initial_goal_2(b, l + 1);
    r = r && consumeToken(b, GL_SQCLOSE);
    exit_section_(b, m, INITIAL_GOAL, r);
    return r;
  }

  // GL_ACHIEVEGOAL | GL_PERFORMGOAL
  private static boolean initial_goal_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "initial_goal_2")) return false;
    boolean r;
    r = consumeToken(b, GL_ACHIEVEGOAL);
    if (!r) r = consumeToken(b, GL_PERFORMGOAL);
    return r;
  }

  /* ********************************************************** */
  // glist
  static boolean mas(PsiBuilder b, int l) {
    return glist(b, l + 1);
  }

  /* ********************************************************** */
  // EQUAL | LESS | PLUS | MINUS
  public static boolean oper(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "oper")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPER, "<oper>");
    r = consumeToken(b, EQUAL);
    if (!r) r = consumeToken(b, LESS);
    if (!r) r = consumeToken(b, PLUS);
    if (!r) r = consumeToken(b, MINUS);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // TELL | COLON PL_PERFORMGOAL | COLON PL_ACHIEVEGOAL
  public static boolean performative(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "performative")) return false;
    if (!nextTokenIs(b, "<performative>", COLON, TELL)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PERFORMATIVE, "<performative>");
    r = consumeToken(b, TELL);
    if (!r) r = parseTokens(b, 0, COLON, PL_PERFORMGOAL);
    if (!r) r = parseTokens(b, 0, COLON, PL_ACHIEVEGOAL);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // event
  //     COLON CURLYOPEN guard_atom (COMMA guard_atom)* CURLYCLOSE
  //     (RULEARROW
  //     deed (COMMA deed)*)?
  //     SEMI
  public static boolean plan(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan")) return false;
    if (!nextTokenIs(b, "<plan>", MINUS, PLUS)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, PLAN, "<plan>");
    r = event(b, l + 1);
    r = r && consumeTokens(b, 0, COLON, CURLYOPEN);
    r = r && guard_atom(b, l + 1);
    r = r && plan_4(b, l + 1);
    r = r && consumeToken(b, CURLYCLOSE);
    r = r && plan_6(b, l + 1);
    r = r && consumeToken(b, SEMI);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (COMMA guard_atom)*
  private static boolean plan_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_4")) return false;
    while (true) {
      int c = current_position_(b);
      if (!plan_4_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "plan_4", c)) break;
    }
    return true;
  }

  // COMMA guard_atom
  private static boolean plan_4_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_4_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && guard_atom(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (RULEARROW
  //     deed (COMMA deed)*)?
  private static boolean plan_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_6")) return false;
    plan_6_0(b, l + 1);
    return true;
  }

  // RULEARROW
  //     deed (COMMA deed)*
  private static boolean plan_6_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_6_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RULEARROW);
    r = r && deed(b, l + 1);
    r = r && plan_6_0_2(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // (COMMA deed)*
  private static boolean plan_6_0_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_6_0_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!plan_6_0_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "plan_6_0_2", c)) break;
    }
    return true;
  }

  // COMMA deed
  private static boolean plan_6_0_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "plan_6_0_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && deed(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // MULT (NOT?) fof_expr
  public static boolean waitfor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "waitfor")) return false;
    if (!nextTokenIs(b, MULT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, MULT);
    r = r && waitfor_1(b, l + 1);
    r = r && fof_expr(b, l + 1);
    exit_section_(b, m, WAITFOR, r);
    return r;
  }

  // NOT?
  private static boolean waitfor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "waitfor_1")) return false;
    consumeToken(b, NOT);
    return true;
  }

}
