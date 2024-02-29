package GwenDebugger;

import GwendolenToolWindow.BGIViewer;
import GwendolenToolWindow.PlansViewer;
import com.intellij.xdebugger.frame.XValue;
import com.intellij.xdebugger.impl.ui.tree.nodes.XDebuggerTreeNode;
import com.intellij.xdebugger.impl.ui.tree.nodes.XValueNodeImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DebugTreeUtils {
    private static BGIViewer bgiViewer;
    private static PlansViewer plansViewer;
    private static String classToReturnTo;
    private static final String plansClass = "PlansViewer";
    private static final String bgiClass = "BGIViewer";
    private static final int TIMEPERIOD = 50;        //In milliseconds

    private DebugTreeUtils(){
        //Private constructor prevents instantiation
    }

    public static int getTimePeriod(){
        return TIMEPERIOD;
    }

    //Need to set BGIViewer to the correct object before doing any findNodeFromParent calls from it
    //Should always be called before findInTree (so classToReturnTo can have its value assigned).
    public static void setBGIViewer(BGIViewer bgiViewerToGive){
        classToReturnTo = bgiClass;
        bgiViewer = bgiViewerToGive;
    }

    //Need to set PlansViewer to the correct object
    //Should always be called before findInTree (so classToReturnTo can have its value assigned).
    public static void setPlansViewer(PlansViewer plansViewerToGive){
        classToReturnTo = plansClass;
        plansViewer = plansViewerToGive;
    }

    public static void findInTree(XDebuggerTreeNode rootNode, String[][] stringArrayJagged, boolean[] allowChildren,
                                  boolean[] isMap, String[] labelStrings){
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

        treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, 0, allowChildren, isMap, labelStrings);

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
                                          int[][] indexArrayJagged, int maxLen, int currentIndex, boolean[] allowChildren,
                                          boolean[] isMap, String[] labelStrings){
        // See which nodes have their children loaded and which don't
        // Add those that aren't loaded to an array

        //currentIndex is the index in each 1D array that will be used
        List<XDebuggerTreeNode> nodesToLoad = new ArrayList<>();
        for(int i=0; i<indexArrayJagged.length; i++){
            int[] currentIndexArray = indexArrayJagged[i];
            //No need to consider nodes which have their paths filled, or where you can't find a certain child
            if(isIndexArrayFull(currentIndexArray) || isIndexArrayDone(currentIndexArray)){
                continue;
            }
            //Get node at the last loaded node in the array
            XDebuggerTreeNode node = getNodeAtPath(rootNode, currentIndexArray, currentIndex);
            if(!areChildrenLoaded(node)){
                if(!node.isLeaf()){
                    nodesToLoad.add(node);
                }
            }
        }

        // If there are nodes whose children haven't loaded yet
        // Then initiate the load by doing getChildCount()
        // Then wait a certain amount of time before calling the method again

        if(nodesToLoad.size() > 0){
            if(currentIndex < maxLen){
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
                    treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, finalCurrentIndex, allowChildren, isMap, labelStrings);
                }, TIMEPERIOD, TimeUnit.MILLISECONDS);
                executorService.shutdown();
            }
        }else{
            // The children of the nodes have loaded
            // So go through and find the nodes you want
            // Add the positions of the nodes you want to the indexArray
            // Increment currentIndex and either call method again or call method to return info to where it was called from

            for(int i=0; i<indexArrayJagged.length; i++){
                if(isIndexArrayFull(indexArrayJagged[i]) || isIndexArrayDone(indexArrayJagged[i])){
                    continue;
                }
                int nextNodePos = seeWhereNextNodeIs(indexArrayJagged[i], stringArrayJagged[i], currentIndex, rootNode);
                //If nextNodePos == -1 then node not found.
                indexArrayJagged[i][currentIndex] = nextNodePos;
            }

            currentIndex = currentIndex + 1;
            if(currentIndex >= maxLen){
                respondForFind(indexArrayJagged, rootNode, allowChildren, isMap, labelStrings);
            }else{
                //Don't need to wait before calling the method again
                //It's likely that the children of these nodes found don't have their children loaded
                //So the wait will just happen in this function call.
                treeFindRecursive(rootNode, stringArrayJagged, indexArrayJagged, maxLen, currentIndex, allowChildren, isMap, labelStrings);
            }
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
        if(!listOfChildren.isEmpty()){
            String childText = listOfChildren.get(0).getText().toString();
            if(childText.contains("Collecting data...")){
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
        return -2;      //Return -2 to indicate that the next node couldn't be found, and to ignore this node from now on
    }

    private static boolean isIndexArrayFull(int[] indexArray){
        if(indexArray[indexArray.length - 1] == -1){
            return false;
        }else{
            return true;
        }
    }

    //Index array is done with if there is a -2 in it
    private static boolean isIndexArrayDone(int[] indexArray){
        for(int i=0; i<indexArray.length; i++){
            if (indexArray[i] == -2) {
                return true;
            }
        }
        return false;
    }


    //Calls a method in bgiViewer / plansViewer with a 2D array of items to be returned
    //Each array in the 2D array is either: the single raw value of the node requested
    //Or the raw value of every child belonging to the node requested
    //May need to wait for loading
    //Also keeps a list of map nodes (nodes with a key and a value) which are then processed in another method
    private static void respondForFind(int[][] indexArrayJagged, XDebuggerTreeNode rootNode, boolean[] allowChildren,
                                       boolean[] isMap, String[] labelStrings){
        String[][] returnArray = new String[indexArrayJagged.length][];
        List<XDebuggerTreeNode> nodesToLoad = new ArrayList<>();
        List<XDebuggerTreeNode> mapNodes = new ArrayList<>();
        List<String> mapLabelStrings = new ArrayList<>();
        for(int i=0; i<indexArrayJagged.length; i++){
            if(isIndexArrayDone(indexArrayJagged[i])){
                returnArray[i] = new String[]{"No items"};
                continue;
            }

            XDebuggerTreeNode node = getNodeAtPath(rootNode, indexArrayJagged[i], indexArrayJagged[i].length);

            String[] returnedItem = null;
            if(allowChildren[i]){
                if(node.isLeaf()){
                    //When the node has no children to get (so size = 0)
                    returnedItem = new String[]{"No items"};
                    returnArray[i] = returnedItem;
                }else{
                    //Node has children, all of which need to be returned
                    //For situations like getting all intentions
                    if(areChildrenLoaded(node)){
                        returnedItem = addChildrenToList(node);
                        //Can't just put this return statement at the end
                        //Otherwise it will execute after the executorService schedule has been called for this function call
                        returnArray[i] = returnedItem;
                        //Node will only be a map when there are keys and values that need to be returned
                        if(isMap[i]){
                            mapNodes.add((XDebuggerTreeNode) node);
                            mapLabelStrings.add(labelStrings[i]);
                        }
                    }else{
                        //This will make the function be re-called after a wait
                        nodesToLoad.add(node);
                    }
                }
            }else{
                //Make returnedItem array just the single raw value
                XValueNodeImpl valueNode = (XValueNodeImpl) node;
                returnedItem = new String[]{valueNode.getRawValue()};
                returnArray[i] = returnedItem;
            }
        }

        //If there are any nodes to load, then wait TIMEPERIODms then call the function again
        //The node children loads have already been initiated where needed
        if(nodesToLoad.size() > 0){
            for(int i=0; i<nodesToLoad.size(); i++){
                //Initiate load for each node (that's what getChildCount() does)
                nodesToLoad.get(i).getChildCount();
            }
            //Wait period of time then check again
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.schedule(() -> {
                //This is called after the wait
                respondForFind(indexArrayJagged, rootNode, allowChildren, isMap, labelStrings);
            }, TIMEPERIOD, TimeUnit.MILLISECONDS);
            executorService.shutdown();
        }else{
            //If no nodes need their children loading, then everything can be sent to bgiViewer / plansViewer
            if(classToReturnTo.equals(plansClass)){
                plansViewer.receiveInfoGet(returnArray);
            }else{
                bgiViewer.receiveInfoGet(returnArray);
            }
            processMapNodes(mapNodes, mapLabelStrings);
        }
    }


    //Send the map values back in a separate method to avoid more complex method above
    //We already know the mapNodes' children will be loaded.
    //Now need to check if the children's children (usually key and value) are also loaded

    //Example: fAgents is a map. The fAgents node will be the mapNode. This node has children (one for each agent)
    //Then each of the children will have children, typically the key and the value
    //So array will be indexed on: node(List parent), childNode(Element of the list), childOfChildNode(Key / Value of
    //   list element)
    // mapLabelStrings are the labels passed from BGIViewer / plansViewer. They're just the labels associated with the mapNodes
    private static void processMapNodes(List<XDebuggerTreeNode> mapNodes, List<String> mapLabelStrings){
        List<XDebuggerTreeNode> nodesToLoad = new ArrayList<>();
        for(int i=0; i<mapNodes.size(); i++){
            List<XDebuggerTreeNode> children = (List<XDebuggerTreeNode>) mapNodes.get(i).getChildren();
            for(XDebuggerTreeNode child : children){
                if(!areChildrenLoaded(child) && !child.isLeaf()){
                    //Add node to array
                    nodesToLoad.add(child);
                }
            }
        }
        //If there are nodes left to load
        if(!nodesToLoad.isEmpty()){
            //Go through and initiate loads for each node (which are all children of the mapNodes)
            for(XDebuggerTreeNode node : nodesToLoad){
                node.getChildCount();
            }
            //Wait a delay then see if they're loaded
            ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
            executorService.schedule(() -> {
                //This is called after the wait
                processMapNodes(mapNodes, mapLabelStrings);
            }, 500, TimeUnit.MILLISECONDS);
            executorService.shutdown();
            //If there are no child nodes left to load
        }else{
            List<List<List<String>>> valuesOfMapNodeChildren = new ArrayList<>();
            //No more nodes to load. Get values of each node then call method in bgiViewer / plansViewer to send results
            for(int i=0; i<mapNodes.size(); i++){
                List<XDebuggerTreeNode> children = (List<XDebuggerTreeNode>) mapNodes.get(i).getChildren();
                List<List<String>> valuesOfListElementChildren = new ArrayList<>();
                for(int j=0; j<children.size(); j++){
                    List<XDebuggerTreeNode> childrenOfChild = (List<XDebuggerTreeNode>) children.get(j).getChildren();
                    List<String> valuesOfMapNodeChild = new ArrayList<>();
                    for(int k=0; k<childrenOfChild.size(); k++){
                        //Each of these should either be a key or a value (the value associated with each)
                        //E.g. the key of an element in fAgents list may have the value "medic"
                        XValueNodeImpl valueNode = (XValueNodeImpl) childrenOfChild.get(k);
                        valuesOfMapNodeChild.add(valueNode.getRawValue());
                    }
                    //Add values for each list element into a 2D array
                    valuesOfListElementChildren.add(valuesOfMapNodeChild);
                }
                //Add values for each list of children (each of which having a list of attributes)
                valuesOfMapNodeChildren.add(valuesOfListElementChildren);
            }

            if(classToReturnTo.equals(plansClass)){
                plansViewer.receiveMapInfo(valuesOfMapNodeChildren, mapLabelStrings);
            }else{
                bgiViewer.receiveMapNodeInfo(valuesOfMapNodeChildren, mapLabelStrings);
            }
        }
    }

    //Go through list of node's children
    //Add raw value of each node to list, return as array
    private static String[] addChildrenToList(XDebuggerTreeNode node){
        //Go through and add each child to returnedItem array
        List<XDebuggerTreeNode> children = (List<XDebuggerTreeNode>) node.getChildren();
        List<String> childValueList = new ArrayList<String>();
        for(XDebuggerTreeNode child : children){
            XValueNodeImpl valueNode = (XValueNodeImpl) child;
            childValueList.add(valueNode.getRawValue());
        }
        return childValueList.toArray(new String[0]);
    }

}