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

    private void setLabelsLoading(){

    }

    private void sendInfoGet(){
        String[][] findArray = {
                {"this", "fAgName"},
                {"this", "Is"},
                {"stage", "name"}
        };

    }


    public void updateWindow(XDebuggerTree tree) {
        this.tree = tree;
        DebugTreeUtils.setBGIViewer(this);

    }

}
