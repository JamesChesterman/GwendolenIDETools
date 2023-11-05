package Language;

import com.intellij.lexer.FlexAdapter;

public class GwendolenLexerAdapter extends FlexAdapter {
    public GwendolenLexerAdapter(){
        super(new GwendolenLexer(null));
    }
}
