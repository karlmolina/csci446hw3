/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import static csci446hw3.Driver.caveFrame;
import csci446hw3.Room.Status;
import java.util.ArrayList;

/**
 *
 * @author Karl
 */
public class Solver {
    static void solve(Cave cave) {
        solveRecursive(cave, cave.player.room);
        
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
            if (solveRecursive(cave, cave.player.room) == null) {
                break;
            };
        }
    }

    static Room solveRecursive(Cave cave, Room room) {
        boolean check = false;
        for (Room neighbor : room.neighbors) {
            if (neighbor.visited) {
                continue;
            }
            if (room.breeze) {
                if (neighbor.pitStatus == Status.Unknown) {
                    neighbor.pitStatus = Status.Dangerous;
                }
            } else {
                neighbor.pitStatus = Status.Safe;
            }
            if (room.stench) {
                if (neighbor.wumpusStatus == Status.Unknown) {
                    neighbor.wumpusStatus = Status.Dangerous;
                }
            } else {
                neighbor.wumpusStatus = Status.Safe;
            }
        }
        for (Room neighbor : room.neighbors) {
            if (!neighbor.visited && neighbor.wumpusStatus == Status.Safe
                    && neighbor.pitStatus == Status.Safe) {
                check = true;
            }
        }
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
                    if (cave.player.room.gold) {
                        cave.player.hasGold = true;
                    }
                    if (cave.player.room.pit) {
                        System.out.println("Fallen into a pit (rip)");
                        cave.player.dead = true;
                    }
                    if (cave.player.room.wumpus) {
                        System.out.println("Death by Wumpus");
                        cave.player.dead = true;
                    }
                    if (cave.player.dead) {
                        return null;
                    }
                    solveRecursive(cave, neighbor);
                    if (cave.player.dead) {
                        return null;
                    }
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
