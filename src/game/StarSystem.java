/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 073787251
 */
public class StarSystem {

    public Star centerStar;
    public ArrayList<Planet> system = new ArrayList<>();
    public Civ owner;

    public StarSystem(int size, Star s) {
        centerStar = s;
        initPlanets(size);
    }

    public void initPlanets(int size) {
        Random r = new Random();
        for (int i = 0; i < size; i++) {
            system.add(new Planet(centerStar.x, centerStar.y - (i + 3) * 75 + 50, r.nextInt(45) + 5, new Color(r.nextInt(125), r.nextInt(255), r.nextInt(255)), centerStar));
            system.get(i).parentSystem = this;
        }
    }

    public void display(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        for (Planet p : system) {
            if (Planet.clickedPlanet != null) {
                Planet.clickedPlanet.drawBoard(g);
            } else {
                p.display(g);
            }
        }
        if (Planet.clickedPlanet == null) {
            centerStar.display(g);
        }
    }
}
