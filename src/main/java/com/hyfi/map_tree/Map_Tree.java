package com.hyfi.map_tree;

import java.util.*;

// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Map Tree is an algorithm that traverses a map.
 */
public class Map_Tree {
    private static final Logger log = LoggerFactory.getLogger(Map_Tree.class);   
    private static List<Node<String,String>> checkedNodes = new ArrayList<Node<String, String>>();
    private static List<Node<String, String>> connectedPath = new ArrayList<Node<String, String>>();
    private static boolean connected = false;
    private static boolean checked = false;
    private static Integer iter = 0;
    private static Integer mileage = 0;
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Default contructor
     */
    public Map_Tree(){}
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Checks if start node is connected to destination node.
     * Identifies if there is a connected path between any given pair of city names.
     * @param start the start node
     * @param dest  the destination node
     * @return  true/false; is connected...?
     */
    public boolean isConnectedPath(Node<String, String> start, Node<String, String> dest)
    {
        iter++;
        // check if start node is null
        if(start == null) return false;
        checked = isCheckedNode(start);

        // check if destination node has been found
        if (start.equals(dest))
        {   log.info("Found a connection..........");
            log.info("Found node: {}",start.toString());
            // checkedNodes.add(start);
            connectedPath.add(start);
            mileage += start.getDistanceToPreviousShortestNode();
            return true;
        }
        // Traverse each path for nodes with more than 2 adjacent paths
        if (start.getAdjacentNodes().size() > 2 )
        { log.info("Start Next not null; adjacent Node > 2; Start Node: {}",start.toString());
        checkedNodes.add(start);
            for (Node<String, String> n : start.getAdjacentNodes()) 
            {
                if (n.getNextShortestNode() != null && !isCheckedNode(n.getNextShortestNode()))
                {   log.info("Checking From adjacent Node: {}", n.toString());

                    connected = isConnectedPath(n,dest);
    
                }
                else if (n.getNextShortestNode() == null && !isCheckedNode(n))
                {   log.info("Checking From adjacent Node: {}", n.toString());
                    connected = isConnectedPath(n,dest);
                }

                if (n.getNextShortestNode() != null && isCheckedNode(n.getNextShortestNode()))
                {   log.info("Checking From adjacent Node: {}", n.toString());

                    connected = goInReverse(n,dest);
    
                }
                else if (n.getNextShortestNode() == null && isCheckedNode(n))
                {   log.info("Checking From adjacent Node: {}", n.toString());
                    connected = goInReverse(n,dest);
                }
                if (connected)
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    mileage += start.getDistanceToNextShortestNode();
                    return true;
                }
            }        
        }
        // only check nodes with two or less connecting edges or adjacent node
        if (start.getAdjacentNodes().size() < 3 && start.getNextShortestNode() != null 
            && !isCheckedNode(checkedNodes, start))
        { log.info("Start Next node not null; Start Node: {}",start.toString());
            checkedNodes.add(start);
            connected = isConnectedPath(start.getNextShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                mileage += start.getDistanceToNextShortestNode();
                return true;
            }
        }
        // check if node path has been checked
        if (start.getAdjacentNodes().size() < 3 && isCheckedNode(checkedNodes, start) && start.getNextShortestNode() == null) 
        {
            // connected = isConnectedPath(start.getNextShortestNode(), dest);
      
            // if we can't find the dest node going forward, we now got in reverse
            if (!connected)
            {   log.info("Reached end of GRAPH....Checking in reverse..........");
                log.info("ENDING NODE: {}",start.toString());

                connected = this.goInReverse(start, dest);
                if (connected)
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    mileage += start.getDistanceToPreviousShortestNode();
                    return true;
                }
                else return false;
            }
        }
        // check if next node is null
        if(start.getAdjacentNodes().size() < 3 && !isCheckedNode(checkedNodes, start) && start.getNextShortestNode() == null)
        {   log.info("Start Previos node null; Start Next node not null; Start Node: {}",start.toString());
            checkedNodes.add(start);
            connected = isConnectedPath(start.getNextShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                mileage += start.getDistanceToNextShortestNode();
                return true;
            }
            else if (start.getNextShortestNode() == null && !connected)
            {
                return goInReverse(start, dest);
            }
        }

        return connected;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Checks starting at previous node
     * @param start
     * @param dest
     * @return
     */
    public boolean goInReverse(Node<String, String> start, Node<String, String> dest)
    {
        iter--;
        // check if start node is null
        if(start == null) return false;
        checked = isCheckedNode(start);
        // check if destination node has been found
        if (start.equals(dest))
        {   log.info("Found a connection..........");
            log.info("Found node: {}",start.toString());
            // checkedNodes.add(start);
            connectedPath.add(start);
            // mileage += start.getDistanceToNextShortestNode();
            return true;
        }
        // Traverse each path for nodes with more than 2 adjacent paths
        if (start.getAdjacentNodes().size() > 2)
        { log.info("Adjacent Node > 2; Start Node: {}",start.toString());
            for (Node<String, String> n : start.getAdjacentNodes()) 
            {
                checked = isCheckedNode(checkedNodes, n);
                log.info("Has Node: {}; Been checked: {}", n.getNodeName(),checked);
                if (!checked)
                {
                    log.info("Checking FORWARD PATH From adjacent Node: {}", n.toString());
                    
                    connected = isConnectedPath(n,dest);
                }
                // connected = isConnectedPath(n, dest);
                if (connected)
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    // mileage += start.getDistanceToNextShortestNode();
                    return true;
                }
            }
        }
        
        // check if previous node not null
        if (start.getAdjacentNodes().size() < 3 && start.getPreviousShortestNode() != null && isCheckedNode(checkedNodes, start))
        { log.info("Adjacent Node < 3; Start Previous node not null; Start Node: {}",start.toString());
            if (start.equals(dest))
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                // mileage += start.getDistanceToNextShortestNode();
                return true;
            }
            connected = this.goInReverse(start.getPreviousShortestNode(), dest);
        } // traverse path if node has not been checked
        else if (start.getAdjacentNodes().size() < 3 && start.getPreviousShortestNode() != null && !isCheckedNode(checkedNodes, start))
        { log.info("Adjacent Node < 3; Start Previous node not null; Start Node: {}",start.toString());
            if (start.equals(dest))
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                // mileage += start.getDistanceToNextShortestNode();
                return true;
            }
            connected = this.isConnectedPath(start, dest);
        }

        // check if next node is null
        if (!connected && start.getAdjacentNodes().size() < 3 && start.getPreviousShortestNode() == null && isCheckedNode(checkedNodes, start))
        { log.info("Adjacent Node < 3; Start previous node is null; Start node: {}", start.toString());
            if (start.equals(dest))
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                // mileage += start.getDistanceToNextShortestNode();
                return true;
            }

            connected = this.goInReverse(start.getPreviousShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                // mileage += start.getDistanceToNextShortestNode();
                return true;
            }
            else return false;
        }

        return connected;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Compares two nodes and sorts
     * @param node1
     * @param node2
     * @return true is equal/false if not equal
     */
    public boolean compareTo(Node<String,String> node1, Node<String,String> node2) {
        return node1.equals(node2);
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * checks to see if node has been checked already against adjacent nodes.
     * @param node node to be checked
     * @return true if checked/false if not
     */
    public boolean isCheckedNode(Node<String,String> node)
    {

        Integer f = 0;
        if (node == null) {return false;} // no node has been checked
        for (Node<String,String> n : checkedNodes)
            if(n.equals(node)) {f++;}
        if (f == 0){return false;}
        return true;
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * checks to see if node has been checked already against adjacent nodes.
     * @param checked_Nodes list of checked nodes
     * @param node  node to be checked
     * @return true if node path has been checked/false otherwise
     */
    public boolean isCheckedNode(List<Node<String,String>> checked_Nodes, Node<String,String> node)
    {
        Integer f = 0;
        if (node == null) {return false;} // no node has been checked 
        if (checked_Nodes.isEmpty()) {return false;} // no node has been checked
        for (Node<String,String> n : checked_Nodes) // check each node
            if(n.equals(node)) {f++;} // found already checked node
        if (f == 0){return false;}
        return true; // nodes has not been checked
    }
    ///////////////////////////////////////////////////////////////////////////
    /**
     * Checks to see if any adjacent node has not been traversed or checked,
     * against the list of already checked nodes. If atleast one node returns 
     * unchecked, the method returns true.
     * @param adjacentNodes
     * @return true, if one or more unchecked nodes have been found/false, 
     * if all nodes have been checked
     */
    public boolean foundUncheckedNode(List<Node<String,String>> adjacentNodes)
    {
        if (adjacentNodes.isEmpty()) {return false;} // no node has been checked
        Integer f = 0;
        for (Node<String,String> node : adjacentNodes) 
            if(isCheckedNode(checkedNodes, node)){f++;}
        if (f < adjacentNodes.size()-1){return true;}
        return false;
    }
    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////// [ MAIN ] //////////////////////////////////
    public static void main(String[] args) {
        log.info("Start of main...");
        ////////////// Graph /////////////////
        Node<String,String> neamt = new Node<String,String>("neamt","Some Object with secret data...");
        Node<String,String> iasi = new Node<String,String>("Iasi","Amazing Grace");
        Node<String,String> vaslui = new Node<String,String>("vaslui","Wonderful...");
        Node<String,String> urziceni = new Node<String,String>("urziceni","Hello Kitty");
        Node<String,String> bucharest = new Node<String,String>("Bucharest","Go harder...");
        Node<String,String> giurgiu = new Node<String,String>("Giurgiu","Just Do IT...");
        Node<String,String> hirsova = new Node<String,String>("Hirsova","Howdy Partner...");
        Node<String,String> eforie = new Node<String,String>("Eforic","Whew...");

        // System.out.println(iasi.toString());
        // add node neamt
        neamt.setDistanceToPreviousShortestNode(null);
        neamt.setPreviousShortestNode(null);
        neamt.setDistanceToNextShortestNode(87);
        neamt.setNextShortestNode(iasi);
        neamt.addAdjacentNode(iasi);

        // add node Iasi
        iasi.setDistanceToPreviousShortestNode(87);
        iasi.setPreviousShortestNode(neamt);
        iasi.setDistanceToNextShortestNode(92);
        iasi.setNextShortestNode(vaslui);
        iasi.addAdjacentNode(neamt);
        iasi.addAdjacentNode(vaslui);

        // add node vaslui
        vaslui.setDistanceToPreviousShortestNode(92);
        vaslui.setPreviousShortestNode(iasi);
        vaslui.setDistanceToNextShortestNode(142);
        vaslui.setNextShortestNode(urziceni);
        vaslui.addAdjacentNode(iasi);
        vaslui.addAdjacentNode(urziceni);

        // add node urziceni
        urziceni.setDistanceToPreviousShortestNode(142);
        urziceni.setPreviousShortestNode(vaslui);
        urziceni.setDistanceToNextShortestNode(85);
        urziceni.setNextShortestNode(bucharest);
        urziceni.addAdjacentNode(bucharest);
        urziceni.addAdjacentNode(hirsova);
        urziceni.addAdjacentNode(vaslui);

        // add node hirsova
        hirsova.setDistanceToPreviousShortestNode(98);
        hirsova.setPreviousShortestNode(urziceni);
        hirsova.setDistanceToNextShortestNode(86);
        hirsova.setNextShortestNode(eforie);
        hirsova.addAdjacentNode(urziceni);
        hirsova.addAdjacentNode(eforie);

        // add node bucharest
        bucharest.setDistanceToPreviousShortestNode(85);
        bucharest.setPreviousShortestNode(urziceni);
        bucharest.setDistanceToNextShortestNode(90);
        bucharest.setNextShortestNode(giurgiu);
        bucharest.addAdjacentNode(urziceni);
        bucharest.addAdjacentNode(giurgiu);

        // add node giurgiu
        giurgiu.setDistanceToPreviousShortestNode(90);
        giurgiu.setPreviousShortestNode(bucharest);
        giurgiu.setDistanceToNextShortestNode(null);
        giurgiu.setNextShortestNode(null);
        giurgiu.addAdjacentNode(bucharest);

        // add node eforie
        eforie.setDistanceToPreviousShortestNode(86);
        eforie.setPreviousShortestNode(hirsova);
        eforie.setDistanceToNextShortestNode(null);
        eforie.setNextShortestNode(null);
        eforie.addAdjacentNode(hirsova);
        
        // log.info("\n\nNeamt: {} \n\n Iasi: {} \n\n Vaslui: {} \n\n Urziceni: {} \n\n Bucharest: {} \n\n Hirsova: {} \n\n eforie: {} \n\n Giurgiu: {}",neamt.toString(),iasi.toString(), vaslui.toString(),urziceni.toString(),bucharest.toString(),hirsova.toString(),eforie.toString(),giurgiu.toString());

        // System.out.println("\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getNodeName()+":\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getadjacentNodeToString());

        Map_Tree m = new Map_Tree();
        
        // check if their is a connected path between neamt and eforie
        // log.info("Node neamt: {}",neamt.toString());
        log.info("Is connected: {}",m.isConnectedPath(neamt, giurgiu));
        log.info("Trip Mileage: {}\n\n", mileage);
        log.info("Connected Path: {}", connectedPath.toString());
        // connectedPath.forEach((n) -> log.info("Is {} connected to {}: {}",n.getNodeName(), urziceni.getNodeName(), m.isConnectedPath(n, urziceni)));

    }

}