/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author 073787251
 */
public class Ship {
    String name;
    int health;
    int damage;
    int cost;
    int crew;
    static Ship selectedShip = null;
    public Ship (String name, int health, int damage, int cost, int crew) {
        this.name = name;
        this.health = health;
        this.damage = damage;
        this.cost = cost;
        this.crew = crew;
    }
    
    public void drawDescription (Graphics g) {
        Ship.selectedShip = this;
        g.setColor(Color.WHITE);
        g.drawRect(350, 350, 400, 400);
        g.setFont(g.getFont().deriveFont(24.0f));
        g.drawString(name, 360, 400);
        g.setFont(g.getFont().deriveFont(20.0f));
        g.drawString("Health", 360, 450);
        g.fillRect(450, 434, (int)(health * 0.29), 16);
        g.drawString("Damage", 360, 475);
        g.fillRect(450, 459, (int)(damage * 0.29), 16);
        g.drawString("Cost: "+cost+" credits", 360, 525);
        g.drawString("Requires "+crew+" crew", 360, 550);
    }
}
