package GwenDebugger;

import GwendolenToolWindow.BGIViewer;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
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

    /*
    private static void respondForFindNodeFromParent(XDebuggerTreeNode node, int receiver){
        switch(receiver){
            case 0 -> bgiViewer.receiveStageName(node);
            default -> System.out.println("None");
        }
    }

     */


    public static void findInTree(XDebuggerTreeNode rootNode, String[][] stringArrayJagged, boolean[] allowChildren){
        //Get max length of any array in the 2D array
        int maxLen = getLongestArrayIn2DArray(stringArrayJagged);

        //Make an array for the indexes of each node in the string array
        //Make the default value -1
        int[][] indexArrayJagged = new int[stringArrayJagged.length][];
        for(int i=0; i<stringArrayJagged.length; i++){
            indexArrayJagged[i] = new int[stringArrayJagged[i].length];
            for(int j=0; j<stringArrayJagged[i].length; j++){
                indexArrayJagged[i][j] = -1;
            }

        }

        treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, 0, allowChildren);

    }

    private static int getLongestArrayIn2DArray(String[][] stringArray2D){
        int maxLen = 0;
        for(int i=0; i<stringArray2D.length; i++){
            int array1DLen = stringArray2D[i].length;
            if(array1DLen > maxLen){
                maxLen = array1DLen;
            }
        }
        return maxLen;
    }

    private static void treeFindRecursive(XDebuggerTreeNode rootNode, String[][] stringArrayJagged,
                                          int[][] indexArrayJagged, int maxLen, int currentIndex, boolean[] allowChildren){
        // See which nodes have their children loaded and which don't
        // Add those that aren't loaded to an array

        //currentIndex is the index in each 1D array that will be used
        List<XDebuggerTreeNode> nodesToLoad = new ArrayList<>();
        for(int i=0; i<indexArrayJagged.length; i++){
            int[] currentIndexArray = indexArrayJagged[i];
            //Get node at the last loaded node in the array
            XDebuggerTreeNode node = getNodeAtPath(rootNode, currentIndexArray, currentIndex);
            if(!areChildrenLoaded(node)){
                nodesToLoad.add(node);
            }
        }

        // If there are nodes whose children haven't loaded yet
        // Then initiate the load by doing getChildCount()
        // Then wait a certain amount of time before calling the method again

        if(nodesToLoad.size() > 0){
            //Need to wait for these nodes to load
            for(int i=0; i<nodesToLoad.size(); i++){
                //Initiate load for each node (that's what getChildCount() does)
                nodesToLoad.get(i).getChildCount();
            }
            //Wait period of time then check again.
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            int finalCurrentIndex = currentIndex;
            executorService.schedule(() -> {
                //This is called after the wait
                treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, finalCurrentIndex, allowChildren);
            }, 500, TimeUnit.MILLISECONDS);
        }else{
            // The children of the nodes have loaded
            // So go through and find the nodes you want
            // Add the positions of the nodes you want to the indexArray
            // Increment currentIndex and either call method again or call method to return info to where it was called from

            for(int i=0; i<indexArrayJagged.length; i++){
                int nextNodePos = seeWhereNextNodeIs(indexArrayJagged[i], stringArrayJagged[i], currentIndex, rootNode);
                //If nextNodePos == -1 then node not found.
                if(!isIndexArrayFull(indexArrayJagged[i])){
                    indexArrayJagged[i][currentIndex] = nextNodePos;
                }
            }

            currentIndex = currentIndex + 1;
            if(currentIndex >= maxLen){
                respondForFind(indexArrayJagged, rootNode, allowChildren);
            }
            //Don't need to wait before calling the method again
            //It's likely that the children of these nodes found don't have their children loaded
            //So the wait will just happen in this function call.
            treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, currentIndex, allowChildren);
        }
    }

    //Get the node at location 'path' up to currentIndex. So will get the parent of the node we're inspecting
    private static XDebuggerTreeNode getNodeAtPath(XDebuggerTreeNode rootNode, int[] currentIndexArray, int currentIndex){
        if(currentIndex == 0){
            return rootNode;
        }else{
            XDebuggerTreeNode currentNode = rootNode;
            for(int i=0; i<currentIndex; i++){
                currentNode = (XDebuggerTreeNode) currentNode.getChildAt(currentIndexArray[i]);
            }
            return currentNode;
        }
    }

    //Are this node's children loaded?
    private static boolean areChildrenLoaded(XDebuggerTreeNode node){
        List<XDebuggerTreeNode> listOfChildren = (List<XDebuggerTreeNode>) node.getChildren();
        if(listOfChildren.size() == 1){
            if(listOfChildren.get(0).toString().equals("Collecting data...")){
                return false;
            }
        }
        return true;
    }

    //Will take the path we have so far (from index array)
    //Then see what position the newly found node is
    //From the last node in the path we have so far
    private static int seeWhereNextNodeIs(int[] indexArray, String[] stringArrayJagged, int currentIndex,
                                           XDebuggerTreeNode rootNode){
        //Get node at end of path we have so far
        XDebuggerTreeNode node = getNodeAtPath(rootNode, indexArray, currentIndex);
        if(!node.isLeaf()){
            //We know the children are loaded
            List<XDebuggerTreeNode> listOfChildren = (List<XDebuggerTreeNode>) node.getChildren();
            //Get the next node in the string array
            for(int i=0; i<listOfChildren.size(); i++){
                if(listOfChildren.get(i).toString().equals(stringArrayJagged[currentIndex])){
                    return i;
                }
            }
        }
        return -1;
    }

    private static boolean isIndexArrayFull(int[] indexArray){
        if(indexArray[indexArray.length - 1] == -1){
            return false;
        }else{
            return true;
        }
    }

    private static void respondForFind(int[][] indexArrayJagged, XDebuggerTreeNode rootNode, boolean[] allowChildren){
        String[][] returnArray = new String[indexArrayJagged.length][];
        for(int i=0; i<indexArrayJagged.length; i++){
            //Need the + 1 here because otherwise it'll stop one short
            XDebuggerTreeNode node = getNodeAtPath(rootNode, indexArrayJagged[i], indexArrayJagged[i].length);

            String[] returnedItem;
            if(allowChildren[i]){
                //Node has children, all of which need to be returned
                //That's why each node reponse is returned as an array
                //For situtations like for {"this", "Is"} (Intentions)
                List<XDebuggerTreeNode> children = (List<XDebuggerTreeNode>) node.getChildren();
                List<String> childValueList = new ArrayList<String>();
                for(XDebuggerTreeNode child : children){
                    XValueNodeImpl valueNode = (XValueNodeImpl) child;
                    childValueList.add(valueNode.getRawValue());
                }
                returnedItem = childValueList.toArray(new String[0]);
            }else{
                //Just want to return the value
                XValueNodeImpl valueNode = (XValueNodeImpl) node;
                returnedItem = new String[]{valueNode.getRawValue()};
            }
            returnArray[i] = returnedItem;
        }
        bgiViewer.receiveInfoGet(returnArray);
    }

}
