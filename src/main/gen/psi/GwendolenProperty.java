package psi;

import Language.psi.GwendolenNamedElement;
import com.intellij.navigation.ItemPresentation;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

public interface GwendolenProperty extends GwendolenNamedElement {

    String getName();

    PsiElement setName(@NotNull String newName);

    PsiElement getNameIdentifier();

    ItemPresentation getPresentation();
}
