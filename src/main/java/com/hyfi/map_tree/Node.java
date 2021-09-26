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
    private List<Node<Name, Data>> adjacentNodes = new ArrayList<Node<Name,Data>>();
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
        this.adjacentNodes = new ArrayList<Node<Name, Data>>();        
    }
    public Node(Name name, Data data)
    {
        this.data = data;
        this.name = name;
        this.addRecord(data);   
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
        this.addRecord(nextNode.data);
        this.addRecord(previousNode.data);
        this.nextShortestNode = nextNode;
        this.previousShortestNode = previousNode;
        this.adjacentNodes.add(previousNode);
        this.adjacentNodes.add(nextNode);
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
        this.addRecord(data);   
        this.addRecord(nextNode.data);
        this.addRecord(previousNode.data);
        this.nextShortestNode = nextNode;
        this.previousShortestNode = previousNode;
        this.adjacentNodes.add(previousNode);
        this.adjacentNodes.add(nextNode);
    }
    /**
     * Create new node
     * @param name
     * @param adjNodes
     */
    public Node(Name name, List<Node<Name, Data>> adjNodes)
    {
        this.name = name;
        this.adjacentNodes = adjNodes;
        for (Node<Name,Data> node : adjNodes) 
        {
            this.addRecord(node.data);   
        }
    }
    /**
     * Create new node
     * @param name
     * @param adjNodes
     * @param data
     */
    public Node(Name name, List<Node<Name, Data>> adjNodes, Data data)
    {
        this.name = name;
        this.data = data;
        this.addRecord(data);
        this.adjacentNodes = new ArrayList<Node<Name, Data>>();
        this.adjacentNodes = adjNodes;
        for (Node<Name,Data> node : adjNodes) 
        {
            this.addRecord(node.data);   
        }
    }
    /**
     * Sets the data object
     * @param data the data object
     */
    public void setData(Data data){this.data = data; this.addRecord(data);}
    /**
     * Gets the data object
     * @return
     */
    public Data getData(){return this.data;}
    /**
     * Add data to records
     * @param data
     */
    public void addRecord(Data data)
    {
        int found = 0;
        for (Data d : this.records) 
        {
            if (d.equals(data)){found++;}
        }
        if (found == 0) 
        {
            this.records.add(data);
        }       
    }
    public List<Data> getRecords()
    {
        return this.records;
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
     * Get adjacent nodes
     * @return
     */
    public void addAdjacentNode(Node<Name, Data> node)
    {
        this.adjacentNodes.add(node);
    }
    /**
     * Get list of adjacent nodes/edges by name
     * @return
     */
    public List<Node<Name, Data>> getAdjacentNodes() {
        return this.adjacentNodes;
    }
    /**
     * Set adjacent nodes
     * @param adjacentNodes
     */
    public void setAdjacentNodes(List<Node<Name, Data>> adjacentNodes) {
        this.adjacentNodes = adjacentNodes;
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
            // adj = this.adjacentNodes.forEach((n) -> System.out.println(n));
            sb.append("\n{\n" +
                    "\t 'Name': '" + getNodeName() + 
                    "',\n\t 'Data: '"+getData().toString()+
                    "',\n\t 'Adjacent Nodes': [");
                    for (Node<Name, Data> n : this.adjacentNodes) 
                    {
                        if (cnt < this.adjacentNodes.size() && this.adjacentNodes.size() > 1)
                            sb.append("'"+n.getNodeName()+"',");
                        else if (this.adjacentNodes.size() == 1)  sb.append("'"+n.getNodeName()+"'");
                        else if (cnt == this.adjacentNodes.size()-1 ) sb.append("'"+n.getNodeName()+"'");
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
     * Get a string representation of all adjacent nodes
     * @param adjacentNodes
     * @return
     */
    public String getAdjacentNodeToString(List<Node<Name, Data>> adjacentNodes)
    {
        
        StringBuilder sb = new StringBuilder();
        for (Node<Name, Data> node : adjacentNodes) {
            sb.append("\n"+node.toString()+"\n");
        }
        return sb.toString();
    }
    /**
     * Get a string representation of all adjacent nodes
     * @param adjacentNodes
     * @return
     */
    public String getAdjacentNodeToString()
    {
        StringBuilder sb = new StringBuilder();
        for (Node<Name, Data> node : this.adjacentNodes) {
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
    //     neamt.addAdjacentNode(iasi);

    //     // add node Iasi
    //     iasi.setDistanceToPreviousShortestNode(87);
    //     iasi.setPreviousShortestNode(neamt);
    //     iasi.setDistanceToNextShortestNode(92);
    //     iasi.setNextShortestNode(vaslui);
    //     iasi.addAdjacentNode(neamt);
    //     iasi.addAdjacentNode(vaslui);
        
    //     iasi.contents();
    //     System.out.println(iasi.toString());
    //     System.exit(0);

    //     /////////////////////////////////////////
    //     log.info("End of Main......");
    // }

}
