package GwenDebugger;

import GwendolenToolWindow.BGIViewer;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DebugTreeUtils {

    int index;
    private static BGIViewer bgiViewer;
    private DebugTreeUtils(){
        //Private constructor prevents instantiation
    }

    //Need to set BGIViewer to the correct object before doing any findNodeFromParent calls from it
    public static void setBGIViewer(BGIViewer bgiViewerToGive){
        bgiViewer = bgiViewerToGive;
    }

    private static void respondForFindNodeFromParent(XDebuggerTreeNode node, int receiver){
        switch(receiver){
            case 0 -> bgiViewer.receiveStageName(node);
            default -> System.out.println("None");
        }
    }



    //Find node with certain name in tree using parent node
    public static void findNodeFromParent(XDebuggerTreeNode parentNode, String nameToLookFor, int receiver){
        //getChildCount will load the values of the children
        parentNode.getChildCount();

        //Then need to wait for these values to load
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(() -> {
            //What to do when values loaded (after wait)
            List<XDebuggerTreeNode> listOfChildren = (List<XDebuggerTreeNode>) parentNode.getChildren();
            int index = -1;
            XDebuggerTreeNode node = null;
            for(int i=0; i<listOfChildren.size(); i++){
                node = listOfChildren.get(i);
                if(node.toString().equals(nameToLookFor)){
                    index = i;
                    break;
                }
            }
            if(index == -1){
                System.out.println("ERROR - NAME NOT FOUND IN FINDNODEFROMPARENT");
            }else{
                //Decide where to send response (call method with node found)
                respondForFindNodeFromParent(node, receiver);
            }
        }, 500, TimeUnit.MILLISECONDS);
    }
}
