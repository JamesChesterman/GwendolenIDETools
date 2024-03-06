package Settings;


import com.intellij.openapi.options.Configurable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.io.File;

//Methods in here are called by IntelliJ
//Then this interacts with the GwenSettingsComponent and GwenSettingsState
//Like a Controller in MVC
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
        boolean hasBeenModified = !gwenSettingsComponent.getAilAgentFilePath().equals(gwenSettings.ailAgentFilePath)
                | !gwenSettingsComponent.getAilAgentLineNum().equals(gwenSettings.getAilAgentLineNum())
                | !gwenSettingsComponent.getPlanLibraryFilePath().equals(gwenSettings.planLibraryFilePath)
                | !gwenSettingsComponent.getPlanLibraryLineNum().equals(gwenSettings.planLibraryLineNum);
        return hasBeenModified;
    }

    //Gets the text from each text field in the settings menu
    //Store in GwenSettingsState
    @Override
    public void apply(){
        GwenSettingsState gwenSettings = GwenSettingsState.getInstance();
        String ailAgentFilePath = gwenSettingsComponent.getAilAgentFilePath();
        if(isValidFile(ailAgentFilePath)){
            gwenSettings.ailAgentFilePath = ailAgentFilePath;
        }
        //Need to subtract 1 from the line number the user inputs
        //Because in the text editor, lines start at 1. In code, the lines start at 0.
        gwenSettings.ailAgentLineNum = String.valueOf(Integer.parseInt(gwenSettingsComponent.getAilAgentLineNum()) - 1);

        String planLibraryFilePath = gwenSettingsComponent.getPlanLibraryFilePath();
        if(isValidFile(planLibraryFilePath)){
            gwenSettings.planLibraryFilePath = gwenSettingsComponent.getPlanLibraryFilePath();
        }
        gwenSettings.planLibraryLineNum = String.valueOf(Integer.parseInt(gwenSettingsComponent.getPlanLibraryLineNum()) - 1);
    }

    //Sets the text fields to what is stored in the settings
    @Override
    public void reset(){
        GwenSettingsState gwenSettings = GwenSettingsState.getInstance();
        if(gwenSettings.ailAgentFilePath != null){
            gwenSettingsComponent.setAilAgentFilePath(gwenSettings.ailAgentFilePath);
        }
        if(gwenSettings.getAilAgentLineNum() != null){
            //Need to add 1 to line numbers obtained from the state
            //So the user will see the line number corresponding to the one in their text editor
            gwenSettingsComponent.setAilAgentLineNumField(String.valueOf(Integer.parseInt(gwenSettings.getAilAgentLineNum()) + 1));
        }
        if(gwenSettings.planLibraryFilePath != null){
            gwenSettingsComponent.setPlanLibraryFilePathField(gwenSettings.planLibraryFilePath);
        }
        if(gwenSettings.getPlanLibraryLineNum() != null){
            gwenSettingsComponent.setPlanLibraryLineNumField(String.valueOf(Integer.parseInt(gwenSettings.getPlanLibraryLineNum()) + 1));
        }
    }

    private boolean isValidFile(String filePath){
        File file = new File(filePath);
        if(!file.isFile() || !file.exists()) {
            //Warning message set to 'File doesn't exist'
            gwenSettingsComponent.setWarningLabel("File doesn't exist at: " + filePath);
            return false;
        }else if(!filePath.endsWith(".java")){
            //Warning message set to 'File must be a .java file'
            gwenSettingsComponent.setWarningLabel("File must be a .java file at: " + filePath);
            return false;
        }else{
            return true;
        }
    }

    @Override
    public void disposeUIResources(){
        gwenSettingsComponent = null;
    }




}
