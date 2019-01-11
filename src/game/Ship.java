/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author 073787251
 */
public class Ship {
    String name;
    int health;
    int damage;
    public Ship (String name, int health, int damage) {
        this.name = name;
        this.health = health;
        this.damage = damage;
    }
}
