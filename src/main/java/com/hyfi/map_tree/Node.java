package com.hyfi.map_tree;
// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.google.gson.*;
import ch.qos.logback.classic.pattern.SyslogStartConverter;

import java.util.*;
import  org.json.simple.*;

public class Node {
    private static final Logger log = LoggerFactory.getLogger(Node.class);   
    String nodeName = null;
    // Node node = null;
    List<Node> adjascentNodes = null;
    Node nextShortestNode = null;
    Node previousShortestNode = null;
    Integer distanceToNextShortestNode = null;
    Integer distanceToPreviousShortestNode = null;

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
    // /**
    //  * Get this Node
    //  * @return
    //  */
    // public Node getNode() {
    //     return this.node;
    // }
    // /**
    //  * Set this Node
    //  * @param node
    //  */
    // public void setNode(Node node) {
    //     this.node = node;
    // }
    /**
     * Get adjascent nodes
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
        String json = "";
        String adj = "";
        
        try {
            String nextNodeName = (getNextShortestNode() == null ? "none" : getNextShortestNode().getNodeName());
            String previousNodeName = (getPreviousShortestNode() == null ? "none" : getPreviousShortestNode().getNodeName());
            StringBuilder sb = new StringBuilder();
            for (Node n : this.adjascentNodes) {
                sb.append("\n"+n.toString()+"\n");
            }
            // adj = this.adjascentNodes.forEach((n) -> System.out.println(n));
            sb.append("{\n" +
                    "\t 'nodeName': '" + getNodeName() + "',\n"+
                    "\t 'adjascentNodes': '[\n");
            for (Node n : this.adjascentNodes) {
                sb.append("\n"+n+"\n");
            }
            
            sb.append("]',\n" +
                      "\t 'nextShortestNode': '" + (nextNodeName) + "',\n" +
                      "\t 'previousShortestNode': '" + (previousNodeName) + "',\n" +
                      "\t 'distanceToNextShortestNode': '" + getDistanceToNextShortestNode() + "',\n" +
                      "\t 'distanceToPreviousShortestNode': '" + getDistanceToPreviousShortestNode() + "'\n" +
                      "}");
        } catch (NullPointerException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        return json;
    }
    
    private String toString(Node node)
    {

        try {
            String nextNodeName = (getNextShortestNode() == null ? "none" : getNextShortestNode().getNodeName());
            String previousNodeName = (getPreviousShortestNode() == null ? "none" : getPreviousShortestNode().getNodeName());

            StringBuilder sb = new StringBuilder();
            for (Node n : this.adjascentNodes) {
                sb.append("\n"+n.toString()+"\n");
            }
            // adj = this.adjascentNodes.forEach((n) -> System.out.println(n));
            sb.append("{\n" +
                    "\t 'nodeName': '" + getNodeName() + "',\n"+
                    "\t 'adjascentNodes': '[\n");
            for (Node n : this.adjascentNodes) 
            {
                sb.append("\n"+n+"\n");            
            }
            
            sb.append("]',\n" +
                      "\t 'nextShortestNode': '" + (nextNodeName) + "',\n" +
                      "\t 'previousShortestNode': '" + (previousNodeName) + "',\n" +
                      "\t 'distanceToNextShortestNode': '" + getDistanceToNextShortestNode() + "',\n" +
                      "\t 'distanceToPreviousShortestNode': '" + getDistanceToPreviousShortestNode() + "'\n" +
                      "}");


        } catch (NullPointerException e) {
            //TODO: handle exception
            e.printStackTrace();
        }
        

        return "";
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
            sb.append("\n"+node.toString()+"\n");
        }
        return sb.toString();
    }
    public static void main(String[] args) throws Exception
    {
        log.info("Start of main...");

        ////////////// Graph /////////////////
        Node a = new Node("neamt");
        Node b = new Node("Iasi");
        Node c = new Node("vaslui");
        Node d = new Node("urziceni");
        Node e = new Node("Bucharest");
        Node f = new Node("Giurgiu");
        Node g = new Node("Hirsova");
        Node h = new Node("Eforic");

        // add node a
        a.setDistanceToPreviousShortestNode(null);
        a.setPreviousShortestNode(null);
        a.setDistanceToNextShortestNode(87);
        a.setNextShortestNode(b);
        a.adjascentNodes.add(b);
        // // add node b
        b.setDistanceToPreviousShortestNode(87);
        b.setPreviousShortestNode(a);
        b.setDistanceToNextShortestNode(92);
        b.setNextShortestNode(c);
        b.adjascentNodes.add(a);
        b.adjascentNodes.add(c);
        // log.info("\n\nB: {} \n\n A: {}", b.toString(), a.toString());
        // log.info("\n\nAdjascent nodes of A: {} \n\nAdjascent nodes of B: {}", b.getAdjascentNodeToString(), a.getAdjascentNodeToString());
        // System.out.println(b.getAdjascentNodeToString(b.getAdjascentNodes()));
        // // add node c
        // c.setDistanceToPreviousShortestNode(92);
        // c.setPreviousShortestNode(b);
        // c.setDistanceToNextShortestNode(142);
        // c.setNextShortestNode(d);
        // c.adjascentNodes.add(b);
        // c.adjascentNodes.add(d);
        // // add node d
        // d.setDistanceToPreviousShortestNode(85);
        // d.setPreviousShortestNode(e);
        // d.setDistanceToNextShortestNode(98);
        // d.setNextShortestNode(g);
        // d.adjascentNodes.add(e);
        // d.adjascentNodes.add(g);

        // log.info("Neamt: {}", b.getAdjascentNodeToString(b.getAdjascentNodes()));
        // log.info("Neamt: {}",b.toString());
     //Creating the ObjectMapper object
     ObjectMapper mapper = new ObjectMapper();
     //Converting the Object to JSONString
     String jsonString = mapper.writeValueAsString(a);
     System.out.println(jsonString);
    }

}
