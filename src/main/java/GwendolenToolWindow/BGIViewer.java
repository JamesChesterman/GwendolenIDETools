package GwendolenToolWindow;

import GwenDebugger.DebugTreeUtils;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class BGIViewer extends JPanel {
    XDebuggerTree tree;
    JLabel stageLabel = null;
    JLabel agentNameLabel = null;
    JLabel agentsLabel = null;
    JLabel beliefsLabel = null;
    JLabel goalsLabel = null;
    JLabel intentionsLabel = null;
    JLabel plansLabel = null;
    JLabel inboxLabel = null;
    JLabel outboxLabel = null;
    JLabel[] allLabels;
    String STAGESTRING = "Stage: ";
    String AGENTNAMESTRING = "Agent Name: ";
    String AGENTSSTRING = "Agents: ";
    String BELIEFSSTRING = "Beliefs: ";
    String GOALSSTRING = "Goals: ";
    String INTENTIONSSTRING = "Intentions: ";
    String PLANSSTRING = "Plans: ";
    String INBOXSTRING = "Inbox: ";
    String OUTBOXSTRING = "Outbox: ";
    String[] labelStrings;
    String[] mapLabelStrings;
    List<List<String>> listOfAttributes;

    //This is when each element of a list is actually a map
    //Therefore allowing you to get the key and the value of each element of the map
    //When the information is stored in a map, it's returned in a separate function
    private boolean[] isMap = {
            false,
            false,
            true,
            true,
            true,
            false,
            true,
            false,
            false
    };

    private GwenToolWindowContent gwenToolWindowContent;

    public BGIViewer(GwenToolWindowContent gwenToolWindowContent){
        super();

        this.gwenToolWindowContent = gwenToolWindowContent;

        //Make labels be placed one below another
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Initialise Labels and give them their default strings
        allLabels = new JLabel[]{stageLabel, agentNameLabel, agentsLabel, beliefsLabel, goalsLabel,
                intentionsLabel, plansLabel, inboxLabel, outboxLabel};
        labelStrings = new String[]{STAGESTRING, AGENTNAMESTRING, AGENTSSTRING, BELIEFSSTRING, GOALSSTRING,
                INTENTIONSSTRING, PLANSSTRING, INBOXSTRING, OUTBOXSTRING};
        //Any label that can be a map
        mapLabelStrings = new String[]{AGENTSSTRING, BELIEFSSTRING, GOALSSTRING, PLANSSTRING};
        for(int i=0; i<allLabels.length; i++){
            allLabels[i] = new JLabel(labelStrings[i]);
            add(allLabels[i]);
        }
        listOfAttributes = new ArrayList<List<String>>();
        for(int i=0; i<allLabels.length; i++){
            //Make an arrayList for each attribute
            listOfAttributes.add(new ArrayList<String>());
        }


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
        }
    }


    //Calls the find function on the tree
    //Response is returned to a different function
    private void sendInfoGet(){
        setLabelsLoading();
        String[][] findArray = {
                {"stage", "name"},
                {"this", "fAgName"},
                {"this", "fMAS", "fAgents"},
                {"this", "bbmap", "0", "value", "belsMap"},
                {"this", "gbmap", "0", "value", "goalMap"},
                {"this", "Is"},
                {"this", "plmap", "0", "value", "relPlans"},
                {"this", "Inbox"},
                {"this", "Outbox"}
        };
        //This is for when you want multiple data items to be returned
        //For Is (Intentions) you want to find all intentions and return them
        //Used in the respondForFind method in DebugTreeUtils
        boolean[] allowChildren = {
                false,
                false,
                true,
                true,
                true,
                true,
                true,
                true,
                true
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





}
