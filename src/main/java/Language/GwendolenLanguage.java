package Language;

import com.intellij.lang.Language;

public class GwendolenLanguage extends Language {
    public static final GwendolenLanguage INSTANCE = new GwendolenLanguage();

    private GwendolenLanguage(){
        super("Gwendolen");
    }
}
