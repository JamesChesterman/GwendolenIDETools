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

public class GwendolenGuardAtomImpl extends ASTWrapperPsiElement implements GwendolenGuardAtom {

  public GwendolenGuardAtomImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GwendolenVisitor visitor) {
    visitor.visitGuardAtom(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GwendolenVisitor) accept((GwendolenVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GwendolenAgentnameterm> getAgentnametermList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenAgentnameterm.class);
  }

  @Override
  @Nullable
  public GwendolenFofExpr getFofExpr() {
    return findChildByClass(GwendolenFofExpr.class);
  }

  @Override
  @Nullable
  public GwendolenGoal getGoal() {
    return findChildByClass(GwendolenGoal.class);
  }

  @Override
  @Nullable
  public GwendolenPerformative getPerformative() {
    return findChildByClass(GwendolenPerformative.class);
  }

}
