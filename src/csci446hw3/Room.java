/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

/**
 *
 * @author h89q624
 */
class Room {

    int col, row;
    boolean visited, wumpus, pit, gold, stench, breeze, glitter, bump;

    // indices correspond to direction
    // 0,     1,    2,     3
    // up, down, left, right
    Room[] adjacent = new Room[4];

    public Room(int col, int row) {
        this.col = col;
        this.row = row;
    }

    boolean isAdjacent(Room room) {
        for (Room adjRoom : adjacent) {
            if (room == adjRoom) {
                return true;
            }
        }

        return false;
    }

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
    
    String position() {
        return col + "," + row;
    }
}
