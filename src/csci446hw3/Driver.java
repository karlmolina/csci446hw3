/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.io.FileNotFoundException;

/**
 *
 * @author h89q624
 */
public class Driver {

    public static CaveFrame caveFrame;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        int[] caveSizes = new int[]{4, 5, 8, 10};
        for (int i = 0; i < caveSizes.length; i++) {
            int caveSize = caveSizes[i];

            Cave cave = new Cave(caveSize);
            //Cave cave = new Cave("data/testworld5.txt");
            caveFrame = new CaveFrame(cave, "WUMPUS WORLD");
            caveFrame.f.validate();
            caveFrame.f.repaint();

            Solver.solve(cave);
            
            System.out.println("done");
        }
    }
}
