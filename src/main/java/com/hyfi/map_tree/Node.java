package com.hyfi.map_tree;
// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class Node {
    // private static final Logger log = LoggerFactory.getLogger(Node.class);   
    private String nodeName = null;
    // Node node = null;
    private List<Node> adjascentNodes = null;
    private Node nextShortestNode = null;
    private Node previousShortestNode = null;
    private Integer distanceToNextShortestNode = null;
    private Integer distanceToPreviousShortestNode = null;

    /**
     * Default constructor; creates a null node
     */
    public Node(){}
    /**
     * Create new node name and create a new node
     * @param newNodeName
     */
    public Node(String newNodeName)
    {
        this.nodeName = newNodeName;
        this.adjascentNodes = new ArrayList<Node>();        
    }
    /**
     * Create a new node, define the next and previous connected nodes
     * @param newNodeName
     * @param nextNode
     * @param previousNode
     */
    public Node(String newNodeName, Node nextNode, Node previousNode)
    {
        // this.node = new Node();
        this.adjascentNodes = new ArrayList<Node>();
        this.nodeName = newNodeName;
        this.nextShortestNode = nextNode;
        this.previousShortestNode = previousNode;
        this.adjascentNodes.add(previousNode);
        this.adjascentNodes.add(nextNode);
    }
    /**
     * Make a copy of a node and its adjascent node
     * @param node
     * @param adjNodes
     */
    public Node(String nodeName, List<Node> adjNodes)
    {
        this.adjascentNodes = new ArrayList<Node>();
        this.nodeName = nodeName;
        this.adjascentNodes = adjNodes;
    }
    /**
     * Gets the node thats the shortest in the list of
     * adjascent nodes
     * @param adjNodes
     * @return the next shortest node
     */
    private Node getNextShortest(List<Node> adjNodes)
    {   // create temp node
        Node n = null;
        // Collection<String,Node> adjascentNode_ = new Collection<E>(){
            
        // };
        Integer distance = Integer.MAX_VALUE;
        for (Node node : adjNodes) 
        {   // find the adjescent node with the shortest
            // compare the distance between each node and source node
            // and keep the shortest distance, now compare it to the 
            // adjascent node and assign the node with the shortest path
            n = new Node();
            if ((distance = Math.min(distance, node.getDistanceToPreviousShortestNode())) == node.getDistanceToPreviousShortestNode())
            {   // if this node has a shorter distance     
                this.distanceToNextShortestNode = node.getDistanceToPreviousShortestNode();
                n = node;
            }
        }
        return n;
    }
    /**
     * Get node name
     * @return
     */
    public String getNodeName() {
        return this.nodeName;
    }
    /**
     * Set node name
     * @param nodeName
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName;
    }
    /**
     * Get adjascent nodes
     * @return
     */
    public void setAdjascentNode(Node node)
    {
        this.adjascentNodes.add(node);
    }
    /**
     * Get list of adjascent nodes/edges by name
     * @return
     */
    public List<Node> getAdjascentNodes() {
        return this.adjascentNodes;
    }
    /**
     * Set adjascent nodes
     * @param adjascentNodes
     */
    public void setAdjascentNodes(List<Node> adjascentNodes) {
        this.adjascentNodes = adjascentNodes;
    }
    /**
     * Get next shortest node
     * @return
     */
    public Node getNextShortestNode() {
        return this.nextShortestNode;
    }
    /**
     * Set next shortest node
     * @param nextShortestNode
     */
    public void setNextShortestNode(Node nextShortestNode) {
        this.nextShortestNode = nextShortestNode;
    }
    /**
     * Get previous shortest node
     * @return
     */
    public Node getPreviousShortestNode() {
        return this.previousShortestNode;
    }
    /**
     * Set previous shortest node
     * @param previousShortestNode
     */
    public void setPreviousShortestNode(Node previousShortestNode) {
        this.previousShortestNode = previousShortestNode;
    }
    /**
     * Get distance to next shortest node
     * @return
     */
    public Integer getDistanceToNextShortestNode() {
        return this.distanceToNextShortestNode;
    }
    /**
     * Set distance to next shortest node
     */
    public void setDistanceToNextShortestNode(Integer distanceToNextShortestNode) {
        this.distanceToNextShortestNode = distanceToNextShortestNode;
    }
    /**
     * Set distance to next shortest node; & set next shortest node
     * @param distanceToNextShortestNode
     * @param nextShortestNode
     */
    public void setDistanceToNextShortestNode(Integer distanceToNextShortestNode, Node nextShortestNode){
        this.distanceToNextShortestNode = distanceToNextShortestNode;
        this.nextShortestNode = nextShortestNode;
    }
    /**
     * Get distance to previous shortest node
     * @return
     */
    public Integer getDistanceToPreviousShortestNode() {
        return this.distanceToPreviousShortestNode;
    }
    /**
     * Set distance to previous shortest node
     * @param distanceToPreviousShortestNode
     */
    public void setDistanceToPreviousShortestNode(Integer distanceToPreviousShortestNode) {
        this.distanceToPreviousShortestNode = distanceToPreviousShortestNode;
    }

    @Override
    public String toString() {
        StringBuilder sb = null;
        int cnt = 0;
        try {
            sb = new StringBuilder();
            // adj = this.adjascentNodes.forEach((n) -> System.out.println(n));
            sb.append("\n{\n" +
                    "\t 'nodeName': '" + getNodeName() + "',\n\t 'Adjascent Nodes': [");
                    for (Node n : this.adjascentNodes) 
                    {
                        if (cnt < this.adjascentNodes.size() && this.adjascentNodes.size() > 1)
                            sb.append("'"+n.getNodeName()+"',");
                        else if (this.adjascentNodes.size() == 1)  sb.append("'"+n.getNodeName()+"'");
                        else if (cnt == this.adjascentNodes.size()-1 ) sb.append("'"+n.getNodeName()+"'");
                        cnt++;
                    }
            sb.append("],\n" +
                      "\t 'nextShortestNode': '" + (getNextShortestNode() == null ? "none" : getNextShortestNode().getNodeName()) + "',\n" +
                      "\t 'previousShortestNode': '" + (getPreviousShortestNode() == null ? "none" : getPreviousShortestNode().getNodeName()) + "',\n" +
                      "\t 'distanceToNextShortestNode': '" + (getDistanceToNextShortestNode() == null ? "none" : getDistanceToNextShortestNode()) + "',\n" +
                      "\t 'distanceToPreviousShortestNode': '" + (getDistanceToPreviousShortestNode() == null ? "none" : getDistanceToPreviousShortestNode()) + "'\n" +
                      "}\n");
        } catch (NullPointerException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return sb.toString();
    }
    /**
     * Get a string representation of all Adjascent nodes
     * @param adjascentNodes
     * @return
     */
    public String getAdjascentNodeToString(List<Node> adjascentNodes)
    {
        
        StringBuilder sb = new StringBuilder();
        for (Node node : adjascentNodes) {
            sb.append("\n"+node.toString()+"\n");
        }
        return sb.toString();
    }
    /**
     * Get a string representation of all Adjascent nodes
     * @param adjascentNodes
     * @return
     */
    public String getAdjascentNodeToString()
    {
        StringBuilder sb = new StringBuilder();
        for (Node node : this.adjascentNodes) {
            sb.append(""+node.toString()+",");
        }
        return sb.toString();
    }
    // public static void main(String[] args) throws Exception
    // {

        

    // }

}
