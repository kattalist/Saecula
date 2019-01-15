/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author 073787251
 */
public class Planet {

    public int x, y;
    public int size;
    public Color terrainColor;
    public double orbitAngle = 0;
    public double orbitSize;
    public Star parent;
    public StarSystem parentSystem;
    public double orbitSpeed = (new Random().nextDouble() + 0.1) / 2;
    public ArrayList<Tile> planetBoard = new ArrayList<>();
    public static Color[] tileColors = {Color.GREEN, Color.BLUE, Color.GRAY, new Color(0, 200, 0), Color.RED, Color.YELLOW};
    public boolean clicked = false;
    public static Planet clickedPlanet = null;

    public Planet(int x, int y, int size, Color c) {
        this.x = x;
        this.y = y;
        terrainColor = c;
        this.size = size;
    }

    public Planet(int x, int y, int size, Color c, Star parent) {
        this.x = x;
        this.y = y;
        terrainColor = c;
        this.size = size;
        this.parent = parent;
        orbitSize = Math.sqrt(Math.pow(parent.x - x, 2) + Math.pow(parent.y - y, 2));
    }

    public void display(Graphics g) {
        if (clicked) {
            drawBoard(g);
        } else {
            double rx = x, ry = y;
            if (!(this instanceof Star)) { //IF THIS IS NOT A STAR, OTHERWISE WE HAVE A NULLPOINTEREXCEPTION
                double cx = x + (size / 2);
                double cy = y + (size / 2);
                double xDiff = Math.abs(cx - parentSystem.centerStar.x);
                double yDiff = Math.abs(cy - parentSystem.centerStar.y);
                double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);

                rx = parent.x - Math.cos(Math.toRadians(orbitAngle - 90)) * dist;
                ry = parent.y - Math.sin(Math.toRadians(orbitAngle - 90)) * dist;
                g.setColor(Color.WHITE);
                g.drawOval(parent.x - (int) orbitSize - MainFrame.screenX + size / 2, parent.y - (int) orbitSize - MainFrame.screenY + size / 2, (int) orbitSize * 2 - size, (int) orbitSize * 2 - size);
            }
            g.setColor(terrainColor);
            g.fillOval((int) rx - (size / 2) - MainFrame.screenX, (int) ry - (size / 2) - MainFrame.screenY, size, size);
            try {
                g.setColor(parentSystem.owner.outlineColor);
            } catch (NullPointerException e) {
                g.setColor(Color.WHITE);
            }
            g.drawOval((int) rx - (size / 2) - MainFrame.screenX - 5, (int) ry - (size / 2) - MainFrame.screenY - 5, size + 10, size + 10);
            g.setColor(Color.red);
            g.drawOval(MouseInfo.getPointerInfo().getLocation().x - 13, MouseInfo.getPointerInfo().getLocation().y - 35, 10, 10);
            if (mousedOver(MouseInfo.getPointerInfo().getLocation(), new Point((int) rx, (int) ry))) {
                g.setColor(Color.yellow);
                g.drawOval((int) rx - (size / 2) - MainFrame.screenX - 5, (int) ry - (size / 2) - MainFrame.screenY - 5, size + 10, size + 10);
            }
            orbitAngle += orbitSpeed;
        }
    }

    public boolean mousedOver(Point p, Point planetPoint) {
        Point actualPoint = new Point(p.x - 13, p.y - 30);
        double rx = planetPoint.x - MainFrame.screenX, ry = planetPoint.y - MainFrame.screenY;
        double xDiff = Math.abs(actualPoint.x - rx);
        double yDiff = Math.abs(actualPoint.y - ry);
        double dist = Math.sqrt(xDiff * xDiff + yDiff * yDiff);
        return dist < (size / 2);
    }

    public void drawBoard(Graphics g) {
        if (planetBoard.isEmpty()) {
            genBoard();
            drawBoard(g);
        } else {
            for (Tile t : planetBoard) {
                g.setColor(tileColors[t.type]);
                g.fillRect(t.x, t.y, 49, 49);
                if (t.usage != 0) {
                    Graphics2D g2 = (Graphics2D) g;
                    g2.setColor(parentSystem.owner.outlineColor);
                    g2.setStroke(new BasicStroke(2));
                    g2.drawRect(t.x,t.y,49,49);
                }
            }
            g.setColor(Color.WHITE);
            Point p = MouseInfo.getPointerInfo().getLocation();
            if (p.x - 13 < 600 && p.y  - 35 < 600) {
                g.drawRect(p.x - 14 - (p.x - 13) % 50, p.y - 36 - (p.y - 35) % 50, 50, 50);
            }
            try {
                g.setFont(g.getFont().deriveFont(24.0f));
                g.drawString(Tile.tileTypes[Tile.mousedOver().type] + " Tile", 10, 630);
                g.setFont(g.getFont().deriveFont(16.0f));
                if (Tile.mousedOver().usage == 0) {
                    g.drawString("This tile is unused.", 10, 660);
                } else if (Tile.mousedOver().usage == 1) {
                    g.drawString("There is currently a Level " + Tile.mousedOver().colonyLevel + " colony established here.", 10, 660);
                } else if (Tile.mousedOver().usage == 2) {
                    switch (Tile.mousedOver().type) {
                        case 0:
                            g.drawString("This tile is being used to farm crops.", 10, 660);
                            g.drawString("+5 food/min, -2 credits/min", 10, 680);
                            break;
                        case 1:
                            g.drawString("There is a water mill on this tile.", 10, 660);
                            g.drawString("+5 power/min, -3 credits/min", 10, 680);
                            break;
                        case 2:
                            g.drawString("There is a quarry on this tile.", 10, 660);
                            g.drawString("+5 stone/min, -3 food/min", 10, 680);
                            break;
                        case 3:
                            g.drawString("There is a lumber mill on this tile.", 10, 660);
                            g.drawString("+5 wood/min, -2 food/min", 10, 680);
                            break;
                        case 4:
                            g.drawString("This tile is being used to mine clay for bricks.", 10, 660);
                            g.drawString("+5 bricks/min, -3 food/min", 10, 680);
                            break;
                        case 5:
                            g.drawString("This tile is being used to mine gold.", 10, 660);
                            g.drawString("+5 credits/min, -3 food/min", 10, 680);
                            break;
                    }
                } else {
                    switch (Tile.mousedOver().type) {
                        case 0:
                            g.drawString("This tile is being used to farm livestock.", 10, 660);
                            g.drawString("+8 food/min, -5 credits/min", 10, 680);
                            break;
                        case 1:
                            g.drawString("There is an underwater mine on this tile.", 10, 660);
                            g.drawString("+5 credits/min, -5 food/min", 10, 680);
                            break;
                        case 2:
                            g.drawString("There is a nuclear testing site on this tile.", 10, 660);
                            g.drawString("Unlocks Class II weaponry, -10 food/min", 10, 680);
                            break;
                        case 3:
                            g.drawString("This tile is being used to make paper for books.", 10, 660);
                            g.drawString("Unlocks Diplomacy, -2 wood/min", 10, 680);
                            break;
                        case 4:
                            g.drawString("There is an antimatter testing site on this tile.", 10, 660);
                            g.drawString("Unlocks Class III weaponry, -20 food/min", 10, 680);
                            break;
                        case 5:
                            g.drawString("There is a trade hub on this tile.", 10, 660);
                            g.drawString("Unlocks Economics, -3 food/min", 10, 680);
                            break;
                    }
                }
            } catch (NullPointerException e) {
            }
        }
    }

    public void genBoard() {
        Random r = new Random();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                planetBoard.add(new Tile(i * 50, j * 50, r.nextInt(6)));
            }
        }
    }
}
