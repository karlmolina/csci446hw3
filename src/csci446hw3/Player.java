/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

/**
 * Class to keep information about the player
 * @author Karl Molina, Jordan Palmer
 */
public class Player {
    // Boolean to see if the player has heard the scream, if he
    // has an arrow, or if he has the gold
    boolean heardScream, hasArrow = true, hasGold;
    // The amout of moves that the player has taken
    int moves = 0;
    // The room that the player is in
    Room room;
    // Whether or not the player is dead
    boolean dead;
    
    /**
     * Player constructor that creates a player based on the room
     * he starts in.
     * @param startRoom 
     */
    public Player(Room startRoom) {
        room = startRoom;
        room.visited = true;
    }
    
    /**
     * Moves the player to the next room
     * @param room 
     */
    public void move(Room room) {
        this.room = room;
        this.room.visited = true;
        moves++;
    }
    
    /**
     * The row that the player is in
     * @return 
     */
    public int getRow() {
        return room.row;
    }
    
    /**
     * The column that the player is in.
     * @return 
     */
    public int getCol() {
        return room.col;
    }
}
