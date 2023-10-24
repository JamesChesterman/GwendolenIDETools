package Grammar;

import com.intellij.psi.tree.TokenSet;
import psi.GwendolenTypes;

public interface GwendolenTokenSets {
    TokenSet GWENDOLEN = TokenSet.create(GwendolenTypes.GWENDOLEN);

    TokenSet COMMENT = TokenSet.create(GwendolenTypes.COMMENT);

    TokenSet LINE_COMMENT = TokenSet.create(GwendolenTypes.LINE_COMMENT);

}
