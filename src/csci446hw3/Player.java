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
public class Player {
    boolean heardScream, hasArrow = true, hasGold;
    Room room;
    
    public Player(Room startRoom) {
        direction = 0;
        room = startRoom;
    }
    
    // index of Room.adjacent
    // right, down, left, up
    // 0, 1, 2, 3
    int direction;
    
    public void turnRight() {
        direction = ++direction % 4;
    }
    
    public void turnLeft() {
        direction = --direction % 4;
    }
    
    public Room goForward() {
        return room = room.adjacent[direction];
    }
    
    public int getRow() {
        return room.row;
    }
    public int getCol() {
        return room.col;
    }
}
