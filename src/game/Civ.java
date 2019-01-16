/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author 073787251
 */
public class Civ {

    String name;
    StarSystem home;
    Army army;
    Color outlineColor;
    Planet activePlanet;

    public int food = 0;
    public int stone = 0;
    public int bricks = 0;
    public int power = 0;
    public int wood = 0;
    public int gold = 10;
    public int pop = 5;

    public boolean classTwo = false;
    public boolean classThree = false;
    public boolean diplomacy = false;
    public boolean economics = false;

    public int crops = 0, livestock = 0, mills = 0, underMines = 0, quarries = 0, nuclears = 0, lumbers = 0, papers = 0, clayMines = 0, antimatters = 0, goldMines = 0, hubs = 0, lvlOnes = 0, lvlTwos = 0, lvlThrees = 0, lvlFours = 0, lvlFives = 0;

    public int foodDiff = 0, stoneDiff = 0, brickDiff = 0, powerDiff = 0, woodDiff = 0, goldDiff = 0, popDiff = 0;
    
    public Civ(String name, StarSystem home, Army army, Color outlineColor) {
        this.name = name;
        this.home = home;
        this.army = army;
        this.outlineColor = outlineColor;
    }

    public void newPlanet() {
        Random r = new Random();
        ArrayList<Planet> options = new ArrayList<>();
        for (StarSystem s : MainFrame.universe) {
            if (s.owner == this) {
                options.add(s.system.get(r.nextInt(s.system.size())));
            }
        }
        activePlanet = options.get(r.nextInt(options.size()));
    }

    public void performAction() {
        //AI will develop in this order: farm --> lumber --> gold mine --> quarry --> mill --> colony
        //1 farm per lumber + mine
        if (crops <= 0 || crops < lumbers || crops < goldMines || crops < quarries || crops < clayMines || crops < underMines) {
            for (Tile t : activePlanet.planetBoard) {
                if (t.usage == 0 && t.type == 0 && gold >= 5) {
                    useTile(t, 2);
                }
            }
        } else if (lumbers <= 0) {
            for (Tile t : activePlanet.planetBoard) {
                if (t.usage == 0 && t.type == 3) {
                    useTile(t, 2);
                }
            }
        }
    }

    public void useTile(Tile t, int currentUse) {
        if (currentUse == 1) {
            if (t.usage == 1) {
                if (t.colonyLevel < 5) {
                    if (t.colonyLevel == 1 && gold >= 5 && (stone >= 5 || bricks >= 5)) {
                        lvlOnes--;
                        lvlTwos++;
                        t.colonyLevel++;
                        if (stone >= 5) {
                            stone -= 5;
                        } else {
                            bricks -= 5;
                        }
                    } else if (t.colonyLevel == 2 && gold >= 15 && (stone >= 20 || bricks >= 20)) {
                        lvlTwos--;
                        lvlThrees++;
                        t.colonyLevel++;
                        if (stone >= 20) {
                            stone -= 20;
                        } else {
                            bricks -= 20;
                        }
                    } else if (t.colonyLevel == 3 && gold >= 30 && (stone >= 40 || bricks >= 40)) {
                        lvlThrees--;
                        lvlFours++;
                        t.colonyLevel++;
                        if (stone >= 40) {
                            stone -= 40;
                        } else {
                            bricks -= 40;
                        }
                    } else if (t.colonyLevel == 4 && gold >= 75 && (stone >= 100 || bricks >= 100)) {
                        lvlFours--;
                        lvlFives++;
                        t.colonyLevel++;
                        if (stone >= 100) {
                            stone -= 100;
                        } else {
                            bricks -= 100;
                        }
                    }
                }
            } else if (gold >= 5) {
                t.usage = 1;
                t.colonyLevel = 1;
                gold -= 5;
                lvlOnes++;
            }
        } else if (currentUse == 2) {
            switch (t.type) {
                case 0:
                    if (gold >= 5) {
                        t.usage = 2;
                        gold -= 5;
                        crops++;
                    }
                    break;
                case 1:
                    if (stone >= 10) {
                        t.usage = 2;
                        stone -= 10;
                        mills++;
                    } else if (bricks >= 10) {
                        t.usage = 2;
                        bricks -= 10;
                        mills++;
                    }
                    break;
                case 2:
                    if (wood >= 3) {
                        t.usage = 2;
                        wood -= 3;
                        quarries++;
                    }
                    break;
                case 3:
                    t.usage = 2;
                    lumbers++;
                    break;
                case 4:
                    if (wood >= 3) {
                        t.usage = 2;
                        wood -= 3;
                        clayMines++;
                    }
                    break;
                case 5:
                    if (wood >= 2) {
                        t.usage = 2;
                        wood -= 2;
                        goldMines++;
                    }
                    break;
            }
        } else {
            switch (t.type) {
                case 0:
                    if (gold >= 5) {
                        t.usage = 3;
                        gold -= 5;
                        livestock++;
                    }
                    break;
                case 1:
                    if (stone >= 5) {
                        t.usage = 3;
                        stone -= 5;
                        underMines++;
                    } else if (bricks >= 5) {
                        t.usage = 3;
                        bricks -= 5;
                        underMines++;
                    }
                    break;
                case 2:
                    if (gold >= 5) {
                        t.usage = 3;
                        gold -= 5;
                        nuclears++;
                        classTwo = true;
                    }
                    break;
                case 3:
                    if (stone >= 5) {
                        t.usage = 3;
                        stone -= 5;
                        papers++;
                        diplomacy = true;
                    } else if (bricks >= 5) {
                        t.usage = 3;
                        bricks -= 5;
                        papers++;
                        diplomacy = true;
                    }
                    break;
                case 4:
                    if (gold >= 20) {
                        t.usage = 3;
                        gold -= 20;
                        antimatters++;
                        classThree = true;
                    }
                    break;
                case 5:
                    if (gold >= 3) {
                        t.usage = 3;
                        gold -= 3;
                        hubs++;
                        economics = true;
                    }
                    break;
            }
        }
    }
}
