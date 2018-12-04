/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import static csci446hw3.Driver.caveFrame;
import csci446hw3.Room.Status;

/**
 * The class to solve the wumpus world cave
 * @author Karl Molina, Jordan Palmer
 */
public class Solver {

    /**
     * Solves the wumpus world cave starting in the room that 
     * the player is in.
     * @param cave 
     */
    public static void solve(Cave cave) {
        // Start by trying to solve the cave once
        solveRecursive(cave, cave.player.room);
        // If the player did not find the gold set a dangerous room
        // to safe and try again
        while (!cave.player.hasGold) {
            boolean breakcheck = false;
            for (Room[] roomArray : cave.rooms) {
                for (Room room : roomArray) {
                    if (room != null) {
                        if (!breakcheck && (room.pitStatus == Status.Dangerous || room.wumpusStatus == Status.Dangerous)) {
                            room.pitStatus = Status.Safe;
                            room.wumpusStatus = Status.Safe;
                            breakcheck = true;
                        }
                        room.visited = false;
                    }
                }
            }
            // Try to solve the cave again
            solveRecursive(cave, cave.player.room);
            // Stop if the player is dead
            if (cave.player.dead) {
                break;
            };
        }
        
        System.out.println("Cells entered (moves made) " + cave.player.moves);
        
        // Calculate score
        int score = -cave.player.moves;
        if (cave.player.hasGold) {
            score += 1000;
        }

        System.out.println("Score: " + score);
    }

    /**
     * Recursively search the cave using logics rules and setting
     * rooms to safe or dangerous depending on the percepts that the
     * player perceives.
     * @param cave
     * @param room
     * @return 
     */
    private static Room solveRecursive(Cave cave, Room room) {
        boolean check = false;
        // Logic rules that set the neighbors of the room that the
        // player is in
        for (Room neighbor : room.neighbors) {
            if (neighbor.visited) {
                continue;
            }
            // Set the pit status based on a breeze
            if (room.breeze) {
                if (neighbor.pitStatus == Status.Unknown) {
                    neighbor.pitStatus = Status.Dangerous;
                }
            } else {
                neighbor.pitStatus = Status.Safe;
            }
            // Set the wumpus status of the neighbor 
            // dependent on the stench of the room that you
            // are in.
            if (room.stench) {
                if (neighbor.wumpusStatus == Status.Unknown) {
                    neighbor.wumpusStatus = Status.Dangerous;
                }
            } else {
                neighbor.wumpusStatus = Status.Safe;
            }
        }
        // Check if the room has any neighbors that are not visited
        // and are safe from both a pit and the wumpus
        for (Room neighbor : room.neighbors) {
            if (!neighbor.visited && neighbor.wumpusStatus == Status.Safe
                    && neighbor.pitStatus == Status.Safe) {
                check = true;
            }
        }
        // If there are available rooms then recursively move 
        // to those rooms
        if (check) {
            for (Room neighbor : room.neighbors) {
                if (cave.player.hasGold) {
                    cave.player.room = room;
                    return room;
                }
                if (!neighbor.visited && neighbor.wumpusStatus == Status.Safe
                        && neighbor.pitStatus == Status.Safe) {
                    cave.player.move(neighbor);
                    caveFrame.f.validate();
                    caveFrame.f.repaint();
                    // Die in a pit
                    if (cave.player.room.pit) {
                        System.out.println("Fallen into a pit (rip)");
                        cave.player.dead = true;
                    }
                    // Die by the wumpus
                    if (cave.player.room.wumpus) {
                        System.out.println("Death by Wumpus");
                        cave.player.dead = true;
                    }
                    if (cave.player.dead) {
                        return null;
                    }
                    // Find the gold
                    if (cave.player.room.gold) {
                        System.out.println("Found gold.");
                        cave.player.hasGold = true;
                    }
                    // Recursively move to the next room
                    solveRecursive(cave, neighbor);
                    if (cave.player.dead) {
                        return null;
                    }
                    // Recursively move back to the room 
                    // that you came from
                    cave.player.move(room);
                    caveFrame.f.validate();
                    caveFrame.f.repaint();
                    System.out.print("");
                }
            }
        } else {
            return room;
        }

        return null;
    }
}
