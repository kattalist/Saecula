/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author 073787251
 */
public class GameBoard extends javax.swing.JPanel {

    public boolean interacting = false;
    public static int foodDiff = 0, stoneDiff = 0, brickDiff = 0, powerDiff = 0, woodDiff = 0, goldDiff = 0, popDiff = 0;

    /**
     * Creates new form GameBoard
     */
    public GameBoard() {
        initComponents();
        setBackground(Color.black);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Planet.clickedPlanet == null) {
                    for (StarSystem s : MainFrame.universe) {
                        for (Planet p : s.system) {
                            double cx = p.x + (p.size / 2);
                            double cy = p.y + (p.size / 2);
                            double xDiff = Math.abs(cx - p.parent.x);
                            double yDiff = Math.abs(cy - p.parent.y);
                            double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
                            if (p.mousedOver(MouseInfo.getPointerInfo().getLocation(), new Point((int) (p.parent.x - Math.cos(Math.toRadians(p.orbitAngle - 90)) * dist), (int) (p.parent.y - Math.sin(Math.toRadians(p.orbitAngle - 90)) * dist)))) {
                                p.clicked = true;
                                Planet.clickedPlanet = p;
                                if (p.parentSystem.owner != MainFrame.civs.get(0)) {
                                    interacting = true;
                                }
                            }
                        }
                    }
                } else {
                    if (Tile.clickedTile == null) {
                        for (Tile t : Planet.clickedPlanet.planetBoard) {
                            if (((MouseInfo.getPointerInfo().getLocation().x - 13) - ((MouseInfo.getPointerInfo().getLocation().x - 13) % 50) == t.x) && ((MouseInfo.getPointerInfo().getLocation().y - 35) - ((MouseInfo.getPointerInfo().getLocation().y - 35) % 50) == t.y)) {
                                Tile.clickedTile = t;
                            }
                        }
                    } else {
                        if (MouseInfo.getPointerInfo().getLocation().x - 13 >= 100 && MouseInfo.getPointerInfo().getLocation().x - 13 <= 600) {
                            if (MouseInfo.getPointerInfo().getLocation().y - 35 <= 300 && MouseInfo.getPointerInfo().getLocation().y - 35 >= 200) {
                                MainFrame.civs.get(0).useTile(Tile.clickedTile, 1);
                                Tile.clickedTile = null;
                            } else if (MouseInfo.getPointerInfo().getLocation().y - 35 <= 500 && MouseInfo.getPointerInfo().getLocation().y - 35 >= 400) {
                                MainFrame.civs.get(0).useTile(Tile.clickedTile, 2);
                                Tile.clickedTile = null;
                            } else if (MouseInfo.getPointerInfo().getLocation().y - 35 <= 700 && MouseInfo.getPointerInfo().getLocation().y - 35 >= 600) {
                                MainFrame.civs.get(0).useTile(Tile.clickedTile, 3);
                                Tile.clickedTile = null;
                            }
                        }
                    }
                }
            }
        });
    }

    public void anim() {
        Timer t1 = new Timer(17, new TimerListener());
        Timer minUpdate = new Timer(6000, new MinuteTimer());
        t1.start();
        minUpdate.start();
    }

    public void drawBoard(Graphics g) {
        if (interacting) {
            alienInteraction(Planet.clickedPlanet.parentSystem.owner, g);
        } else {
            if (Tile.clickedTile != null) {
                Tile.clickedTile.displayClickedScreen(g);
            } else {
                for (StarSystem s : MainFrame.universe) {
                    s.display(g);
                    if (s.owner != MainFrame.civs.get(0)) {
                        g.setColor(s.owner.outlineColor);
                        int[] xPoints = {s.centerStar.x - MainFrame.screenX - 30, s.centerStar.x - MainFrame.screenX, s.centerStar.x - MainFrame.screenX - 30};
                        int[] yPoints = {s.centerStar.y - MainFrame.screenY - 30, s.centerStar.y - MainFrame.screenY, s.centerStar.y - MainFrame.screenY + 30};
                        if (s.centerStar.x - MainFrame.screenX > 800) {
                            xPoints[0] = 770;
                            xPoints[1] = 800;
                            xPoints[2] = 770;
                            g.drawPolyline(xPoints, yPoints, 3);
                        } else if (s.centerStar.x - MainFrame.screenX < 0) {
                            xPoints[0] = 30;
                            xPoints[1] = 0;
                            xPoints[2] = 30;
                            g.drawPolyline(xPoints, yPoints, 3);
                        } else if (s.centerStar.y - MainFrame.screenY < 0) {
                            xPoints[0] = s.centerStar.x - MainFrame.screenX - 30;
                            xPoints[1] = s.centerStar.x - MainFrame.screenX;
                            xPoints[2] = s.centerStar.x - MainFrame.screenX + 30;
                            yPoints[0] = 30;
                            yPoints[1] = 0;
                            yPoints[2] = 30;
                            g.drawPolyline(xPoints, yPoints, 3);
                        } else if (s.centerStar.y - MainFrame.screenY > 800) {
                            xPoints[0] = s.centerStar.x - MainFrame.screenX - 30;
                            xPoints[1] = s.centerStar.x - MainFrame.screenX;
                            xPoints[2] = s.centerStar.x - MainFrame.screenX + 30;
                            yPoints[0] = 733;
                            yPoints[1] = 763;
                            yPoints[2] = 733;
                            g.drawPolyline(xPoints, yPoints, 3);
                        }
                    }
                }
            }
            if (Planet.clickedPlanet != null && Tile.clickedTile == null) {
                String[] lines = {MainFrame.civs.get(0).gold + " credits", MainFrame.civs.get(0).food + " food", MainFrame.civs.get(0).stone + " stone", MainFrame.civs.get(0).bricks + " bricks", MainFrame.civs.get(0).power + " power", MainFrame.civs.get(0).wood + " wood", MainFrame.civs.get(0).pop + " people"};
                int[] diffs = {MainFrame.civs.get(0).goldDiff, MainFrame.civs.get(0).foodDiff, MainFrame.civs.get(0).stoneDiff, MainFrame.civs.get(0).brickDiff, MainFrame.civs.get(0).powerDiff, MainFrame.civs.get(0).woodDiff, MainFrame.civs.get(0).popDiff};
                g.setColor(Color.WHITE);
                g.setFont(g.getFont().deriveFont(18.0f));
                for (int i = 0; i < lines.length; i++) {
                    g.setColor(Color.WHITE);
                    g.drawString(lines[i], 650, i * 25 + 50);
                    if (diffs[i] > 0) {
                        g.setColor(Color.GREEN);
                        g.drawString("\u25B2" + diffs[i], 600, i * 25 + 50);
                    } else if (diffs[i] < 0) {
                        g.setColor(Color.RED);
                        g.drawString("\u25BC" + Math.abs(diffs[i]), 600, i * 25 + 50);
                    }
                }
                g.setColor(Color.WHITE);
                g.drawString("Unrest :", 100, 700);
                g.setColor(Color.RED);
                g.fillRect(200, 682, (int) MainFrame.civs.get(0).unrest, 18);
            }
        }
    }

    public void alienInteraction(Civ alien, Graphics g) {
        g.setColor(Color.WHITE);
        g.drawString("You are now interacting with The "+ alien.name, 100, 100);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (Civ c : MainFrame.civs) {
                if (c.food < 0 || c.gold < 0 || c.power < 0) {
                    c.unrest += 0.1;
                }
                if (c.unrest >= 100) {
                    c.gameOver = true;
                }
            }
            repaint();
        }
    }

    private class MinuteTimer implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            for (Civ c : MainFrame.civs) {
                if (c != MainFrame.civs.get(0)) {
                    c.performAction();
                }
                int pfood = c.food, pgold = c.gold, ppower = c.power, pstone = c.stone, pbricks = c.bricks, pwood = c.wood, ppop = c.pop;
                //CROP FARMS
                c.food += (c.crops * 5);
                c.gold -= (c.crops * 2);
                //LIVESTOCK FARMS
                c.food += (c.livestock * 8);
                c.gold -= (c.livestock * 5);
                //MILLS
                c.power += (c.mills * 5);
                c.gold -= (c.mills * 3);
                //UNDERWATER MINES
                c.gold += (c.underMines * 5);
                c.food -= (c.underMines * 5);
                //QUARRIES
                c.stone += (c.quarries * 5);
                c.food -= (c.quarries * 3);
                //NUCLEAR TESTING
                c.power -= (c.nuclears * 10);
                //LUMBER MILLS
                c.wood += (c.lumbers * 5);
                c.food -= (c.lumbers * 2);
                //PAPER FACTORY
                c.wood -= (c.papers * 2);
                //CLAY MINES
                c.bricks += (c.clayMines * 5);
                c.food -= (c.clayMines * 3);
                //ANTIMATTER TESTING
                c.power -= (c.antimatters * 20);
                //GOLD MINES
                c.gold += (c.goldMines * 5);
                c.food -= (c.goldMines * 3);
                //TRADE HUBS
                c.food -= (c.hubs * 3);
                //COLONIES
                //LVL 1
                c.pop += (c.lvlOnes * 2);
                c.pop += (c.lvlTwos * 4);
                c.pop += (c.lvlThrees * 10);
                c.pop += (c.lvlFours * 25);
                c.pop += (c.lvlFives * 60);

                c.gold += (c.lvlOnes * 3);
                c.gold += (c.lvlTwos * 5);
                c.gold += (c.lvlThrees * 15);
                c.gold += (c.lvlFours * 40);
                c.gold += (c.lvlFives * 100);

                c.food -= (c.lvlOnes * 2);
                c.food -= (c.lvlTwos * 5);
                c.food -= (c.lvlThrees * 10);
                c.food -= (c.lvlFours * 20);
                c.food -= (c.lvlFives * 50);

                c.power -= (c.lvlOnes * 5);
                c.power -= (c.lvlTwos * 10);
                c.power -= (c.lvlThrees * 15);
                c.power -= (c.lvlFours * 25);
                c.power -= (c.lvlFives * 50);
                //Afterwards, calculate the amount each resource has changed
                c.foodDiff = c.food - pfood;
                c.stoneDiff = c.stone - pstone;
                c.brickDiff = c.bricks - pbricks;
                c.powerDiff = c.power - ppower;
                c.woodDiff = c.wood - pwood;
                c.goldDiff = c.gold - pgold;
                c.popDiff = c.pop - ppop;
            }
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
