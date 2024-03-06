package Settings;


import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

//Methods in here are called by IntelliJ
//Then this interacts with the GwenSettingsComponent and GwenSettingsState
public class GwenSettingsConfigurable implements Configurable {
    private GwenSettingsComponent gwenSettingsComponent;

    @Nls(capitalization = Nls.Capitalization.Title)
    @Override
    public String getDisplayName(){
        return "Gwendolen Plugin Settings";
    }

    @Override
    public JComponent getPreferredFocusedComponent(){
        return gwenSettingsComponent.getPreferredFocusedComponent();
    }

    @Nullable
    @Override
    public JComponent createComponent(){
        gwenSettingsComponent = new GwenSettingsComponent();
        return gwenSettingsComponent.getPanel();
    }

    @Override
    public boolean isModified(){
        GwenSettingsState gwenSettings = GwenSettingsState.getInstance();
        //When the file path stored doesn't match the file path entered in the form
        //Then it has been modified
        boolean hasBeenModified = !gwenSettingsComponent.getAilAgentFilePath().equals(gwenSettings.ailAgentFilePath);
        return hasBeenModified;
    }

    //Gets the text from each text field in the settings menu
    //Store in GwenSettingsState
    @Override
    public void apply(){
        GwenSettingsState gwenSettings = GwenSettingsState.getInstance();
        gwenSettings.ailAgentFilePath = gwenSettingsComponent.getAilAgentFilePath();
    }

    //Sets the text fields to what is stored in the settings
    @Override
    public void reset(){
        GwenSettingsState gwenSettings = GwenSettingsState.getInstance();
        gwenSettingsComponent.setAilAgentFilePath(gwenSettings.ailAgentFilePath);
    }

    @Override
    public void disposeUIResources(){
        gwenSettingsComponent = null;
    }




}
