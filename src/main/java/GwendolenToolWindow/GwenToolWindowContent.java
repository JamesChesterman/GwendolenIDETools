package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GwenToolWindowContent {
    private final JPanel contentPanel = new JPanel();
    private BreakpointController breakpointController;

    public GwenToolWindowContent(ToolWindow toolWindow){
        contentPanel.setLayout(new BorderLayout(0, 20));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        contentPanel.add(createControlsPanel(), BorderLayout.PAGE_START);
    }

    public void initBreakpointController(Project project){
        breakpointController = new BreakpointController(project);
    }

    @NotNull
    private JPanel createControlsPanel(){
        JPanel controlsPanel = new JPanel();
        JCheckBox steppingCheckBox = new JCheckBox("Enable Stepping Mode: ");

        steppingCheckBox.addItemListener(new ItemListener(){
            @Override
            public void itemStateChanged(ItemEvent e){
                breakpointController.addBreakpoint();
                if(e.getStateChange() == ItemEvent.SELECTED){
                    System.out.println("Stepping mode enabled");
                }else{
                    System.out.println("Stepping mode disabled");
                }
            }
        });

        controlsPanel.add(steppingCheckBox);

        return controlsPanel;
    }

    public JPanel getContentPanel(){
        return contentPanel;
    }
}
