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

public class GwendolenPlanImpl extends ASTWrapperPsiElement implements GwendolenPlan {

  public GwendolenPlanImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GwendolenVisitor visitor) {
    visitor.visitPlan(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GwendolenVisitor) accept((GwendolenVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GwendolenDeed> getDeedList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenDeed.class);
  }

  @Override
  @NotNull
  public GwendolenEvent getEvent() {
    return findNotNullChildByClass(GwendolenEvent.class);
  }

  @Override
  @NotNull
  public List<GwendolenGuardAtom> getGuardAtomList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenGuardAtom.class);
  }

}
