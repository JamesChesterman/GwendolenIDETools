package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import GwenDebugger.JavaBreakpointListener;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PlansViewer extends JPanel {
    private JCheckBox planBreakdownCheckBox;
    private boolean breakpointEnabled;
    private BreakpointController breakpointController;
    private GwenToolWindowContent gwenToolWindow;
    private JButton skipButton;
    private String planLibraryFileURL;
    private int planLibraryLineNum;

    public PlansViewer(GwenToolWindowContent gwenToolWindow, BreakpointController breakpointController){
        super();
        this.gwenToolWindow = gwenToolWindow;
        this.breakpointController = breakpointController;
        planLibraryFileURL = JavaBreakpointListener.getPlanLibraryFileURL();
        planLibraryLineNum = JavaBreakpointListener.getPlanLibraryLineNum();

        makeBreakdownCheckbox();
        makeSkipToPlanBreakdown();
    }

    //Checkbox that toggles whether or not there is a breakpoint in PlanLibrary.java
    //This gives important information for plans
    private void makeBreakdownCheckbox(){
        planBreakdownCheckBox = new JCheckBox("Plan Breakdown Enabled");
        breakpointEnabled = breakpointController.checkBreakpoint(planLibraryFileURL, planLibraryLineNum);
        planBreakdownCheckBox.setSelected(breakpointEnabled);
        planBreakdownCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                breakpointEnabled = !breakpointEnabled;
                skipButton.setEnabled(breakpointEnabled);
                breakpointController.toggleBreakpoint(planLibraryFileURL, planLibraryLineNum);
            }
        });

        addComponent(this, planBreakdownCheckBox, 0, 0, 1, 1);
    }

    //Button that will skip to the next plans breakpoint (the breakpoint in PlanLibrary)
    //This will do the 'skip' operation on the breakpoint in AILAgent.java
    //It will stop when the breakpoint in PlanLibrary is reached.
    private void makeSkipToPlanBreakdown(){
        skipButton = new JButton("Skip To Next Plan Breakdown");
        skipButton.setEnabled(breakpointEnabled);
        skipButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                gwenToolWindow.setPlanMode(true);
                skipButton.setEnabled(false);
                breakpointController.goToNextCycle(gwenToolWindow.getDebugSession());
            }
        });
        addComponent(this, skipButton, 0, 1, 1, 1);
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

    public void updateDebugTreeValues(XDebuggerTree newTree, boolean skipped){
        if(skipped) {
            breakpointController.goToNextCycle(gwenToolWindow.getDebugSession());
        }else{
            gwenToolWindow.setPlanMode(false);
            skipButton.setEnabled(true);
        }
    }

}
