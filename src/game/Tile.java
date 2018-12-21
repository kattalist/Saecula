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
public class Tile {
    int x, y, type; //TYPES: 0 - GRASS, 1 - WATER, 2 - STONE, 3 - FOREST, 4 - CLAY, 5 - ORE
    public Tile (int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    } 
}
