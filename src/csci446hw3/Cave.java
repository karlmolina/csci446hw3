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
 * Cave class represents the wumpus world cave
 * @author Karl Molina, Jordan Palmer
 */
public class Cave {

    // The 2D array of rooms in the cave
    Room[][] rooms;
    // Rooms where the wumpus and the gold are in
    // for debugging and setting the stench
    Room wumpusRoom, goldRoom;
    // Size of the cave
    int size;
    // Player that is in the cave
    Player player;

    /**
     * Cave constructor creates randomly generated cave 
     * of the size given
     * @param size size of the maze
     */
    public Cave(int size) {
        this.size = size;
        initializeRooms();
        // New player at room 1,1
        player = new Player(rooms[1][1]);
        generateGold();
        generateWumpus();
        generatePits();
    }

    /**
     * Cave constructor creates cave from a file
     * @param file path to file of cave
     * @throws FileNotFoundException 
     */
    public Cave(String file) throws FileNotFoundException {
        Scanner in = new Scanner(new File(file));
        ArrayList<String> lines = new ArrayList();
        // Read in the lines of the file
        while (in.hasNext()) {
            lines.add(in.next());
        }
        this.size = lines.get(0).length();
        
        initializeRooms();
        // Player starts at room 1,1
        player = new Player(rooms[1][1]);

        char[][] charArray = new char[size][size];

        for (int i = 0; i < size; i++) {
            charArray[i] = lines.get(size - 1 - i).toCharArray();
        }
        // Set the wumpus, pits, and gold rooms from the file
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
    /**
     * Initializes all the rooms and sets their adjacent rooms and 
     * neighbors
     */
    void initializeRooms() {
        // Creates a room of size + 1 because we do not use row 0
        // or column 0, that way the position of the room
        // is the exact index of that room
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
        // Add the rooms neighbors
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
    
    /**
     * Generates gold in a random room
     */
    void generateGold() {
        Random rand = new Random();

        // Set place of gold
        int gRow, gCol;
        do {
            // Random position
            gRow = rand.nextInt(size) + 1;
            gCol = rand.nextInt(size) + 1;
            // Repeat if the position is 1,1 where the player starts
        } while (gRow == 1 && gCol == 1);

        goldRoom = rooms[gRow][gCol];
        goldRoom.gold = true;
        goldRoom.glitter = true;
    }

    /**
     * Generates a wumpus in a random room
     */
    void generateWumpus() {
        Random rand = new Random();

        // Set place of wumpus
        int wRow, wCol;
        do {
            wRow = rand.nextInt(size) + 1;
            wCol = rand.nextInt(size) + 1;
            // Loop if the wumpus is in 1,1 where the player starts
        } while (wRow == 1 && wCol == 1);

        wumpusRoom = rooms[wRow][wCol];
        wumpusRoom.wumpus = true;

        generateStench();
    }

    /**
     * Generate stench for the neighbors of the room that the 
     * wumpus is in
     */
    void generateStench() {
        for (Room room : wumpusRoom.adjacent) {
            if (room != null) {
                room.stench = true;
            }
        }
    }

    /**
     * Generate pits in random rooms with a chance of 20%
     */
    void generatePits() {
        Random rand = new Random();

        // Generate pits
        for (Room[] roomArray : rooms) {
            for (Room room : roomArray) {
                if (room != null) {
                    // 20 percent chance to generate pit
                    if (rand.nextDouble() > 0.8) {
                        // Cannot generate a pit in the starting 
                        // position
                        if (!(room.row == 1 && room.col == 1)) {
                            room.pit = true;
                        }
                    }
                }
            }
        }

        generateBreeze();
    }

    /**
     * Generate breezes for the pits
     */
    void generateBreeze() {
        // Generate breezes
        for (Room[] roomArray : rooms) {
            for (Room room : roomArray) {
                if (room != null) {
                    // Find a room that has a pit
                    if (room.pit) {
                        // Set that room's neighbors to have breezes
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
