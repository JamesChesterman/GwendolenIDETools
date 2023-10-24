package Grammar;

import Language.GwendolenLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class GwendolenElementType extends IElementType {
    public GwendolenElementType(@NotNull @NonNls String debugName){
        super(debugName, GwendolenLanguage.INSTANCE);
    }


}
