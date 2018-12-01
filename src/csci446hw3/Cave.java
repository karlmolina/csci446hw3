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

    public Cave(int size) {
        this.size = size;
        rooms = new Room[size][size];
        Random rand = new Random();

        // Initialize rooms
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Initialize room
                Room room = new Room(i, j);
                rooms[i][j] = room;
            }
        }

        // Set adjacent rooms
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                Room room = rooms[i][j];
                if (i != 0) {
                    room.adjacent[0] = rooms[i - 1][j];
                }
                if (i != size - 1) {
                    room.adjacent[1] = rooms[i + 1][j];
                }

                if (j != 0) {
                    room.adjacent[2] = rooms[i][j - 1];
                }
                if (j != size - 1) {
                    room.adjacent[3] = rooms[i][j + 1];
                }
            }
        }

        // Set place of gold
        int gRow, gCol;
        do {
            gRow = rand.nextInt(size);
            gCol = rand.nextInt(size);
        } while (gRow == 0 && gCol == 0);

        goldRoom = rooms[gRow][gCol];
        goldRoom.gold = true;
        goldRoom.glitter = true;

        // Set place of wumpus
        int wRow, wCol;
        do {
            wRow = rand.nextInt(size);
            wCol = rand.nextInt(size);
        } while (wRow == 0 && wCol == 0);

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
                // 20 percent chance to generate pit
                if (rand.nextDouble() > 0.8) {
                    if (room.row != 0 && room.col != 0) {
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
