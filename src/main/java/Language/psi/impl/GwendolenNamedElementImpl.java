package Language.psi.impl;

import Language.psi.GwendolenNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class GwendolenNamedElementImpl extends ASTWrapperPsiElement implements GwendolenNamedElement {
    public GwendolenNamedElementImpl(@NotNull ASTNode node){
        super(node);
    }

}
