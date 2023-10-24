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

public class GwendolenFofExprImpl extends ASTWrapperPsiElement implements GwendolenFofExpr {

  public GwendolenFofExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull GwendolenVisitor visitor) {
    visitor.visitFofExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof GwendolenVisitor) accept((GwendolenVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<GwendolenConstVar> getConstVarList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenConstVar.class);
  }

  @Override
  @NotNull
  public List<GwendolenFofExpr> getFofExprList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, GwendolenFofExpr.class);
  }

  @Override
  @Nullable
  public GwendolenOper getOper() {
    return findChildByClass(GwendolenOper.class);
  }

}
