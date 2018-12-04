/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import static csci446hw3.Driver.caveFrame;
import java.io.FileNotFoundException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author h89q624
 */
public class SolverTest {
    
    public SolverTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of solve method, of class Solver.
     */
    @Test
    public void testSolve() {
        System.out.println("solve");
        Cave cave = null;
        Solver.solve(cave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dfs method, of class Solver.
     */
    @Test
    public void testDfs() throws FileNotFoundException {
        System.out.println("dfs");
        Cave cave = new Cave("data/testworld.txt");
        caveFrame = new CaveFrame(cave, "WUMPUS WORLD");
        caveFrame.f.validate();
        caveFrame.f.repaint();
        
        Solver instance = new Solver();
        Room expResult = null;
        Room result = instance.solveRecursive(cave, cave.player.room);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
