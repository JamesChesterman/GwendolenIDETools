package Settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


//Menu for the settings to be entered
//Like a view in MVC
public class GwenSettingsComponent {
    //Have a panel here rather than inheriting JPanel so you can set FormBuilder to the panel
    private final JPanel mainPanel;
    private final JBTextField ailAgentFilePathField = new JBTextField();
    private final JBTextField ailAgentLineNumField = new JBTextField();
    private final JBTextField planLibraryFilePathField = new JBTextField();
    private final JBTextField planLibraryLineNumField = new JBTextField();
    private JBLabel warningLabel = new JBLabel("");

    public GwenSettingsComponent(){
         mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("AILAgent.java file path: "), ailAgentFilePathField, 1, false)
                 .addLabeledComponent(new JBLabel("AILAgent.java line number for: 'rule.apply(this);'"), ailAgentLineNumField, 1, false)
                 .addLabeledComponent(new JBLabel("PlanLibrary.java file path: "), planLibraryFilePathField, 1, false)
                 .addLabeledComponent(new JBLabel("PlanLibrary.java line number for: 'PlanSet l = relPlans.get(pi);'"), planLibraryLineNumField, 1, false)
                 .addComponent(warningLabel, 0)
                 .addComponentFillVertically(new JPanel(), 0)
                 .getPanel();
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent(){
        return ailAgentFilePathField;
    }

    //Get values from text fields
    public String getAilAgentFilePath(){
        return ailAgentFilePathField.getText();
    }

    public String getAilAgentLineNum(){
        return ailAgentLineNumField.getText();
    }

    public String getPlanLibraryFilePath(){
        return planLibraryFilePathField.getText();
    }

    public String getPlanLibraryLineNum(){
        return planLibraryLineNumField.getText();
    }


    //Set values in text fields
    public void setAilAgentFilePath(@NotNull String newText){
        ailAgentFilePathField.setText(newText);
    }

    public void setAilAgentLineNumField(@NotNull String newText){
        ailAgentLineNumField.setText(newText);
    }

    public void setPlanLibraryFilePathField(@NotNull String newText){
        planLibraryFilePathField.setText(newText);
    }

    public void setPlanLibraryLineNumField(@NotNull String newText){
        planLibraryLineNumField.setText(newText);
    }

    //Set value in warning label
    public void setWarningLabel(String warningText){
        warningLabel.setText(warningText);
    }


}
