package GwendolenToolWindow;

import GwenDebugger.DebugTreeUtils;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class BGIViewer extends JPanel {
    XDebuggerTree tree;
    private ComboBox agentComboBox;
    private JLabel agentComboBoxLabel;
    private String[] agents;
    private boolean agentsAdded;
    JLabel stageLabel = null;
    JLabel agentsLabel = null;
    JLabel agentNameLabel = null;
    JLabel beliefsLabel = null;
    JLabel goalsLabel = null;
    JLabel intentionsLabel = null;
    JLabel currentIntentionLabel = null;
    JLabel plansLabel = null;
    JLabel inboxLabel = null;
    JLabel outboxLabel = null;
    JLabel[] allLabels;
    String STAGESTRING = "Stage: ";
    String AGENTSSTRING = "Agents: ";
    String AGENTNAMESTRING = "Agent Name: ";
    String BELIEFSSTRING = "Beliefs: ";
    String GOALSSTRING = "Goals: ";
    String INTENTIONSSTRING = "Intentions: ";
    String CURRENTINTENTIONSTRING = "Current Intention: ";
    String PLANSSTRING = "Plans: ";
    String INBOXSTRING = "Inbox: ";
    String OUTBOXSTRING = "Outbox: ";
    String[] labelStrings;
    String[] mapLabelStrings;
    List<List<String>> listOfAttributes;

    private String currentAgent;
    private List<String> listOfCurrentAgents;

    //This is when each element of a list is actually a map
    //Therefore allowing you to get the key and the value of each element of the map
    //When the information is stored in a map, it's returned in a separate function
    private boolean[] isMap = {
            false,      //Stage
            true,       //agents
            false,      //agentName
            true,       //beliefs
            true,       //goals
            false,      //intentions
            false,      //current intention
            true,       //rel plans
            false,      //inbox
            false       //outbox
    };


    private GwenToolWindowContent gwenToolWindowContent;

    public BGIViewer(GwenToolWindowContent gwenToolWindowContent){
        super();
        agentsAdded = false;
        listOfCurrentAgents = new ArrayList<String>();

        this.gwenToolWindowContent = gwenToolWindowContent;

        setLayout(new GridBagLayout());

        addComboBox();

        //Initialise Labels and give them their default strings
        allLabels = new JLabel[]{stageLabel, agentsLabel, agentNameLabel, beliefsLabel, goalsLabel,
                intentionsLabel, currentIntentionLabel, plansLabel, inboxLabel, outboxLabel};
        labelStrings = new String[]{STAGESTRING, AGENTSSTRING, AGENTNAMESTRING, BELIEFSSTRING, GOALSSTRING,
                INTENTIONSSTRING, CURRENTINTENTIONSTRING, PLANSSTRING, INBOXSTRING, OUTBOXSTRING};
        //Any label that can be a map
        mapLabelStrings = new String[]{AGENTSSTRING, BELIEFSSTRING, GOALSSTRING, PLANSSTRING};
        for(int i=0; i<allLabels.length; i++){
            allLabels[i] = new JLabel(labelStrings[i]);
            allLabels[i].setHorizontalAlignment(SwingConstants.LEFT);
            addComponent(this, allLabels[i], 0, i+1, 1, 1);
        }
        listOfAttributes = new ArrayList<List<String>>();
        for(int i=0; i<allLabels.length; i++){
            //Make an arrayList for each attribute
            listOfAttributes.add(new ArrayList<String>());
        }
        allLabels[1].setVisible(false);
    }

    //Code for the combobox and combobox label
    private void addComboBox(){
        JPanel comboBoxPanel = new JPanel();
        comboBoxPanel.setLayout(new GridBagLayout());

        agentComboBoxLabel = new JLabel("Select an Agent: ");
        addComponent(comboBoxPanel, agentComboBoxLabel, 0, 0, 1, 1);
        //Initialise and add combobox
        agentComboBox = new ComboBox<String>();
        agentComboBox.setSize(200, 10);
        agentComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                ComboBox<String> sourceComboBox = (ComboBox<String>) e.getSource();
                String selectedItem = (String) sourceComboBox.getSelectedItem();
                changeAgentSelected(selectedItem);
            }
        });
        addComponent(comboBoxPanel, agentComboBox, 1, 0, 1, 1);

        addComponent(this, comboBoxPanel, 0, 0, 1, 1);
    }

    //Change which step number is displayed
    //Get the step that processed the selected agent last
    //So you're getting the most up to date details for that agent.
    private void changeAgentSelected(String agentToChangeTo){
        //Make sure you don't select the agent that is already being displayed
        if(!agentToChangeTo.equals(currentAgent)){
            boolean agentFound = false;
            //Get the step number of the step that last processed this agent
            for(int i=listOfCurrentAgents.size()-1; i>=0; i--){
                String agent = listOfCurrentAgents.get(i);
                if(agent.equals(agentToChangeTo)){
                    //Do i+1 because cycle number starts at 1, but list indices start at 0
                    gwenToolWindowContent.getSlider().setValue(i+1);
                    updateCycleNumber(i+1);
                    currentAgent = agent;
                    agentFound = true;
                    break;
                }
            }
            //The agent doesn't have a step in which it's processed yet
            //So change the selected item in the dropdown back to what it was before the user changes it
            if(!agentFound){
                agentComboBox.setSelectedIndex(listOfCurrentAgents.indexOf(currentAgent));
            }

        }
    }


    //Add component to grid bag layout.
    private void addComponent(Container container, Component component, int column, int row, int width, int height){
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = column;
        constraints.gridy = row;
        constraints.gridwidth = width;
        constraints.gridheight = height;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5,5,5,5);
        container.add(component, constraints);
    }

    //Called when the tree is changed
    //The parameter should be the new tree obtained
    public void updateWindow(XDebuggerTree tree) {
        this.tree = tree;
        DebugTreeUtils.setBGIViewer(this);
        sendInfoGet();
    }

    //Called when the 'Change Cycle Number' button is pressed
    //Changes values displayed in BGIViewer to the cycleNumber inputted
    public void updateCycleNumber(int cycleNumber){
        for(int i=0; i<listOfAttributes.size(); i++){
            //Each list in listOfAttributes is the history for ONE attribute
            //So get the same position in each attribute list for all the attributes at this cycle number
            List<String> listForAttribute = listOfAttributes.get(i);
            allLabels[i].setText(listForAttribute.get(cycleNumber-1));
            if(labelStrings[i].equals(AGENTNAMESTRING)){
                agentComboBox.setSelectedIndex(getIndex(agents, listOfCurrentAgents.get(cycleNumber-1)));
                currentAgent = listOfCurrentAgents.get(cycleNumber-1);
            }
        }
    }


    //Calls the find function on the tree
    //Response is returned to a different function
    private void sendInfoGet(){
        setLabelsLoading();
        String[][] findArray = {
                {"stage", "name"},
                {"this", "fMAS", "fAgents"},
                {"this", "fAgName"},
                {"this", "bbmap", "0", "value", "belsMap"},
                {"this", "gbmap", "0", "value", "goalMap"},
                {"this", "Is"},
                {"this", "I"},
                {"this", "plmap", "0", "value", "relPlans"},
                {"this", "Inbox"},
                {"this", "Outbox"}
        };
        //This is for when you want multiple data items to be returned
        //For Is (Intentions) you want to find all intentions and return them
        //Used in the respondForFind method in DebugTreeUtils
        boolean[] allowChildren = {
                false,      //stage
                true,       //agents
                false,      //agent name
                true,       //beliefs
                true,       //goals
                true,       //intentions
                false,      //current intention
                true,       //plans
                true,       //inbox
                true        //outbox
        };
        DebugTreeUtils.findInTree(tree.getRoot(), findArray, allowChildren, isMap, labelStrings);
    }

    private void setLabelsLoading(){
        for(int i=0; i<allLabels.length; i++){
            allLabels[i].setText(labelStrings[i] + "Loading ...");
        }
    }

    //Called from DebugTreeUtils with the information requested by the 'SendInfoGet' method
    //Sets the labels to the new values
    //Add the new values to the 2D list
    public void receiveInfoGet(String[][] returnArray){
        StringBuilder textToSet;
        for(int i=0; i<returnArray.length; i++){
            if(isMap[i]){
                continue;
            }
            textToSet = new StringBuilder("<html><b>" + labelStrings[i] + "</b><br/>" + returnArray[i][0]);
            if(returnArray[i].length > 1){
                for(int j=1; j<returnArray[i].length; j++){
                    //If there are multiple entries returned
                    //Display each one separated by a new line
                    textToSet.append("<br/>").append(returnArray[i][j]);
                }
            }
            textToSet.append("</html>");
            allLabels[i].setText(textToSet.toString());
            listOfAttributes.get(i).add(textToSet.toString());
            setCurrentAgentName(i, returnArray);
        }
    }

    //Change which agent name is selected when you advance the program
    //Can't update the combo box here because the list of agents is only returned in receiveMapNodeInfo
    //Which is always called after receiveInfoGet.
    private void setCurrentAgentName(int index, String[][] returnArray){
        if(labelStrings[index].equals(AGENTNAMESTRING)){
            if(returnArray[index].length > 0){
                currentAgent = returnArray[index][0];
                listOfCurrentAgents.add(currentAgent);
            }
        }
    }


    //Called from DebugTreeUtils
    //Each label may need something a bit different
    //For example for agents I only need the raw value for the key for each agent
    public void receiveMapNodeInfo(List<List<List<String>>> mapNodeChildren, List<String> labelStringsFound){
        //If label not covered, need to set it to not having any values. Default value of boolean is false
        boolean[] labelsCovered = new boolean[mapLabelStrings.length];
        for(int i=0; i<mapNodeChildren.size(); i++){
            //Each array here corresponds to a single label string
            String labelString = labelStringsFound.get(i);
            String textToSet = "<html><b>" + labelString + "</b></html>";
            String[] valForEachElement = new String[0];
            if(labelString.equals("Agents: ")){
                //Just want the key for each agent
                valForEachElement = getValForEachAgent(mapNodeChildren.get(i), 0);
                addToAgentDropdown(valForEachElement);
                agentComboBox.setSelectedIndex(getIndex(agents, currentAgent));
                labelsCovered[0] = true;
            }else if(labelString.equals("Beliefs: ")){
                //Just want value for each belief not key
                valForEachElement = getValForEachAgent(mapNodeChildren.get(i), 1);
                labelsCovered[1] = true;
            }else if(labelString.equals("Goals: ")){
                //Just want the value for each goal not key
                valForEachElement = getValForEachAgent(mapNodeChildren.get(i), 0);
                labelsCovered[2] = true;
            }else if(labelString.equals("Plans: ")){
                //Just want value for each plan
                valForEachElement = getValForEachAgent(mapNodeChildren.get(i), 1);
                labelsCovered[3] = true;
            }
            textToSet = getTextForList(valForEachElement, labelString);
            int index = getIndex(labelStrings, labelString);
            allLabels[index].setText(textToSet);
            listOfAttributes.get(index).add(textToSet);
        }
        //Write default values to labels with no items
        writeLabelsNoItems(labelsCovered);

        //Notify gwenToolWindowContent saying that the values have loaded
        gwenToolWindowContent.cycleComplete();
    }

    //Get raw value for 'key' for each agent. Return as string array.
    private String[] getValForEachAgent(List<List<String>> agentsList, int index){
        List<String> keyForEachAgent = new ArrayList<>();
        for(List<String> agent : agentsList){
            //'Key' raw value for each agent is the first item in each array
            keyForEachAgent.add(agent.get(index));
        }
        return keyForEachAgent.toArray(new String[0]);
    }

    //List of items is input
    //Output is string that can be assigned to a label (with line breaks etc)
    private String getTextForList(String[] array, String labelString){
        StringBuilder stringBuilder = new StringBuilder("<html><b>" + labelString + "</b>");
        for(int i=0; i<array.length; i++){
            stringBuilder.append("<br/>").append(array[i]);
        }
        stringBuilder.append("</html>");
        return stringBuilder.toString();
    }

    private int getIndex(String[] array, String element){
        for(int i=0; i<array.length; i++){
            if(element.equals(array[i])){
                return i;
            }
        }
        return -1;
    }

    //Any labels that do not have values returned in receiveMapNodeInfo should have 'no items' written to
    private void writeLabelsNoItems(boolean[] labelsCovered){
        for(int i=0; i<labelsCovered.length; i++){
            if(!labelsCovered[i]){
                String labelString = mapLabelStrings[i];
                String textToSet = "<html><b>" + labelString + "</b><br/>No Items</html>";
                int index = getIndex(labelStrings, labelString);
                allLabels[index].setText(textToSet);
                listOfAttributes.get(index).add(textToSet);
            }
        }
    }

    //Adds each agent name to the agent dropdown.
    private void addToAgentDropdown(String[] agents){
        if(!agentsAdded){
            this.agents = agents.clone();
            for(int i=0; i<agents.length; i++){
                agentComboBox.addItem(agents[i]);
            }
            //Ensures agents are only added to the dropdown one time
            agentsAdded = true;
        }
    }



}
