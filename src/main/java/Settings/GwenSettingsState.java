package Settings;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.util.xmlb.XmlSerializerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

//@Storage will make a custom file name for the data
@State(
        name = "Settings.GwenSettingsState",
        storages = @Storage("GwenPluginSettings.xml")
)
//Like a Model in MVC
public class GwenSettingsState implements PersistentStateComponent<GwenSettingsState> {
    //Fields need to be public so PersistentStateComponent will serialize them
    public String ailAgentFilePath;
    public String ailAgentLineNum;
    public String planLibraryFilePath;
    public String planLibraryLineNum;

    //Allows GwenSettingsConfigurable to get the right class easily
    public static GwenSettingsState getInstance(){
        return ApplicationManager.getApplication().getService(GwenSettingsState.class);
    }

    public String getAilAgentLineNum(){
        //Default value if no changes made to line num is 2034
        if(ailAgentLineNum == null){
            return String.valueOf(2034);
        }else{
            return ailAgentLineNum;
        }
    }

    public String getPlanLibraryLineNum(){
        //Default value if no changes made to line num is 174
        if(planLibraryLineNum == null){
            return String.valueOf(174);
        }else{
            return planLibraryLineNum;
        }
    }

    @Nullable
    @Override
    public GwenSettingsState getState(){
        return this;
    }

    @Override
    public void loadState(@NotNull GwenSettingsState state){
        XmlSerializerUtil.copyBean(state, this);
    }
}
