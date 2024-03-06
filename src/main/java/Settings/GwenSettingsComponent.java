package Settings;

import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.FormBuilder;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;


//Menu for the settings to be entered
public class GwenSettingsComponent {
    //Have a panel here rather than inheriting JPanel so you can set FormBuilder to the panel
    private final JPanel mainPanel;
    private final JBTextField ailAgentFilePathField = new JBTextField();

    public GwenSettingsComponent(){
         mainPanel = FormBuilder.createFormBuilder()
                .addLabeledComponent(new JBLabel("AILAgent.java File Path: "), ailAgentFilePathField, 1, false)
                 .addComponentFillVertically(new JPanel(), 0)
                 .getPanel();
    }

    public JPanel getPanel(){
        return mainPanel;
    }

    public JComponent getPreferredFocusedComponent(){
        return ailAgentFilePathField;
    }

    public String getAilAgentFilePath(){
        return ailAgentFilePathField.getText();
    }

    public void setAilAgentFilePath(@NotNull String newText){
        ailAgentFilePathField.setText(newText);
    }


}
