/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;

/**
 *
 * @author 073787251
 */
public class Army {
    String name;
    int size;
    ArrayList<Ship> fleet = new ArrayList<>();
    
    public Army(String name, int size) {
        this.name = name;
        this.size = size;
    }
    
    public int strength() {
        int i = 0;
        for (Ship s: fleet) {
            i += s.damage;
        }
        return i;
    }
    
    public int defense() {
        int i = 0;
        for (Ship s: fleet) {
            i += s.health;
        }
        return i;
    }
}
