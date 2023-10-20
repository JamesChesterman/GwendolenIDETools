package Language;

import com.intellij.openapi.fileTypes.LanguageFileType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class GwendolenFileType extends LanguageFileType {
    public static final GwendolenFileType INSTANCE = new GwendolenFileType();

    private GwendolenFileType(){
        super(GwendolenLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public String getName(){
        return "Gwendolen File";
    }

    @NotNull
    @Override
    public String getDescription(){
        return "Gwendolen language file";
    }

    @NotNull
    @Override
    public String getDefaultExtension(){
        return "gwen";
    }

    @Nullable
    @Override
    public Icon getIcon(){
        return GwendolenIcons.FILE;
    }
}
