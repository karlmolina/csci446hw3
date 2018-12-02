/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import static csci446hw3.Driver.caveFrame;
import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class Solver {
    public static void solve(Cave cave) {
        ArrayList<Room> safe = new ArrayList<>();
        ArrayList<Room> dangerous = new ArrayList<>();
        ArrayList<Room> visited = new ArrayList<>();
        ArrayList<Room> safeNotVisited = new ArrayList<>();
        
        Player player = cave.player;
        
        Room currentRoom = player.room;
        
        currentRoom.visited = true;
        
        if (currentRoom.breeze || currentRoom.stench) {
            for (Room adjRoom : currentRoom.adjacent) {
                if (adjRoom != null && !adjRoom.visited) {
                    dangerous.add(adjRoom);
                }
            }
        } else {
            for (Room adjRoom : currentRoom.adjacent) {
                if (adjRoom != null && !adjRoom.visited) {
                    safeNotVisited.add(adjRoom);
                }
            }
        }
        
        while(!safeNotVisited.isEmpty()) {
            
        }
        while(!dangerous.isEmpty()) {
            
        }
        
        
        
        caveFrame.f.validate();
        caveFrame.f.repaint();
    }
}
