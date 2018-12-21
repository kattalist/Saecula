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
import java.awt.event.MouseEvent;
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
                System.out.println("Wow");
                g.setColor(tileColors[t.type]);
                g.drawRect(t.x, t.y, 50, 50);
            }
        }
    }

    public void genBoard() {
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 12; j++) {
                planetBoard.add(new Tile(i * 50, j * 50, 0));
            }
        }
        //Randomly seed some tiles with water
        Random r = new Random();
        for (int i = 0; i < 3; i++) {
            int seedCenter = r.nextInt(planetBoard.size());
            planetBoard.get(seedCenter).type = 1;
            for (int j = 0; j < r.nextInt(3); j++) {
                try {
                    planetBoard.get(seedCenter - 8 * j).type = 1;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            for (int j = 0; j < r.nextInt(3); j++) {
                try {
                    planetBoard.get(seedCenter + 8 * j).type = 1;
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            for (int j = 0; j < r.nextInt(3); j++) {
                try {
                    if ((int) (seedCenter + j / 8) > (int) (seedCenter / 8)) {
                        planetBoard.get(seedCenter + j).type = 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
            for (int j = 0; j < r.nextInt(3); j++) {
                try {
                    if ((int) (seedCenter + j / 8) < (int) (seedCenter / 8)) {
                        planetBoard.get(seedCenter - j).type = 1;
                    }
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
            }
        }
    }
}
