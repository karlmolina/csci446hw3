/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package csci446hw3;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
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
    public static final int NODE_SIZE = 80;
    private BoardPanel boardPanel;
    JFrame f;

    /**
     * CaveFrame constructor
     *
     * @param board
     * @param title
     */
    public CaveFrame(Cave cave, String title) {
        boardPanel = new BoardPanel(cave);

        f = new JFrame(title);
        int size = NODE_SIZE * (cave.size);

        f.add(boardPanel);
        f.setSize(size + 17, size + 70);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }

    private class BoardPanel extends JPanel implements MouseMotionListener {

        Cave cave;
        int mouseX, mouseY;
        Image breeze, v, stench, wumpus, gold, pit, player;

        public BoardPanel(Cave cave) {
            this.cave = cave;
            addMouseMotionListener(this);
            Toolkit t = Toolkit.getDefaultToolkit();
            // Initialize the graphics images
            breeze = t.getImage("images/breeze.gif");
            v = t.getImage("images/visisted.gif");
            stench = t.getImage("images/stench.gif");
            wumpus = t.getImage("images/wumpus.gif");
            gold = t.getImage("images/gold.gif");
            pit = t.getImage("images/pit.gif");
            player = t.getImage("images/player_right.gif");
        }

        /**
         * Method that draws the current board to the JPanel
         *
         * @param g
         */
        @Override
        public void paint(Graphics g) {
            g.setColor(Color.WHITE);
            g.fillRect(0, 0, this.getSize().width, this.getSize().height);
            g.setColor(Color.BLACK);
            g.setFont(new Font("Arial", Font.PLAIN, 14));

            // Draw each room depending on what they contain
            for (int col = 1; col <= cave.size; col++) {
                for (int row = 1; row <= cave.size; row++) {
                    Room room = cave.rooms[col][row];

                    int y = (cave.size - row ) * NODE_SIZE;
                    int x = (col - 1) * NODE_SIZE;
                    g.drawRect(x, y, NODE_SIZE, NODE_SIZE);

                    // Draw the position of the room
                    g.drawString(room.position(), x + 2, y + 2 + this.getFont().getSize());

                    // Draw images of what is inside of the rooms
                    if (room.breeze && !room.pit) {
                        g.drawImage(breeze, x, y, this);
                    }
                    if (room.pit) {
                        g.drawImage(pit, x, y, this);
                    }
                    if (room.wumpus) {
                        g.drawImage(wumpus, x, y, this);
                    }
                    if (room.stench) {
                        g.drawImage(stench, x, y, this);
                    }
                    if (room.gold) {
                        g.drawImage(gold, x, y, this);
                    }
                }
            }
            
            Player p = cave.player;
            // Draw the player
            g.drawImage(player, (p.getCol()-1)*NODE_SIZE, (cave.size - p.getRow())*NODE_SIZE, this);

            // Draw the position of the mouse
            g.drawString(mouseX + "," + mouseY, 10, this.getSize().height - 5);
        }

        @Override
        public void mouseDragged(MouseEvent e) {
        }

        @Override
        public void mouseMoved(MouseEvent e) {
            mouseX = e.getX();
            mouseY = e.getY();
            this.repaint();
        }
    }
}
