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
        Cave cave = new Cave(10);
        //Cave cave = new Cave("data/testworld2.txt");
        caveFrame = new CaveFrame(cave, "WUMPUS WORLD");
        caveFrame.f.validate();
        caveFrame.f.repaint();

        Solver.solve(cave);
        int score = -cave.player.moves;
        if (cave.player.hasGold) {
            score += 1000;
        }

        System.out.println("Score: " + score);
        System.out.println("done");
    }
}
