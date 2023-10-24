package Grammar;

import Language.GwendolenFileType;
import Language.GwendolenLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class GwendolenFile extends PsiFileBase {
    public GwendolenFile(@NotNull FileViewProvider viewProvider){
        super(viewProvider, GwendolenLanguage.INSTANCE);
    }

    @NotNull
    @Override
    public FileType getFileType(){
        return GwendolenFileType.INSTANCE;
    }

    @Override
    public String toString(){
        return "Gwendolen File";
    }
}
