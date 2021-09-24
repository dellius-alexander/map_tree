package com.hyfi.map_tree;

import java.util.*;

// Imports for logging
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class Map_Tree {
    private static final Logger log = LoggerFactory.getLogger(Map_Tree.class);   
    /**
     * Default contructor
     */
    public Map_Tree(){}
    /**
     * Checks if start node is connected to destination node.
     * Identifies if there is a connected path between any given pair of city names.
     * @param start the start node
     * @param dest  the destination node
     * @return  true/false; is connected...?
     */
    public boolean isConnectedPath(Node<String, String> start, Node<String, String> dest)
    {
        List<Node<String, String>> connectedPath = new ArrayList<Node<String, String>>();
        boolean connected = false;
        if(start == null) return false;
        // Traverse each path for nodes with more than 2 adjascent path
        if (start.getAdjascentNodes().size() > 2)
        { log.info("Start Next not null; Adjascent Node > 2; Start Node: {}",start.toString());
            if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
            for (Node<String, String> n : start.getAdjascentNodes()) 
            {
                log.info("Checking From Adjascent Node: {}", n.toString());
                if (n.getNodeName() == dest.getNodeName())
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    return true;
                }
                connected = isConnectedPath(n, dest);
                if (connected)
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    return true;
                }
            }            
            if (start.getNextShortestNode() == null && connected == false)
            {
                connected = goInReverse(start,dest);
                if (connected)
                {   log.info("Found a connection..........");
                    log.info("Found node: {}",start.toString());
                    connectedPath.add(start);
                    return true;
                }
            }
            
        }
        // only check nodes with two or less connecting edges or adjascent node
        if (start.getAdjascentNodes().size() < 3 && start.getNextShortestNode() != null)
        { log.info("Start Previos node null; Start Next node not null; Start Node: {}",start.toString());
            if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
            connected = isConnectedPath(start.getNextShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
        }
        // only check if 
        else if (start.getNextShortestNode() != null && start.getAdjascentNodes().size() < 3)
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());
            connected = false;    
        if (start.getNodeName() == dest.getNodeName())
            { log.info("Found a connection..........");
                return true;
            }
            connected = isConnectedPath(start.getNextShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
        }
        else if (start.getNextShortestNode() == null )
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());
            if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }

        }
        // if we can't find the dest node going forward, we now got in reverse
        if (!connected)
        {   log.info("Found a connection..........");
            log.info("Found node: {}",start.toString());
            connectedPath.add(start);
            connected = goInReverse(start, dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
        }
        return connected;
    }
    /**
     * Checks starting at previous node
     * @param start
     * @param dest
     * @return
     */
    private boolean goInReverse(Node<String, String> start, Node<String, String> dest)
    {
        boolean connected = false;
        List<Node<String, String>> connectedPath = new ArrayList<Node<String, String>>();
        if(start == null) return false;
        if (start.getAdjascentNodes().size() < 3 && start.getPreviousShortestNode() != null)
        { log.info("Start Previos node null; Start Next node not null; Start Node: {}",start.toString());
            if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
            return isConnectedPath(start.getPreviousShortestNode(), dest);
        }
        else if (start.getPreviousShortestNode() != null && start.getAdjascentNodes().size() < 3)
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());  
        if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
            log.info("Next node: {}",start.getNextShortestNode().toString());
            connected = isConnectedPath(start.getPreviousShortestNode(), dest);
            if (connected)
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);            
                return true; 
            }

        }
        else if (start.getPreviousShortestNode() == null )
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());
            if (start.getNodeName() == dest.getNodeName())
            {   log.info("Found a connection..........");
                log.info("Found node: {}",start.toString());
                connectedPath.add(start);
                return true;
            }
            else return false;
        }
        
        // // Traverse each path for nodes with more than 2 adjascent path
        // if (start.getAdjascentNodes().size() > 2)
        // { log.info("Start Next not null; Adjascent Node > 2; Start Node: {}",start.toString());
        //         connected = false;
        //     if (start.getNodeName() == dest.getNodeName())
        //     { log.info("Found a connection..........");
        //         return true;
        //     }
        //     for (Node n : start.getAdjascentNodes()) 
        //     {
        //         log.info("Checking From Adjascent Node: {}", n.toString());
        //         if (n.getNodeName() == dest.getNodeName())
        //         { log.info("Found a connection..........");
        //             return true;
        //         }
        //         System.exit(0);
        //         connected = goInReverse(n, dest);
        //         if (connected) return true;
        //         connected = isConnectedPath(n, dest);
        //         if (connected) return true;
        //     }            
        //     if (start.getNextShortestNode() == null && start.getPreviousShortestNode() != null && connected == false)
        //     {
        //         connected = isConnectedPath(start.getPreviousShortestNode(),dest);
        //         if (connected) return true;
        //     }     
        //     if (connected) return true;   
        // }
        return connected;
    }
    public static void main(String[] args) {
        log.info("Start of main...");

        ////////////// Graph /////////////////
        Node neamt = new Node<String,String>("neamt","Hello world");
        Node iasi = new Node<String,String>("Iasi","Amazing Grace");
        Node vaslui = new Node<String,String>("vaslui","Wonderful...");
        Node urziceni = new Node<String,String>("urziceni","Hello Kitty");
        Node bucharest = new Node<String,String>("Bucharest","Go harder...");
        Node giurgiu = new Node<String,String>("Giurgiu","Just Do IT...");
        Node hirsova = new Node<String,String>("Hirsova","Howdy Partner...");
        Node eforie = new Node<String,String>("Eforic","Whew...");

        // add node neamt
        neamt.setDistanceToPreviousShortestNode(null);
        neamt.setPreviousShortestNode(null);
        neamt.setDistanceToNextShortestNode(87);
        neamt.setNextShortestNode(iasi);
        neamt.setAdjascentNode(iasi);


        // add node Iasi
        iasi.setDistanceToPreviousShortestNode(87);
        iasi.setPreviousShortestNode(neamt);
        iasi.setDistanceToNextShortestNode(92);
        iasi.setNextShortestNode(vaslui);
        iasi.setAdjascentNode(neamt);
        iasi.setAdjascentNode(vaslui);

        // add node vaslui
        vaslui.setDistanceToPreviousShortestNode(92);
        vaslui.setPreviousShortestNode(iasi);
        vaslui.setDistanceToNextShortestNode(142);
        vaslui.setNextShortestNode(urziceni);
        vaslui.setAdjascentNode(iasi);
        vaslui.setAdjascentNode(urziceni);

        // add node urziceni
        urziceni.setDistanceToPreviousShortestNode(142);
        urziceni.setPreviousShortestNode(vaslui);
        urziceni.setDistanceToNextShortestNode(85);
        urziceni.setNextShortestNode(bucharest);
        urziceni.setAdjascentNode(bucharest);
        urziceni.setAdjascentNode(hirsova);
        urziceni.setAdjascentNode(vaslui);

        // add node hirsova
        hirsova.setDistanceToPreviousShortestNode(98);
        hirsova.setPreviousShortestNode(urziceni);
        hirsova.setDistanceToNextShortestNode(86);
        hirsova.setNextShortestNode(eforie);
        hirsova.setAdjascentNode(urziceni);
        hirsova.setAdjascentNode(eforie);

        // add node bucharest
        bucharest.setDistanceToPreviousShortestNode(85);
        bucharest.setPreviousShortestNode(urziceni);
        bucharest.setDistanceToNextShortestNode(90);
        bucharest.setNextShortestNode(giurgiu);
        bucharest.setAdjascentNode(urziceni);
        bucharest.setAdjascentNode(giurgiu);

        // add node giurgiu
        giurgiu.setDistanceToPreviousShortestNode(90);
        giurgiu.setPreviousShortestNode(bucharest);
        giurgiu.setDistanceToNextShortestNode(null);
        giurgiu.setNextShortestNode(null);
        giurgiu.setAdjascentNode(bucharest);

        // add node eforie
        eforie.setDistanceToPreviousShortestNode(86);
        eforie.setPreviousShortestNode(hirsova);
        eforie.setDistanceToNextShortestNode(null);
        eforie.setNextShortestNode(null);
        eforie.setAdjascentNode(hirsova);
        // log.info("\n\nNeamt: {} \n\n Iasi: {} \n\n Vaslui: {} \n\n Urziceni: {} \n\n Bucharest: {} \n\n Hirsova: {} \n\n eforie: {} \n\n Giurgiu: {}",neamt.toString(),iasi.toString(), vaslui.toString(),urziceni.toString(),bucharest.toString(),hirsova.toString(),eforie.toString(),giurgiu.toString());

        // System.out.println("\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getNodeName()+":\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getAdjascentNodeToString());

        Map_Tree m = new Map_Tree();
        // check if their is a connected path between neamt and eforie
        log.info("Is connected: {}",m.isConnectedPath(neamt, vaslui));
    }
}
