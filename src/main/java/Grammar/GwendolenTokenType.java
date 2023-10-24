package Grammar;

import Language.GwendolenLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class GwendolenTokenType extends IElementType {
    public GwendolenTokenType(@NotNull @NonNls String debugName){
        super(debugName, GwendolenLanguage.INSTANCE);
    }

    @Override
    public String toString(){
        return "GwendolenTokenType." + super.toString();
    }

}
