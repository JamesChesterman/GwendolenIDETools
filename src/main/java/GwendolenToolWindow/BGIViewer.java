package GwendolenToolWindow;

import GwenDebugger.DebugTreeUtils;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import javax.swing.*;

public class BGIViewer extends JPanel {
    boolean stageReceived = false;
    boolean nameReceived = false;
    XDebuggerTree tree;
    JLabel stageLabel;

    public BGIViewer(){
        super();

        stageLabel = new JLabel("Stage: ");
        add(stageLabel);
    }

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
            DebugTreeUtils.findNodeFromParent(nodeReceived, "name", 0);
            stageReceived = true;
        }else if(!nameReceived){
            XValueNodeImpl valueNode = (XValueNodeImpl) nodeReceived;
            stageLabel.setText("Stage: " + valueNode.getRawValue());
            nameReceived = true;
        }
    }

    public void updateWindow(XDebuggerTree tree){
        this.tree = tree;
        DebugTreeUtils.setBGIViewer(this);
        getStage();
    }

    public void getInfo(){

    }

}
