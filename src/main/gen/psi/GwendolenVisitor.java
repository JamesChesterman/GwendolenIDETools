package psi;

import Language.psi.GwendolenNamedElement;
import com.intellij.psi.PsiElementVisitor;
import org.jetbrains.annotations.NotNull;
import com.intellij.psi.PsiElement;

public class GwendolenVisitor extends PsiElementVisitor {
    public void visitProperty(@NotNull GwendolenProperty o){
        visitElement(o);
    }

    public void visitNamedElement(@NotNull GwendolenNamedElement o){
        visitElement(o);
    }

    public void visitPsiElement(@NotNull PsiElement o){
        visitElement(o);
    }

}
