/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Class to draw graphics of the csp getting solved.
 *
 * @author Karl
 */
public class CaveFrame {

    /**
     * How big the nodes are on the graphic
     */
    public static final int NODE_SIZE = 100;
    private BoardPanel boardPanel;
    JFrame f;

    /**
     * BoardFrame constructor
     *
     * @param board
     * @param title
     */
    public CaveFrame(Cave cave, String title) {
        boardPanel = new BoardPanel(cave);

        f = new JFrame(title);
        int size = NODE_SIZE * (cave.size + 2);
        f.setSize(size, size);
        f.add(boardPanel);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private class BoardPanel extends JPanel {

        Cave cave;

        public BoardPanel(Cave cave) {
            this.cave = cave;
        }

        /**
         * Method that draws the current board to the JPanel
         *
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 24));
            
            for (int i = 0; i < cave.size; i++) {
                for (int j = 0; j < cave.size; j++) {
                    Room room = cave.rooms[i][j];

                    int x = j * NODE_SIZE;
                    int y = i * NODE_SIZE;
                    g.drawRect(x, y, NODE_SIZE, NODE_SIZE);
                    
                    String text = room.toString();
                    
                    for (String line : text.split("\n")) {
                        g.drawString(line, x + 2, y += g.getFontMetrics().getHeight());
                    }
                }
            }
        }
    }
}
