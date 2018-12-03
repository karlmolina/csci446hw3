/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author h89q624
 */
public class Cave {

    Room[][] rooms;
    Room wumpusRoom, goldRoom;
    int size;
    Player player;

    private Cave() {

    }

    public Cave(int size) {
        this.size = size;
        initializeRooms();
        player = new Player(rooms[1][1]);
        generateGold();
        generateWumpus();
        generatePits();
    }

    public Cave(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new File(file));
        ArrayList<String> lines = new ArrayList();

        while (in.hasNext()) {
            lines.add(in.next());
        }
        this.size = lines.get(0).length();

        initializeRooms();
        player = new Player(rooms[1][1]);

        char[][] charArray = new char[size][size];

        for (int i = 0; i < size; i++) {
            charArray[i] = lines.get(size - 1 - i).toCharArray();
        }

        for (int col = 0; col < size; col++) {
            for (int row = 0; row < size; row++) {
                char currentChar = lines.get(size - 1 - row).toCharArray()[col];
                Room currentRoom = rooms[col + 1][row + 1];

                switch (currentChar) {
                    case 'W':
                        currentRoom.wumpus = true;
                        wumpusRoom = currentRoom;
                        break;
                    case 'P':
                        currentRoom.pit = true;
                        break;
                    case 'G':
                        currentRoom.gold = true;
                        goldRoom = currentRoom;
                        break;
                    default:
                        break;
                }
            }
        }

        generateStench();
        generateBreeze();
    }

    void initializeRooms() {
        rooms = new Room[size + 1][size + 1];

        // Initialize rooms
        for (int col = 1; col <= size; col++) {
            for (int row = 1; row <= size; row++) {
                // Initialize room
                Room room = new Room(col, row);
                rooms[col][row] = room;
            }
        }

        // Set adjacent rooms
        for (int col = 1; col <= size; col++) {
            for (int row = 1; row <= size; row++) {
                Room room = rooms[col][row];
                // up
                if (col != size) {
                    room.adjacent[3] = rooms[col + 1][row];
                }
                // down
                if (col != 1) {
                    room.adjacent[1] = rooms[col - 1][row];
                }
                // left
                if (row != 1) {
                    room.adjacent[2] = rooms[col][row - 1];
                }
                // right
                if (row != size) {
                    room.adjacent[0] = rooms[col][row + 1];
                }
            }
        }

        for (Room[] roomArray : rooms) {
            for (Room room : roomArray) {
                if (room != null) {
                    for (Room adjRoom : room.adjacent) {
                        if (adjRoom != null) {
                            room.neighbors.add(adjRoom);
                        }
                    }
                }
            }
        }
    }

    void generateGold() {
        Random rand = new Random();

        // Set place of gold
        int gRow, gCol;
        do {
            gRow = rand.nextInt(size) + 1;
            gCol = rand.nextInt(size) + 1;
        } while (gRow == 1 && gCol == 1);

        goldRoom = rooms[gRow][gCol];
        goldRoom.gold = true;
        goldRoom.glitter = true;
    }

    void generateWumpus() {
        Random rand = new Random();

        // Set place of wumpus
        int wRow, wCol;
        do {
            wRow = rand.nextInt(size) + 1;
            wCol = rand.nextInt(size) + 1;
        } while (wRow == 1 && wCol == 1);

        wumpusRoom = rooms[wRow][wCol];
        wumpusRoom.wumpus = true;

        generateStench();
    }

    void generateStench() {
        for (Room room : wumpusRoom.adjacent) {
            if (room != null) {
                room.stench = true;
            }
        }
    }

    void generatePits() {
        Random rand = new Random();

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
                }
            }
        }

        generateBreeze();
    }

    void generateBreeze() {
        // Generate breeze
        for (Room[] roomArray : rooms) {
            for (Room room : roomArray) {
                if (room != null) {
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
