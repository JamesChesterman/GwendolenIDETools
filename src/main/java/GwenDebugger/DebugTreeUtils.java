package GwenDebugger;

import GwendolenToolWindow.BGIViewer;
import com.intellij.xdebugger.impl.ui.tree.XDebuggerTree;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;

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

    private static void respondForFindNodeFromParent(XDebuggerTreeNode node, int receiver){
        switch(receiver){
            case 0 -> bgiViewer.receiveStageName(node);
            default -> System.out.println("None");
        }
    }


    public static void findInTree(XDebuggerTreeNode rootNode, String[][] stringArrayJagged){
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

        treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, 0);

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
                                          int[][] indexArrayJagged, int maxLen, int currentIndex){
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
                treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, finalCurrentIndex);
            }, 500, TimeUnit.MILLISECONDS);
        }else{
            //Can find the positions for each node
            //Add the position to the correct index array
            for(int i=0; i<indexArrayJagged.length; i++){
                int nextNodePos = seeWhereNextNodeIs(indexArrayJagged[i], stringArrayJagged[i], currentIndex, rootNode);
                if(nextNodePos == -1){
                    System.out.println("ERROR - NODE NOT FOUND!");
                }else{
                    if(!isIndexArrayFull(indexArrayJagged[i])){
                        indexArrayJagged[i][currentIndex] = nextNodePos;
                    }
                }
            }

            currentIndex = currentIndex + 1;
            if(currentIndex >= maxLen){
                System.out.println("DONE!"); //Need to call something that calls to BGIViewer with results.
            }
            //Don't need to wait before calling the method again
            //It's likely that the children of these nodes found don't have their children loaded
            //So the wait will just happen in this function call.
            treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, currentIndex);
        }
    }

    //Get the node at location 'path' up to currentIndex. So will get the parent of the node we're inspecting
    private static XDebuggerTreeNode getNodeAtPath(XDebuggerTreeNode rootNode, int[] currentIndexArray, int currentIndex){
        if(currentIndex == 0){
            return rootNode;
        }else{
            XDebuggerTreeNode currentNode = rootNode;
            for(int i=0; i<currentIndex; i++){
                currentNode = (XDebuggerTreeNode) rootNode.getChildAt(currentIndexArray[i]);
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
