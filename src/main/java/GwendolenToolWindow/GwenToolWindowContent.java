package GwendolenToolWindow;

import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();

    public GwenToolWindowContent(ToolWindow toolWindow){
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);
    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        JLabel label = new JLabel("HELLO");
        controlsPanel.add(label);
        return controlsPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
