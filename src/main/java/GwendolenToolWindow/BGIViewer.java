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
    JLabel intentionsLabel = null;
    JLabel inboxLabel = null;
    JLabel outboxLabel = null;
    JLabel[] allLabels;
    String STAGESTRING = "Stage: ";
    String AGENTNAMESTRING = "Agent Name: ";
    String INTENTIONSSTRING = "Intentions: ";
    String INBOXSTRING = "Inbox: ";
    String OUTBOXSTRING = "Outbox: ";
    String[] labelStrings;
    List<List<String>> listOfAttributes;

    private GwenToolWindowContent gwenToolWindowContent;

    public BGIViewer(GwenToolWindowContent gwenToolWindowContent){
        super();

        this.gwenToolWindowContent = gwenToolWindowContent;

        //Make labels be placed one below another
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Initialise Labels and give them their default strings
        allLabels = new JLabel[]{stageLabel, agentNameLabel, intentionsLabel, inboxLabel, outboxLabel};
        labelStrings = new String[]{STAGESTRING, AGENTNAMESTRING, INTENTIONSSTRING, INBOXSTRING, OUTBOXSTRING};
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
                {"this", "Is"},
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
                true
        };
        DebugTreeUtils.findInTree(tree.getRoot(), findArray, allowChildren);
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
        //Notify gwenToolWindowContent saying that the values have loaded
        gwenToolWindowContent.cycleComplete();
    }





}
