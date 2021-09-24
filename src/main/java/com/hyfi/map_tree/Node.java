package com.hyfi.map_tree;
// // Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class Node<Name, Data> {
    private static final Logger log = LoggerFactory.getLogger(Node.class);
    private Name name;
    private Data data;
    private List<Data> records = new ArrayList<Data>();
    // Node node = null;
    private List<Node<Name, Data>> adjascentNodes = new ArrayList<Node<Name,Data>>();
    private Node<Name, Data> nextShortestNode = null;
    private Node<Name, Data> previousShortestNode = null;
    private Integer distanceToNextShortestNode = null;
    private Integer distanceToPreviousShortestNode = null;

    /**
     * Default constructor; creates a null node
     */
    public Node(){log.info("init......");}
    /**
     * Create new node name and create a new node
     * @param name
     */
    public Node(Name name)
    {
        this.name = name;
        this.adjascentNodes = new ArrayList<Node<Name, Data>>();        
    }
    public Node(Name name, Data data)
    {
        this.data = data;
        this.name = name;
        this.records.add(data);   
    }
    /**
     * Create a new node, define the next and previous connected nodes
     * @param name
     * @param nextNode
     * @param previousNode
     */
    public Node(Name name, Node<Name, Data> nextNode, Node<Name, Data> previousNode)
    {
        this.name = name;
        this.records.add(data);
        this.nextShortestNode = nextNode;
        this.previousShortestNode = previousNode;
        this.adjascentNodes.add(previousNode);
        this.adjascentNodes.add(nextNode);
    }
    /**
     * Create new node
     * @param name
     * @param nextNode
     * @param previousNode
     * @param data
     */
    public Node(Name name, Node<Name, Data> nextNode, Node<Name, Data> previousNode, Data data)
    {
        this.data = data;
        this.name = name;
        this.nextShortestNode = nextNode;
        this.previousShortestNode = previousNode;
        this.adjascentNodes.add(previousNode);
        this.adjascentNodes.add(nextNode);
    }
    /**
     * Create new node
     * @param name
     * @param adjNodes
     */
    public Node(Name name, List<Node<Name, Data>> adjNodes)
    {
        this.name = name;
        this.adjascentNodes = adjNodes;
    }
    /**
     * Create new node
     * @param name
     * @param adjNodes
     * @param data
     */
    public Node(Name name, List<Node<Name, Data>> adjNodes, Data data)
    {
        this.data = data;
        this.adjascentNodes = new ArrayList<Node<Name, Data>>();
        this.name = name;
        this.adjascentNodes = adjNodes;
    }
    /**
     * Sets the data object
     * @param data the data object
     */
    public void setData(Data data){this.data = data;}
    /**
     * Gets the data object
     * @return
     */
    public Data getData(){return this.data;}
    /**
     * Add data to records
     * @param data
     */
    public void addRecord(Data data){this.records.add(data);}
    public List<Data> getRecords()
    {
        return this.records;
    }
    /**
     * Gets the node thats the shortest in the list of
     * adjascent nodes
     * @param adjNodes
     * @return the next shortest node
     */
    private Node<Name, Data> getNextShortest(List<Node<Name, Data>> adjNodes)
    {   // create temp node
        Node<Name, Data> n = null;
        // Collection<String,Node> adjascentNode_ = new Collection<E>(){
            
        // };
        Integer distance = Integer.MAX_VALUE;
        for (int i=0; i < adjNodes.size(); i++) 
        {   // find the adjescent node with the shortest
            // compare the distance between each node and source node
            // and keep the shortest distance, now compare it to the 
            // adjascent node and assign the node with the shortest path
            n = adjNodes.get(i);

            for(int j=0; j < adjNodes.size(); j++)
            {

            }
            
        }

        return n;
    }
    /**
     * Get node name
     * @return
     */
    public Name getNodeName() {
        return this.name;
    }
    /**
     * Set node name
     * @param name
     */
    public void setNodeName(Name name) {
        this.name = name;
    }
    /**
     * Get adjascent nodes
     * @return
     */
    public void addAdjascentNode(Node<Name, Data> node)
    {
        this.adjascentNodes.add(node);
    }
    /**
     * Get list of adjascent nodes/edges by name
     * @return
     */
    public List<Node<Name, Data>> getAdjascentNodes() {
        return this.adjascentNodes;
    }
    /**
     * Set adjascent nodes
     * @param adjascentNodes
     */
    public void setAdjascentNodes(List<Node<Name, Data>> adjascentNodes) {
        this.adjascentNodes = adjascentNodes;
    }
    /**
     * Get next shortest node
     * @return
     */
    public Node<Name, Data> getNextShortestNode() {
        return this.nextShortestNode;
    }
    /**
     * Set next shortest node
     * @param nextShortestNode
     */
    public void setNextShortestNode(Node<Name, Data> nextShortestNode) {
        this.nextShortestNode = nextShortestNode;
    }
    /**
     * Get previous shortest node
     * @return
     */
    public Node<Name, Data> getPreviousShortestNode() {
        return this.previousShortestNode;
    }
    /**
     * Set previous shortest node
     * @param previousShortestNode
     */
    public void setPreviousShortestNode(Node<Name, Data> previousShortestNode) {
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
    public void setDistanceToNextShortestNode(Integer distanceToNextShortestNode, Node<Name, Data> nextShortestNode){
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
    /**
     * Contents of this node
     */
    public void contents()
    {
        System.out.println("Name: "+this.name+" \nData: "+this.data);

    }
    /**
     * To String JSON
     */
    @Override
    public String toString() {
        StringBuilder sb = null;
        int cnt = 0;
        try {
            sb = new StringBuilder();
            // adj = this.adjascentNodes.forEach((n) -> System.out.println(n));
            sb.append("\n{\n" +
                    "\t 'Name': '" + getNodeName() + 
                    "',\n\t 'Data: '"+getData().toString()+
                    "',\n\t 'Adjascent Nodes': [");
                    for (Node<Name, Data> n : this.adjascentNodes) 
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
            
            e.printStackTrace();
        }
        return sb.toString();
    }
    /**
     * Get a string representation of all Adjascent nodes
     * @param adjascentNodes
     * @return
     */
    public String getAdjascentNodeToString(List<Node<Name, Data>> adjascentNodes)
    {
        
        StringBuilder sb = new StringBuilder();
        for (Node<Name, Data> node : adjascentNodes) {
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
        for (Node<Name, Data> node : this.adjascentNodes) {
            sb.append(""+node.toString()+",");
        }
        return sb.toString();
    }
    
    // public static void main(String[] args) throws Exception
    // {
    //     log.info("Start of Main......");
    //     Node<String,String> neamt = new Node<String,String>("neamt","Hello world");
    //     Node<String,String> iasi = new Node<String,String>("Iasi","Amazing Grace");
    //     Node<String,String> vaslui = new Node<String,String>("vaslui","Wonderful...");
    //     // add node neamt
    //     neamt.setDistanceToPreviousShortestNode(null);
    //     neamt.setPreviousShortestNode(null);
    //     neamt.setDistanceToNextShortestNode(87);
    //     neamt.setNextShortestNode(iasi);
    //     neamt.addAdjascentNode(iasi);

    //     // add node Iasi
    //     iasi.setDistanceToPreviousShortestNode(87);
    //     iasi.setPreviousShortestNode(neamt);
    //     iasi.setDistanceToNextShortestNode(92);
    //     iasi.setNextShortestNode(vaslui);
    //     iasi.addAdjascentNode(neamt);
    //     iasi.addAdjascentNode(vaslui);
        
    //     iasi.contents();
    //     System.out.println(iasi.toString());
    //     System.exit(0);

    //     /////////////////////////////////////////
    //     log.info("End of Main......");
    // }

}
