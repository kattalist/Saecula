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

/**
 *
 * @author 073787251
 */
public class Tile {

    public static Tile clickedTile = null;
    int x, y, type; //TYPES: 0 - GRASS, 1 - WATER, 2 - STONE, 3 - FOREST, 4 - CLAY, 5 - ORE

    int usage = 0; //0 - unused, 1 - colony, 2 - special use 1, 3 - special use 2
    int colonyLevel = 0; //If there is a colony here, this variable will reflect that
    /*
     COLONY STATS:
     Level 1: +2 population/min, +2 gold/min, -1 food/4 people/min
     Level 2: +4 population/min, +5 gold/min, -1 food/6 people/min (5c + 5sb cost)
     Level 3: +10 population/min, +15 gold/min, -1 food/15 people/min (15c + 20sb cost)
     Level 4: +25 population/min, +40 gold/min, -1 food/25 people/min (30c + 40sb cost)
     Level 5: +60 population/min, +100 gold/min, -1 food/40 people/min (75c + 100sb cost)
     SPECIAL USES:
     Grass - farm crops(+5 food/min, -2 credits/min(5c cost)) | farm livestock(+8 food/min, -5 credits/min(5c cost))
     Water - water mill(+5 power/min, -3 credits/min(10sb cost)) | underwater mining(+5 credits/min, -5 food/min(-5sb))
     Stone - mine(+5 stone/min, -3 food/min(3w cost)) | nuclear testing(Unlocks Class II weaponry, -10 power/min(5c cost))
     Forest - chop trees (+5 wood/min, -2 food/min(no cost)) | make paper(Unlocks Diplomacy, -2 credits/min(5sb))
     Clay - make bricks(+5 bricks/min, -3 food/min(3w cost)) | antimatter testing(Unlocks Class III weaponry, -20 power/min(20c cost))
     Ore - mine gold(+5 credits/min, -3 food/min(2w cost)) | build trade hub(Unlocks Economics, -3 food/min(3c cost))
     */
    public static String[] tileTypes = {"Grass", "Water", "Stone", "Forest", "Clay", "Ore"};

    public Tile(int x, int y, int type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public static Tile mousedOver() {
        if (Planet.clickedPlanet != null) {
            Point p = MouseInfo.getPointerInfo().getLocation();
            for (Tile t : Planet.clickedPlanet.planetBoard) {
                if ((t.x - t.x % 50 == p.x - 13 - (p.x - 13) % 50) && (t.y - t.y % 50 == p.y - 35 - (p.y - 35) % 50)) {
                    return t;
                }
            }
        }
        return null;
    }

    public void displayClickedScreen(Graphics g) {
        g.setColor(Color.WHITE);
        if (usage == 0) {
            g.setFont(g.getFont().deriveFont(24.0f));
            g.drawString("This tile is currently unused! Please select an option from below.", 60, 100);
            g.drawString("Colonize this tile (-5 credits)", 250, 256);
            g.setFont(g.getFont().deriveFont(16.0f));
            switch (type) {
                case 0:
                    g.drawString("Farm crops (-5 credits)", 250, 445);
                    g.drawString("+5 food/min, -2 credits/min", 250, 471);
                    g.drawString("Farm livestock (-5 credits)", 250, 645);
                    g.drawString("+8 food/min, -5 credits/min", 250, 671);
                    break;
                case 1:
                    g.drawString("Build a water mill (-10 stone/brick)", 250, 445);
                    g.drawString("+5 power/min, -3 credits/min", 250, 471);
                    g.drawString("Mine underwater (-5 stone/brick)", 250, 645);
                    g.drawString("+10 credits/min, -5 food/min", 250, 671);
                    break;
                case 2:
                    g.drawString("Mine stone (-3 wood)", 250, 445);
                    g.drawString("+5 stone/min, -3 food/min", 250, 471);
                    g.drawString("Build a nuclear testing site (-5 credits)", 250, 645);
                    g.drawString("Unlocks Class II weaponry, -10 power/min", 250, 671);
                    break;
                case 3:
                    g.drawString("Chop trees (no cost)", 250, 445);
                    g.drawString("+5 wood/min, -2 food/min", 250, 471);
                    g.drawString("Build a paper factory (-5 stone/brick)", 250, 645);
                    g.drawString("Unlocks Diplomacy, -2 credits/min", 250, 671);
                    break;
                case 4:
                    g.drawString("Mine clay (-3 wood)", 250, 445);
                    g.drawString("+5 bricks/min, -3 food/min", 250, 471);
                    g.drawString("Build an antimatter testing site(-20 credits)", 250, 645);
                    g.drawString("Unlocks Class III weaponry, -20 power/min", 250, 671);
                    break;
                case 5:
                    g.drawString("Mine gold (-2 wood)", 250, 445);
                    g.drawString("+5 credits/min, -3 food/min", 250, 471);
                    g.drawString("Build a trade hub (-3 credits)", 250, 645);
                    g.drawString("Unlocks Economics, -3 food/min", 250, 671);
                    break;
            }
        } else if (usage == 1) {
            g.setFont(g.getFont().deriveFont(24.0f));
            g.drawString("There is currently a Level " + colonyLevel + " colony established here.", 120, 100);
            g.setFont(g.getFont().deriveFont(16.0f));
            switch (colonyLevel) {
                case 1:
                    g.drawString("Upgrade to a Level 2 colony (-5 credits, -5 stone/brick)", 250, 245);
                    g.drawString("+4 population/min, +5 gold/min, -1 food/6 people/min", 250, 271);
                    break;
                case 2:
                    g.drawString("Upgrade to a Level 3 colony (-15 credits, -20 stone/brick)", 250, 245);
                    g.drawString("+10 population/min, +15 gold/min, -1 food/15 people/min", 250, 271);
                    break;
                case 3:
                    g.drawString("Upgrade to a Level 4 colony (-30 credits, -40 stone/brick)", 250, 245);
                    g.drawString("+25 population/min, +40 gold/min, -1 food/25 people/min", 250, 271);
                    break;
                case 4:
                    g.drawString("Upgrade to a Level 5 colony (-75 credits, -100 stone/brick)", 250, 245);
                    g.drawString("+60 population/min, +100 gold/min, -1 food/40 people/min", 250, 271);
                    break;
                default: 
                    g.setFont(g.getFont().deriveFont(16.0f));
                    g.drawString("This colony has reached its maximum possible level.", 250, 256);
                    break;
            }
        } else if (usage == 2) {
            g.setFont(g.getFont().deriveFont(24.0f));
            switch (type) {
                case 0:
                    g.drawString("This tile is being used to farm crops.", 200, 100);
                    g.drawString("+5 food/min, -2 credits/min", 250, 130);
                    break;
                case 1:
                    g.drawString("There is a water mill on this tile.", 230, 100);
                    g.drawString("+5 power/min, -3 credits/min", 245, 130);
                    break;
                case 2:
                    g.drawString("There is a quarry on this tile.", 250, 100);
                    g.drawString("+5 stone/min, -3 food/min", 263, 130);
                    break;
                case 3:
                    g.drawString("There is a lumber mill on this tile.", 230, 100);
                    g.drawString("+5 wood/min, -2 food/min", 250, 130);
                    break;
                case 4:
                    g.drawString("This tile is being used to mine clay for bricks.", 170, 100);
                    g.drawString("+5 bricks/min, -3 food/min", 260, 130);
                    break;
                case 5:
                    g.drawString("This tile is being used to mine gold.", 220, 100);
                    g.drawString("+5 credits/min, -3 food/min", 260, 130);
                    break;
            }
        } else {
            g.setFont(g.getFont().deriveFont(24.0f));
            switch (type) {
                case 0:
                    g.drawString("This tile is being used to farm livestock.", 195, 100);
                    g.drawString("+8 food/min, -5 credits/min", 258, 130);
                    break;
                case 1:
                    g.drawString("There is an underwater mine on this tile.", 185, 100);
                    g.drawString("+5 credits/min, -5 food/min", 250, 130);
                    break;
                case 2:
                    g.drawString("There is a nuclear testing site on this tile.", 185, 100);
                    g.drawString("Unlocks Class II weaponry, -10 food/min", 187, 130);
                    break;
                case 3:
                    g.drawString("This tile is being used to make paper for books.", 150, 100);
                    g.drawString("Unlocks Diplomacy, -2 credits/min", 215, 130);
                    break;
                case 4:
                    g.drawString("There is an antimatter testing site on this tile.", 155, 100);
                    g.drawString("Unlocks Class III weaponry, -20 food/min", 175, 130);
                    break;
                case 5:
                    g.drawString("There is a trade hub on this tile.", 230, 100);
                    g.drawString("Unlocks Economics, -3 food/min", 226, 130);
                    break;
            }
        }
        for (int i = 0; i < 3; i++) {
            if (MouseInfo.getPointerInfo().getLocation().x - 13 >= 100 && MouseInfo.getPointerInfo().getLocation().x - 13 <= 600 && MouseInfo.getPointerInfo().getLocation().y - 35 <= i*200 + 300 && MouseInfo.getPointerInfo().getLocation().y - 35 >= i*200 + 200) {
                g.setColor(Color.WHITE);
                int[] xPoints = {100, 150, 100};
                int[] yPoints = {i*200 + 200, i*200+250, i*200 + 300};
                g.drawPolyline(xPoints,yPoints,3);
            }
        }
    }
}
