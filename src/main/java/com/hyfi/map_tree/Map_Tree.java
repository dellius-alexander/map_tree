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

    public boolean isConnectedPath(Node start, Node dest)
    {
        List<Node> connectedPath = new ArrayList<Node>();
        if(start == null) return false;
        if (start.getAdjascentNodes().size() < 3)
        { log.info("Start Previos node null; Start Next node not null; Start Node: {}",start.toString());
            if (start.getNodeName() == dest.getNodeName())
            { log.info("Found a connection..........");
                return true;
            }
            return isConnectedPath(start.getNextShortestNode(), dest);
        }
        else if (start.getNextShortestNode() != null && start.getAdjascentNodes().size() < 3)
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());
            if (start.getNodeName() == dest.getNodeName())
            { log.info("Found a connection..........");
            return true;
            }
            return isConnectedPath(start.getNextShortestNode(), dest);
        }
        else if (start.getNextShortestNode() == null )
        { log.info("Start Next node not null; Dest Previous node not null; Start node: {}", start.toString());
            if (start.getNodeName() == dest.getNodeName())
            { log.info("Found a connection..........");
                return true;
            }

        }
        
        if (start.getAdjascentNodes().size() > 2)
        { log.info("Start Next not null; Adjascent Node > 2; Start Node: {}",start.toString());
            boolean isCon = false;
            if (start.getNodeName() == dest.getNodeName())
            { log.info("Found a connection..........");
                return true;
            }
            for (Node n : start.getAdjascentNodes()) 
            {
                log.info("Checking From Adjascent Node: {}", n.toString());
                if (n.getNodeName() == dest.getNodeName())
                { log.info("Found a connection..........");
                    return true;
                }
                isCon = isConnectedPath(n, dest);
                if (isCon == true) return true;
            }
            // for (int i = 0; i < start.getAdjascentNodes().size(); i++) 
            // {
            //     Node n = start.getAdjascentNodes().get(i);
            //     log.info("Checking From Node: {}", n.toString());
            //     isCon = isConnectedPath(n, dest);
            //     if (isCon == true) return true;
            //     else if (i != start.getAdjascentNodes().size()-1)
            //         return isConnectedPath(n, dest);
            // }            
            
        }

        return false;
    }

    public static void main(String[] args) {
        log.info("Start of main...");

        ////////////// Graph /////////////////
        Node neamt = new Node("neamt");
        Node iasi = new Node("Iasi");
        Node vaslui = new Node("vaslui");
        Node urziceni = new Node("urziceni");
        Node bucharest = new Node("Bucharest");
        Node giurgiu = new Node("Giurgiu");
        Node hirsova = new Node("Hirsova");
        Node eforie = new Node("Eforic");

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
        // System.out.println(iasi.toString());
        // log.info("\n\nNeamt: {} \n\n Iasi: {} \n\n Vaslui: {} \n\n Urziceni: {} \n\n Bucharest: {} \n\n Hirsova: {} \n\n eforie: {} \n\n Giurgiu: {}",neamt.toString(),iasi.toString(), vaslui.toString(),urziceni.toString(),bucharest.toString(),hirsova.toString(),eforie.toString(),giurgiu.toString());

        // System.out.println("\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getNodeName()+":\n"+eforie.getPreviousShortestNode().getPreviousShortestNode().getAdjascentNodeToString());

        Map_Tree m = new Map_Tree();
        // check if their is a connected path between neamt and eforie
        System.out.println(m.isConnectedPath(neamt, eforie));
    }
}
