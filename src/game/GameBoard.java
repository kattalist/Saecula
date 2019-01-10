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
                                if (Tile.clickedTile.usage == 1) {
                                    if (Tile.clickedTile.colonyLevel < 5) {
                                        Tile.clickedTile.colonyLevel++;
                                        if (Tile.clickedTile.colonyLevel == 2) {
                                            MainFrame.lvlOnes --;
                                            MainFrame.lvlTwos ++;
                                        } else if (Tile.clickedTile.colonyLevel == 3) {
                                            MainFrame.lvlTwos --;
                                            MainFrame.lvlThrees ++;
                                        } else if (Tile.clickedTile.colonyLevel == 4) {
                                            MainFrame.lvlThrees --;
                                            MainFrame.lvlFours ++;
                                        } else if (Tile.clickedTile.colonyLevel == 5) {
                                            MainFrame.lvlFours --;
                                            MainFrame.lvlFives ++;
                                        }
                                    }
                                } else {
                                    Tile.clickedTile.usage = 1;
                                    Tile.clickedTile.colonyLevel = 1;
                                    MainFrame.lvlOnes ++;
                                }
                                Tile.clickedTile = null;
                            } else if (MouseInfo.getPointerInfo().getLocation().y - 35 <= 500 && MouseInfo.getPointerInfo().getLocation().y - 35 >= 400) {
                                switch (Tile.clickedTile.type) {
                                    case 0:
                                        if (MainFrame.gold >= 5) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.gold -= 5;
                                            MainFrame.crops ++;
                                        }
                                        break;
                                    case 1:
                                        if (MainFrame.stone >= 10) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.stone -= 10;
                                            MainFrame.mills ++;
                                        } else if (MainFrame.bricks >= 10) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.bricks -= 10;
                                            MainFrame.mills ++;
                                        }
                                        break;
                                    case 2:
                                        if (MainFrame.wood >= 3) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.wood -= 3;
                                            MainFrame.quarries ++;
                                        }
                                        break;
                                    case 3:
                                        Tile.clickedTile.usage = 2;
                                        MainFrame.lumbers ++;
                                        break;
                                    case 4:
                                        if (MainFrame.wood >= 3) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.wood -= 3;
                                            MainFrame.clayMines ++;
                                        }
                                        break;
                                    case 5:
                                        if (MainFrame.wood >= 2) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.wood -= 2;
                                            MainFrame.goldMines ++;
                                        }
                                        break;
                                }
                                Tile.clickedTile = null;
                            } else if (MouseInfo.getPointerInfo().getLocation().y - 35 <= 700 && MouseInfo.getPointerInfo().getLocation().y - 35 >= 600) {
                                switch (Tile.clickedTile.type) {
                                    case 0:
                                        if (MainFrame.gold >= 5) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.gold -= 5;
                                            MainFrame.livestock ++;
                                        }
                                        break;
                                    case 1:
                                        if (MainFrame.stone >= 5) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.stone -= 5;
                                            MainFrame.underMines ++;
                                        } else if (MainFrame.bricks >= 5) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.bricks -= 5;
                                            MainFrame.underMines ++;
                                        }
                                        break;
                                    case 2:
                                        if (MainFrame.gold >= 5) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.gold -= 5;
                                            MainFrame.nuclears ++;
                                            MainFrame.classTwo = true;
                                        }
                                        break;
                                    case 3:
                                        if (MainFrame.stone >= 5) {
                                            Tile.clickedTile.usage = 2;
                                            MainFrame.stone -= 5;
                                            MainFrame.papers ++;
                                            MainFrame.diplomacy = true;
                                        } else if (MainFrame.bricks >= 5) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.bricks -= 5;
                                            MainFrame.papers ++;
                                            MainFrame.diplomacy = true;
                                        }
                                        break;
                                    case 4:
                                        if (MainFrame.gold >= 20) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.gold -= 20;
                                            MainFrame.antimatters ++;
                                            MainFrame.classThree = true;
                                        }
                                        break;
                                    case 5:
                                        if (MainFrame.gold >= 3) {
                                            Tile.clickedTile.usage = 3;
                                            MainFrame.gold -= 3;
                                            MainFrame.hubs ++;
                                            MainFrame.economics = true;
                                        }
                                        break;
                                }
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
        Timer minUpdate =  new Timer(10000, new MinuteTimer());
        t1.start();
        minUpdate.start();
    }

    public void drawBoard(Graphics g) {
        if (Tile.clickedTile != null) {
            Tile.clickedTile.displayClickedScreen(g);
        } else {
            for (StarSystem s : MainFrame.universe) {
                s.display(g);
            }
        }
        if (Planet.clickedPlanet != null) {
            String[] lines = {MainFrame.gold + " credits",MainFrame.food + " food",MainFrame.stone + " stone",MainFrame.bricks + " bricks",MainFrame.power + " power",MainFrame.wood + " wood",MainFrame.pop + " people"};
            int[] diffs = {goldDiff, foodDiff, stoneDiff, brickDiff, powerDiff, woodDiff, popDiff};
            g.setColor(Color.WHITE);
            g.setFont(g.getFont().deriveFont(18.0f));
            for (int i = 0; i < lines.length; i++) {
                g.setColor(Color.WHITE);
                g.drawString(lines[i], 650, i*25 + 50);
                if (diffs[i] > 0) {
                    g.setColor(Color.GREEN);
                    g.drawString("\u25B2" + diffs[i],600,i*25 + 50);
                } else if (diffs[i] < 0) {
                    g.setColor(Color.RED);
                    g.drawString("\u25BC" + Math.abs(diffs[i]),600,i*25 + 50);
                }
            }
            /*g.drawString(MainFrame.gold + " credits", 625, 50);
            g.drawString(MainFrame.food + " food", 625, 75);
            g.drawString(MainFrame.stone + " stone", 625, 100);
            g.drawString(MainFrame.bricks + " bricks", 625, 125);
            g.drawString(MainFrame.power + " power", 625, 150);
            g.drawString(MainFrame.wood + " wood", 625, 175);
            g.drawString(MainFrame.pop + " people", 625, 200);*/
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            repaint();
        }
    }

    private class MinuteTimer implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int pfood = MainFrame.food, pgold = MainFrame.gold, ppower = MainFrame.power, pstone = MainFrame.stone, pbricks = MainFrame.bricks, pwood = MainFrame.wood, ppop = MainFrame.pop;
            //CROP FARMS
            MainFrame.food += (MainFrame.crops * 5); 
            MainFrame.gold -= (MainFrame.crops * 2);
            //LIVESTOCK FARMS
            MainFrame.food += (MainFrame.livestock * 8); 
            MainFrame.gold -= (MainFrame.livestock * 5);
            //MILLS
            MainFrame.power += (MainFrame.mills * 5); 
            MainFrame.gold -= (MainFrame.mills * 3);
            //UNDERWATER MINES
            MainFrame.gold += (MainFrame.underMines * 5); 
            MainFrame.food -= (MainFrame.underMines * 5);
            //QUARRIES
            MainFrame.stone += (MainFrame.quarries * 5); 
            MainFrame.food -= (MainFrame.quarries * 3);
            //NUCLEAR TESTING
            MainFrame.power -= (MainFrame.nuclears * 10);
            //LUMBER MILLS
            MainFrame.wood += (MainFrame.lumbers * 5); 
            MainFrame.food -= (MainFrame.lumbers * 2);
            //PAPER FACTORY
            MainFrame.gold -= (MainFrame.papers * 2);
            //CLAY MINES
            MainFrame.bricks += (MainFrame.clayMines * 5); 
            MainFrame.food -= (MainFrame.clayMines * 3);
            //ANTIMATTER TESTING
            MainFrame.power -= (MainFrame.antimatters * 20);
            //GOLD MINES
            MainFrame.gold += (MainFrame.goldMines * 5); 
            MainFrame.food -= (MainFrame.goldMines * 2);
            //TRADE HUBS
            MainFrame.food -= (MainFrame.hubs * 3);
            //COLONIES
            //LVL 1
            MainFrame.pop += (MainFrame.lvlOnes * 2);
            MainFrame.pop += (MainFrame.lvlTwos * 4);
            MainFrame.pop += (MainFrame.lvlThrees * 10);
            MainFrame.pop += (MainFrame.lvlFours * 25);
            MainFrame.pop += (MainFrame.lvlFives * 60);
            
            MainFrame.gold += (MainFrame.lvlOnes * 3);
            MainFrame.gold += (MainFrame.lvlTwos * 5);
            MainFrame.gold += (MainFrame.lvlThrees * 15);
            MainFrame.gold += (MainFrame.lvlFours * 40);
            MainFrame.gold += (MainFrame.lvlFives * 100);
            
            MainFrame.food -= (MainFrame.lvlOnes * 2);
            MainFrame.food -= (MainFrame.lvlTwos * 5);
            MainFrame.food -= (MainFrame.lvlThrees * 10);
            MainFrame.food -= (MainFrame.lvlFours * 20);
            MainFrame.food -= (MainFrame.lvlFives * 50);
            //Afterwards, calculate the amount each resource has changed
            foodDiff = MainFrame.food - pfood;
            stoneDiff = MainFrame.stone - pstone;
            brickDiff = MainFrame.bricks - pbricks;
            powerDiff = MainFrame.power - ppower;
            woodDiff = MainFrame.wood - pwood;
            goldDiff = MainFrame.gold - pgold;
            popDiff = MainFrame.pop - ppop;
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
