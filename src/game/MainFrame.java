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
    
    public static ArrayList<Civ> civs = new ArrayList<>();

    public static int food = 0;
    public static int stone = 0;
    public static int bricks = 0;
    public static int power = 0;
    public static int wood = 0;
    public static int gold = 10;
    public static int pop = 5;
    
    public static boolean classTwo = true;
    public static boolean classThree = true;
    public static boolean diplomacy = false;
    public static boolean economics = false;
    
    public static int crops = 0, livestock = 0, mills = 0, underMines = 0, quarries = 0, nuclears = 0, lumbers = 0, papers = 0, clayMines = 0, antimatters = 0, goldMines = 0, hubs = 0, lvlOnes = 0, lvlTwos = 0, lvlThrees = 0, lvlFours = 0, lvlFives = 0;
    
    public static ArrayList<Planet> planets = new ArrayList<>();
    public static ArrayList<Star> stars = new ArrayList<>();
    public static ArrayList<StarSystem> universe = new ArrayList<>();
    
    public static int screenX = 0, screenY = 0;
    public static Random r = new Random();

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
        gameBoard1.setVisible(true);
        armyPanel1.setVisible(false);
        initUniverse();
        initCivs();
        gameBoard1.anim();
        //This code is gross
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
        universe.add(new StarSystem(5, new Star(r.nextInt(400) + 200, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 1500, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) - 1500, r.nextInt(400) + 200, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 200, r.nextInt(400) - 1500, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
        universe.add(new StarSystem(r.nextInt(5) + 1, new Star(r.nextInt(400) + 200, r.nextInt(400) + 1500, r.nextInt(100) + 50, new Color(r.nextInt(55) + 200, r.nextInt(255), r.nextInt(5)))));
    }

    public void initCivs() {
        civs.add(new Civ("Jack's Empire", universe.get(0), armyPanel1.playerArmy, new Color(0, 100, 255)));
        civs.add(new Civ("Ceric Order", universe.get(1), new Army("Ceric Army", 0), new Color(255, 100, 0)));
        civs.add(new Civ("Xcathli Republic", universe.get(2), new Army("Xcathli Militia", 0), new Color(0, 255, 0)));
        civs.add(new Civ("Order of Oor", universe.get(2), new Army("Oorian Battalion", 0), new Color(255, 0, 255)));
        civs.add(new Civ("Clan of Unspeakable Darkness", universe.get(2), new Army("Army of Unspeakable Darkness", 0), new Color(0, 255, 255)));
        for (int i = 0; i < universe.size(); i++) {
            universe.get(i).owner = civs.get(i);
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

    private class moveAction extends AbstractAction {

        int moveDir;

        moveAction(int move) {
            moveDir = move;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            switch (moveDir) {
                case 1:
                    screenY -= 8;
                    break;
                case 2:
                    screenX += 8;
                    break;
                case 3:
                    screenY += 8;
                    break;
                case 4:
                    screenX -= 8;
                    break;
            }
            repaint();
        }
    }

    private class returnAction extends AbstractAction {

        returnAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (Tile.clickedTile != null) {
                Tile.clickedTile = null;
            } else {
                Planet.clickedPlanet = null;
                for (StarSystem s : universe) {
                    for (Planet p : s.system) {
                        p.clicked = false;
                    }
                }
            }
            repaint();
        }
    }
    
    private class changeAction extends AbstractAction {

        changeAction() {
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!armyPanel1.isVisible()) {
                armyPanel1.setVisible(true);
                armyPanel1.repaint();
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
