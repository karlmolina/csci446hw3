/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.io.FileNotFoundException;

/**
 * Wumpus world generator and solver.
 * @author Karl Molina, Jordan Palmer
 */
public class Driver {

    // Swing GUI frame to draw the graphics
    public static CaveFrame caveFrame;

    /**
     * Runs the wumpus world solver on cave sizes of 
     * 4, 5, 8, and 10.
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int[] caveSizes = new int[]{4, 5, 8, 10};
        for (int i = 0; i < caveSizes.length; i++) {
            int caveSize = caveSizes[i];

            Cave cave = new Cave(caveSize);
            caveFrame = new CaveFrame(cave, "WUMPUS WORLD");
            caveFrame.f.validate();
            caveFrame.f.repaint();
            
            System.out.println("Solving " + caveSize + "X" + caveSize + " cave.");
            // Solve the cave
            Solver.solve(cave);
            System.out.println();
        }
    }
}
