/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.util.ArrayList;

/**
 * The wumpus world room class.
 * @author Karl Molina, Jordan Palmer
 */
class Room {
    // The column and row position of the Room
    int col, row;
    // If the room has been visited
    boolean visited, 
            // If the room has the wumpus in it
            wumpus, 
            // If the room has a pit in it
            pit, 
            // If the room has the gold in it
            gold, 
            // If the room has stench in it
            stench, 
            // If the room is breezy
            breeze, 
            // If the room has gold
            glitter, 
            // If the player bumped into a wall in this room
            bump;
    
    // don't know = 0, know safe = 1, know not safe = 2
    enum Status {
        Unknown,
        Safe,
        Dangerous
    }
    
    Status wumpusStatus = Status.Unknown, pitStatus = Status.Unknown;
    
    // indices correspond to direction
    // 0,     1,    2,     3
    // up, down, left, right
    Room[] adjacent = new Room[4];
    
    // The neighbors of this room
    // or the rooms surrounding this one
    ArrayList<Room> neighbors = new ArrayList();

    /**
     * The room constructor which creates a room based on the 
     * column position and the row position.
     * @param col
     * @param row 
     */
    public Room(int col, int row) {
        this.col = col;
        this.row = row;
    }

    /**
     * Tostring method that gives you the position and the information
     * of the room
     * @return 
     */
    @Override
    public String toString() {
        String output = "";
        output += col + "," + row + "\n";
        
        if (visited) {
            output += "v";
        }
        if (wumpus) {
            output += "w";
        }
        if (pit) {
            output += "p";
        }
        if (gold) {
            output += "g";
        }
        if (breeze) {
            output += "b";
        }
        if (stench) {
            output += "s";
        }
        
        return output;
    }
    
    /**
     * The position of the room
     * @return 
     */
    String position() {
        return col + "," + row;
    }
}
