// This is a generated file. Not intended for manual editing.
package Language.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static Language.psi.GwendolenTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import Language.psi.*;

public class GwendolenGwendolenagentImpl extends ASTWrapperPsiElement implements GwendolenGwendolenagent {

  public GwendolenGwendolenagentImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GwendolenVisitor visitor) {
    visitor.visitGwendolenagent(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GwendolenVisitor) accept((GwendolenVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GwendolenInitialGoal> getInitialGoalList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenInitialGoal.class);
  }

  @Override
  @NotNull
  public List<GwendolenPlan> getPlanList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenPlan.class);
  }

}
