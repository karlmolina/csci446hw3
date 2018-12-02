/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class Node {
    private Node parent;
    private ArrayList<Node> children;
    Room room;
    
    public Node(int x, int y) {
        this.parent = null;
        this.children = new ArrayList<>();
    }

    public Node parent() {
        return parent;
    }
    
    public void setParent(Node node) {
        this.parent = node;
    }
    
    public void addChild(Node child) {
        children.add(child);
    }
    
    public ArrayList<Node> children() {
        return children;
    }
}