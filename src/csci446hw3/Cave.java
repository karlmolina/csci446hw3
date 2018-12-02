/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.util.Random;

/**
 *
 * @author h89q624
 */
public class Cave {

    Room[][] rooms;
    Room wumpusRoom, goldRoom;
    int size;
    Player player;

    public Cave(int size) {
        
        this.size = size;
        rooms = new Room[size + 1][size + 1];
        Random rand = new Random();

        // Initialize rooms
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                // Initialize room
                Room room = new Room(i, j);
                rooms[i][j] = room;
            }
        }
        
        player = new Player(rooms[1][1]);

        // Set adjacent rooms
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                Room room = rooms[i][j];
                // up
                if (i != size) {
                    room.adjacent[3] = rooms[i + 1][j];
                }
                // down
                if (i != 1) {
                    room.adjacent[1] = rooms[i - 1][j];
                }
                // left
                if (j != 1) {
                    room.adjacent[2] = rooms[i][j - 1];
                }
                // right
                if (j != size) {
                    room.adjacent[0] = rooms[i][j + 1];
                }
            }
        }

        // Set place of gold
        int gRow, gCol;
        do {
            gRow = rand.nextInt(size)+1;
            gCol = rand.nextInt(size)+1;
        } while (gRow == 1 && gCol == 1);

        goldRoom = rooms[gRow][gCol];
        goldRoom.gold = true;
        goldRoom.glitter = true;

        // Set place of wumpus
        int wRow, wCol;
        do {
            wRow = rand.nextInt(size)+1;
            wCol = rand.nextInt(size)+1;
        } while (wRow == 1 && wCol == 1);

        wumpusRoom = rooms[wRow][wCol];
        wumpusRoom.wumpus = true;

        for (Room room : wumpusRoom.adjacent) {
            if (room != null) {
                room.stench = true;
            }
        }

        // Generate pits
        for (Room[] roomArray : rooms) {
            for (Room room : roomArray) {
                if (room != null) {
                    // 20 percent chance to generate pit
                    if (rand.nextDouble() > 0.8) {
                        if (!(room.row == 1 && room.col == 1)) {
                            room.pit = true;
                        }
                    }

                    if (room.pit) {
                        for (Room adjRoom : room.adjacent) {
                            if (adjRoom != null) {
                                adjRoom.breeze = true;
                            }
                        }
                    }
                }
            }
        }
    }
}
