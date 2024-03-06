package GwendolenToolWindow;

import GwenDebugger.BreakpointController;
import GwenDebugger.DebugTreeUtils;
import GwenDebugger.JavaBreakpointListener;
import com.intellij.ui.JBColor;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
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
    private JLabel explanationLabel;
    private String currentPi = "";
    private JLabel piLabel;
    private JLabel piValueLabel;
    private final String piText = "Predicate Indicator of event at the top of Current Intention's event stack: ";
    private final Color greenColour = new Color(25,84,40);
    private final Color redColour = new Color(112,8,8);
    private List<JPanel> planPanels;
    private List<JLabel> planLabels;

    private boolean[] isMap = {
            false,
            true
    };


    public PlansViewer(GwenToolWindowContent gwenToolWindow, BreakpointController breakpointController){
        super();
        this.gwenToolWindow = gwenToolWindow;
        this.breakpointController = breakpointController;
        planPanels = new ArrayList<>();
        planLabels = new ArrayList<>();

        setLayout(new GridBagLayout());

        makeBreakdownCheckbox();
        makeSkipToPlanBreakdown();
        makeLabels();
    }

    //Called in gwentoolwindow when starttools is pressed
    //Should reset the UI so you can run the program again
    public void setItemsEnabled(boolean enabled){
        skipButton.setEnabled(enabled);
        piValueLabel.setText("");
        for(int i=0; i<planPanels.size(); i++){
            planPanels.get(i).setVisible(false);
        }
    }

    //Checkbox that toggles whether or not there is a breakpoint in PlanLibrary.java
    //This gives important information for plans
    private void makeBreakdownCheckbox(){
        planBreakdownCheckBox = new JCheckBox("Plan Breakdown Enabled");
        breakpointEnabled = breakpointController.checkBreakpoint(JavaBreakpointListener.getPlanLibraryFileURL(),
                                                                JavaBreakpointListener.getPlanLibraryLineNum());
        planBreakdownCheckBox.setSelected(breakpointEnabled);
        planBreakdownCheckBox.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                breakpointEnabled = !breakpointEnabled;
                skipButton.setEnabled(breakpointEnabled);
                breakpointController.toggleBreakpoint(JavaBreakpointListener.getPlanLibraryFileURL(),
                                                    JavaBreakpointListener.getPlanLibraryLineNum());
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

    private void makeLabels(){
        explanationLabel = new JLabel("When current intention has 'which has no plan yet' at the top of its deed stack...");
        addComponent(this, explanationLabel, 0, 2, 2, 1);


        piLabel = new JLabel(piText);
        addComponent(this, piLabel, 0, 3, 1, 1);

        //Add green box around the pi value
        JPanel piPanel = new JPanel();
        piPanel.setBackground(greenColour);
        piValueLabel = new JLabel("");
        piPanel.add(piValueLabel);

        addComponent(this, piPanel, 0,4,1,1);

        JLabel applicablePlansLabel = new JLabel("Plans Applicable / Inapplicable:");
        addComponent(this, applicablePlansLabel, 0, 5, 1, 1);
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

    //Called from breakpoint listener. Passes in the new debug tree and whether the breakpoint should be skipped over
    //If skipped is true, it resumes the program again without doing anything with tree (should be null anyway)
    public void updateDebugTreeValues(XDebuggerTree newTree, boolean skipped){
        if(skipped) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run(){
                    breakpointController.goToNextCycle(gwenToolWindow.getDebugSession());
                }
            });
        }else{
            gwenToolWindow.setPlanMode(false);

            sendInfoGet(newTree);
        }
    }

    //Similar to BGIViewer code, sets up info to send to DebugTreeUtils
    private void sendInfoGet(XDebuggerTree tree){
        setLabelsLoading();

        //relPlans key has the predicate indicator, value has the whole plan
        String[][] findArray = {
                {"pi"},
                {"relPlans"}
        };

        boolean[] allowChildren = {
                false,
                true
        };

        //Used when receiving the map nodes
        //Because they can be returned in any order
        //Still need to have every node here.
        String[] labelStrings = {
                "pi",
                "relPlans"
        };

        DebugTreeUtils.setPlansViewer(this);
        DebugTreeUtils.findInTree(tree.getRoot(), findArray, allowChildren, isMap, labelStrings);
    }

    //Set all labels (for values) to 'Loading...' whilst their values are being retrieved from the debug tree
    private void setLabelsLoading(){
        piValueLabel.setText("Loading...");
        for(int i=0; i<planLabels.size(); i++){
            planLabels.get(i).setText("Loading...");
        }
    }

    //Called from DebugTreeUtils with the information requested by the sendInfoGet method
    //Adds list of relPlans
    public void receiveInfoGet(String[][] returnArray){
        for(int i=0; i<returnArray.length; i++){
            if(isMap[i]){
                continue;
            }

            if(i == 0){
                //It's the predicate indicator of
                //The event at the top of
                //Current intention's event stack
                currentPi = returnArray[i][0];
                piValueLabel.setText(currentPi);
            }

        }
    }

    //Called from DebugTreeUtils
    //Should only be one node returned (just with its children)
    public void receiveMapInfo(List<List<List<String>>> mapNodeChildren, List<String> labelStringsFound){
        List<List<String>> listOfPlanMaps = mapNodeChildren.get(0);
        int numOfPlans = listOfPlanMaps.size();
        makeAllPanelsVisible();
        checkMakeMorePanels(numOfPlans);
        checkDisablePanels(numOfPlans);
        for(int i=0; i<listOfPlanMaps.size(); i++){
            //Predicate indicator of plan - must match current intention top event predicate indicator
            List<String> planMap = listOfPlanMaps.get(i);
            String planPi = planMap.get(0);
            String plan = planMap.get(1);
            processPlanPIs(planPi, plan, i);
        }


        skipButton.setEnabled(true);
    }

    //When doing checkDisablePanels
    //Some panels will become invisible
    //This just makes them all visible before they're processed
    private void makeAllPanelsVisible(){
        for(int i=0; i<planPanels.size(); i++){
            planPanels.get(i).setVisible(true);
        }
    }

    //Each plan is contained in a panel
    //When there are more plans than panels
    //Will make more panels and add them to list of panels
    private void checkMakeMorePanels(int numOfPlans){
        if(numOfPlans > planPanels.size()){
            for(int i=planPanels.size(); i<numOfPlans; i++){
                JPanel panel = new JPanel();
                JLabel label = new JLabel();
                panel.add(label);
                planPanels.add(panel);
                planLabels.add(label);
                addComponent(this, panel, 0, 6 + planPanels.size()-1, 2, 1);
            }
        }
    }

    //Each plan contained in a panel
    //When there are fewer plans than panels
    private void checkDisablePanels(int numOfPlans){
        if(numOfPlans < planPanels.size()){
            for(int i=numOfPlans; i<planPanels.size(); i++){
                planPanels.get(i).setVisible(false);
            }
        }
    }

    private void processPlanPIs(String planPi, String plan, int i){
        JPanel panel = planPanels.get(i);
        //Colour the background green if plan is applicable. Red if not.
        if(currentPi.equals(planPi)){
            panel.setBackground(greenColour);
        }else{
            panel.setBackground(redColour);
        }
        //Get panel's corresponding label and set text to plan
        JLabel planLabel = planLabels.get(i);
        planLabel.setText(plan);
    }

}
