package GwendolenToolWindow;

import GwenDebugger.DebugTreeUtils;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import javax.swing.*;

public class BGIViewer extends JPanel {
    XDebuggerTree tree;
    JLabel stageLabel = null;
    JLabel agentNameLabel = null;
    JLabel intentionsLabel = null;
    JLabel[] allLabels;
    String STAGESTRING = "Stage: ";
    String AGENTNAMESTRING = "Agent Name: ";
    String INTENTIONSSTRING = "Intentions: ";
    String[] labelStrings = {STAGESTRING, AGENTNAMESTRING, INTENTIONSSTRING};

    public BGIViewer(){
        super();
        //Make labels be placed one below another
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        //Initialise Labels and give them their default strings
        allLabels = new JLabel[]{stageLabel, agentNameLabel, intentionsLabel};
        labelStrings = new String[]{STAGESTRING, AGENTNAMESTRING, INTENTIONSSTRING};
        for(int i=0; i<allLabels.length; i++){
            allLabels[i] = new JLabel(labelStrings[i]);
            add(allLabels[i]);
        }

    }

    /*
    private void getStage(){
        stageReceived = false;
        nameReceived = false;
        stageLabel.setText("Stage: loading...");
       // DebugTreeUtils.findNodeFromParent(tree.getRoot(), "stage", 0);
        //DebugTreeUtils.checkIfChildrenLoaded(tree.getRoot(), "stage", 0);
        String[][] findArray = {
                {"this", "fAgName"},
                {"this", "Is"},
                {"stage", "name"}
        };
        DebugTreeUtils.findInTree(tree.getRoot(), findArray);
    }

    public void receiveStageName(XDebuggerTreeNode nodeReceived){
        if(!stageReceived){
            //DebugTreeUtils.findNodeFromParent(nodeReceived, "name", 0);
            stageReceived = true;
        }else if(!nameReceived){
            XValueNodeImpl valueNode = (XValueNodeImpl) nodeReceived;
            stageLabel.setText("Stage: " + valueNode.getRawValue());
            nameReceived = true;
        }
    }

     */

    //Called when the tree is changed
    //The parameter should be the new tree obtained
    public void updateWindow(XDebuggerTree tree) {
        this.tree = tree;
        DebugTreeUtils.setBGIViewer(this);
        sendInfoGet();
    }

    //Calls the find function on the tree
    //Response is returned to a different function
    private void sendInfoGet(){
        setLabelsLoading();
        String[][] findArray = {
                {"stage", "name"},
                {"this", "fAgName"},
                {"this", "Is"}
        };
        //This is for when you want multiple data items to be returned
        //For Is (Intentions) you want to find all intentions and return them
        //Used in the respondForFind method in DebugTreeUtils
        boolean[] allowChildren = {
                false,
                false,
                false
        };
        DebugTreeUtils.findInTree(tree.getRoot(), findArray, allowChildren);
    }

    private void setLabelsLoading(){
        for(int i=0; i<allLabels.length; i++){
            allLabels[i].setText(labelStrings[i] + "Loading ...");
        }
    }

    public void receiveInfoGet(String[][] returnArray){
        for(int i=0; i<returnArray.length; i++){
            allLabels[i].setText(labelStrings[i] + returnArray[i][0]);
        }
    }





}
