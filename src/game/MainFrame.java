/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.KeyStroke;

/**
 *
 * @author 073787251
 */
public class MainFrame extends javax.swing.JFrame {

    //A list of all the players (human + 4 AI)

    public static ArrayList<Civ> civs = new ArrayList<>();
    //A list of all the star systems in the game
    public static ArrayList<StarSystem> universe = new ArrayList<>();
    //The screen position can shift when the arrow keys are pressed, so adjust for that using these two variables
    public static int screenX = 0, screenY = 0;
    //This random number generator will be used for all random calculations in this class
    public static Random r = new Random();

    //The key listener - arrow keys for movement, ESCAPE to leave a window, E to open the army panel
    private static final int IFW = JComponent.WHEN_IN_FOCUSED_WINDOW;
    private static final String moveRight = "move right";
    private static final String moveLeft = "move left";
    private static final String moveUp = "move up";
    private static final String moveDown = "move down";
    private static final String leaveMenu = "leave menu";
    private static final String openArmy = "open army";

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
        //By default, set the main game board to be visible and the army panel to be unseen
        gameBoard1.setVisible(true);
        armyPanel1.setVisible(false);
        //Initialize all star systems
        initUniverse();
        //Initialize all civilizations
        initCivs();
        //Animate the game board
        gameBoard1.anim();
        //KEY LISTENER
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("LEFT"), moveLeft);
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("RIGHT"), moveRight);
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("UP"), moveUp);
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("DOWN"), moveDown);
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("ESCAPE"), leaveMenu);
        gameBoard1.getInputMap(IFW).put(KeyStroke.getKeyStroke("E"), openArmy);

        gameBoard1.getActionMap().put(moveLeft, new moveAction(4));
        gameBoard1.getActionMap().put(moveRight, new moveAction(2));
        gameBoard1.getActionMap().put(moveUp, new moveAction(1));
        gameBoard1.getActionMap().put(moveDown, new moveAction(3));
        gameBoard1.getActionMap().put(leaveMenu, new returnAction());
        gameBoard1.getActionMap().put(openArmy, new changeAction());
    }

    public static void initUniverse() {
        //Create five different star systems, one in each cardinal direction from the central system
        universe.add(new StarSystem(5, new Star(r.nextInt(400) + 200, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 1500, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) - 1500, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 200, r.nextInt(400) - 1500, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 200, r.nextInt(400) + 1500, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
    }

    public void initCivs() {
        //Create the five civilizations, each with fun names and each bound to a different star system at the start of the game
        civs.add(new Civ("Jack's Empire", universe.get(0), armyPanel1.playerArmy, new Color(0, 100, 255)));
        civs.add(new Civ("Ceric Order", universe.get(1), new Army("Ceric Army", 0), new Color(255, 100, 0)));
        civs.add(new Civ("Xcathli Republic", universe.get(2), new Army("Xcathli Militia", 0), new Color(0, 255, 0)));
        civs.add(new Civ("Order of Oor", universe.get(3), new Army("Oorian Battalion", 0), new Color(255, 0, 255)));
        civs.add(new Civ("Clan of Unspeakable Darkness", universe.get(4), new Army("Army of Unspeakable Darkness", 0), new Color(0, 255, 255)));
        //Make each civilization the "owner" of its respective star system, and assign each one to a new planet to begin the game
        for (int i = 0; i < universe.size(); i++) {
            universe.get(i).owner = civs.get(i);
            civs.get(i).newPlanet();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        gameBoard1 = new game.GameBoard();
        armyPanel1 = new game.ArmyPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout armyPanel1Layout = new javax.swing.GroupLayout(armyPanel1);
        armyPanel1.setLayout(armyPanel1Layout);
        armyPanel1Layout.setHorizontalGroup(
            armyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 800, Short.MAX_VALUE)
        );
        armyPanel1Layout.setVerticalGroup(
            armyPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 763, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout gameBoard1Layout = new javax.swing.GroupLayout(gameBoard1);
        gameBoard1.setLayout(gameBoard1Layout);
        gameBoard1Layout.setHorizontalGroup(
            gameBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(armyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        gameBoard1Layout.setVerticalGroup(
            gameBoard1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(armyPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(gameBoard1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    //Whenever an arrow key is pressed, run the following code to move the screen
    private class moveAction extends AbstractAction {

        //Use an integer to indicate the direction the screen will move in (1 - up, 2 - right, 3 - down, 4 - left)
        int moveDir;

        moveAction(int move) {
            moveDir = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //Depending on the direction, adjust the screenX or screenY variables
            switch (moveDir) {
                case 1: //UP
                    screenY -= 8;
                    break;
                case 2: //RIGHT
                    screenX += 8;
                    break;
                case 3: //DOWN
                    screenY += 8;
                    break;
                case 4: //LEFT
                    screenX -= 8;
                    break;
            }
            repaint();
        }
    }

    //When ESCAPE is pressed, exit the current menu and return to the previous screen (tile action screen returns to planet board screen, which returns to interstellar map screen)
    private class returnAction extends AbstractAction {

        returnAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (gameBoard1.interacting) {
                gameBoard1.interacting = false;
                gameBoard1.clickedX = 0;
                gameBoard1.clickedY = 0;
            }
            //If the clickedTile variable is not null, then a tile menu must be open. If this is the case, set the variable to null.
            if (Tile.clickedTile != null) {
                Tile.clickedTile = null;
            //If clickedTile is null, then either the main window or the planet board menu is open. In this case, we want to check if clickedPlanet is null. If it is, we don't have to do anything. Otherwise...
            } else if (Planet.clickedPlanet != null) {
                //Set the clickedPlanet variable to null, and the clicked variable of the currently selected planet to false. This will close the planet board menu if it is open.
                Planet.clickedPlanet.clicked = false;
                Planet.clickedPlanet = null;
            }
            repaint();
        }
    }

    //If E is pressed, change screens between the army panel and the main game board.
    private class changeAction extends AbstractAction {

        changeAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            //If the army panel is not visible, make it visible
            if (!armyPanel1.isVisible()) {
                armyPanel1.setVisible(true);
                armyPanel1.repaint();
            //Otherwise, make it invisible. This will make the game board visible as a result.
            } else {
                armyPanel1.setVisible(false);
                repaint();
            }
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private game.ArmyPanel armyPanel1;
    private game.GameBoard gameBoard1;
    // End of variables declaration//GEN-END:variables
}
