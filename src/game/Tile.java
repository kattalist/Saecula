/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.MouseInfo;
import java.awt.Point;

/**
 *
 * @author 073787251
 */
public class Tile {
    int x, y, type; //TYPES: 0 - GRASS, 1 - WATER, 2 - STONE, 3 - FOREST, 4 - CLAY, 5 - ORE
    
    int usage = 2; //0 - unused, 1 - colony, 2 - special use 1, 3 - special use 2
    /*
    COLONY STATS:
        Level 1: +2 population/min, +2 gold/min, -1 food/4 people/min
        Level 2: +4 population/min, +5 gold/min, -1 food/6 people/min (5c + 5sb cost)
        Level 3: +10 population/min, +15 gold/min, -1 food/15 people/min (15c + 20sb cost)
        Level 4: +25 population/min, +40 gold/min, -1 food/25 people/min (30c + 40sb cost)
        Level 5: +60 population/min, +100 gold/min, -1 food/40 people/min (75c + 100sb cost)
    SPECIAL USES:
        Grass - farm crops(+5 food/min, -2 credits/min(5c cost)) | farm livestock(+8 food/min, -5 credits/min(5c cost))
        Water - water mill(+5 power/min, -3 credits/min(10sb cost)) | underwater mining(+5 credits/min, -5 food/min(no cost))
        Stone - mine(+5 stone/min, -3 food/min(3w cost)) | nuclear testing(Unlocks Class II weaponry, -10 food/min(5c cost))
        Forest - chop trees (+5 wood/min, -2 food/min(no cost)) | make paper(Unlocks Diplomacy, -2 credits/min(no cost))
        Clay - make bricks(+5 bricks/min, -3 food/min(3w cost)) | antimatter testing(Unlocks Class III weaponry, -20 food/min(10c cost))
        Ore - mine gold(+5 credits/min, -3 food/min(2w cost)) | build trade hub(Unlocks Economics, -3 food/min(3c cost))
    */
    public static String[] tileTypes = {"Grass","Water","Stone","Forest","Clay","Ore"};
    public Tile (int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    } 
    
    public static Tile mousedOver() {
        if (Planet.clickedPlanet != null) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            for (Tile t: Planet.clickedPlanet.planetBoard) {
                if ((t.x - t.x%50 == p.x - 13 - (p.x - 13)%50) && (t.y - t.y%50 == p.y - 35 - (p.y - 35)%50)) {
                    return t;
                }
            }
        }
        return null;
    }
}
