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

    //Allos GwenSettingsConfigurable to get the right class easily
    public static GwenSettingsState getInstance(){
        return ApplicationManager.getApplication().getService(GwenSettingsState.class);
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
