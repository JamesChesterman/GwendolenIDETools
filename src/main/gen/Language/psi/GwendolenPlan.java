// This is a generated file. Not intended for manual editing.
package Language.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface GwendolenPlan extends PsiElement {

  @NotNull
  List<GwendolenDeed> getDeedList();

  @NotNull
  GwendolenEvent getEvent();

  @NotNull
  List<GwendolenGuardAtom> getGuardAtomList();

}
