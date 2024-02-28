package GwendolenToolWindow;

import GwenDebugger.BreakpointController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PlansViewer extends JPanel {

    private final String planLibraryFileURL = "C:\\Users\\chest\\mcapl-mcapl2023\\src\\classes\\ail\\syntax\\PlanLibrary.java";
    private final int planLibraryLineNum = 174;
    private JCheckBox planBreakdownCheckBox;
    private boolean breakpointEnabled;
    private BreakpointController breakpointController;

    public PlansViewer(BreakpointController breakpointController){
        super();
        this.breakpointController = breakpointController;

        makeBreakdownCheckbox();
    }

    private void makeBreakdownCheckbox(){
        planBreakdownCheckBox = new JCheckBox("Plan Breakdown Enabled");
        breakpointEnabled = breakpointController.checkBreakpoint(planLibraryFileURL, planLibraryLineNum);
        planBreakdownCheckBox.setSelected(breakpointEnabled);
        planBreakdownCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                breakpointEnabled = !breakpointEnabled;
                breakpointController.toggleBreakpoint(planLibraryFileURL, planLibraryLineNum);
            }
        });

        addComponent(this, planBreakdownCheckBox, 0, 0, 1, 1);
    }

    //Add component to grid bag layout.
    private void addComponent(Container container, Component component, int column, int row, int width, int height){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,5,5,5);
        container.add(component, constraints);
    }

}
