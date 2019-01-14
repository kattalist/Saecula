/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;

/**
 *
 * @author 073787251
 */
public class Civ {
    String name;
    StarSystem home;
    Army army;
    Color outlineColor;
    public Civ (String name, StarSystem home, Army army, Color outlineColor) {
        this.name = name;
        this.home = home;
        this.army = army;
        this.outlineColor = outlineColor;
    }
}
