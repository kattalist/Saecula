/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;

/**
 *
 * @author 073787251
 */
public class ArmyPanel extends javax.swing.JPanel {

    public static Army playerArmy = new Army("The Intergalactic Army", 0);
    
    //ALL SHIPS IN THE GAME
    Ship fighter1 = new Ship("Class I Fighter", 25, 30, 50, 3);
    Ship fighter2 = new Ship("Class II Fighter", 50, 60, 150, 6);
    Ship fighter3 = new Ship("Class III Fighter", 80, 100, 300, 10);
    
    Ship cruiser1 = new Ship("Class I Cruiser", 100, 15, 150, 10);
    Ship cruiser2 = new Ship("Class II Cruiser", 250, 30, 400, 25);
    Ship cruiser3 = new Ship("Class III Cruiser", 400, 75, 1000, 50);
    
    Ship behemoth2 = new Ship("Behemoth", 500, 150, 750, 50);
    Ship behemoth3 = new Ship("Behemoth 2.0", 800, 400, 2000, 75);
    
    Ship nuclear = new Ship("BND-500", 300, 200, 850, 30);
    Ship antimatter = new Ship("CHAD-9000", 999, 999, 5000, 150);
    //CHAD stands for Carbon-Hydrogen Antimatter Device

    Ship[] store = {fighter1,cruiser1,fighter2,cruiser2,behemoth2,nuclear,fighter3,cruiser3,behemoth3,antimatter};
    /**
     * Creates new form ArmyPanel
     */
    public ArmyPanel() {
        initComponents();
        setBackground(Color.black);
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (MouseInfo.getPointerInfo().getLocation().x - 13 < 200 && MouseInfo.getPointerInfo().getLocation().x - 13 > 25 && MouseInfo.getPointerInfo().getLocation().y - 35 < 625 && MouseInfo.getPointerInfo().getLocation().y - 35 > 350) {
                    System.out.println("Oh my");
                    if (MainFrame.gold >= Ship.selectedShip.cost && MainFrame.pop >= Ship.selectedShip.crew) {
                        System.out.println("Howdy");
                        playerArmy.size ++;
                        MainFrame.gold -= Ship.selectedShip.cost;
                    }
                }
            }
        });
    }

    public void anim() {
        Timer t1 = new Timer(17, new TimerListener());
        t1.start();
    }
    public void drawScreen(Graphics g) {
        g.setColor(Color.WHITE);
        g.setFont(g.getFont().deriveFont(34.0f));
        g.drawString("MILITARY HQ", 290, 100);
        g.setFont(g.getFont().deriveFont(18.0f));
        g.drawString(playerArmy.name, 25, 200);
        g.drawString("Size: "+playerArmy.size, 25, 225);
        g.drawString("Strength: "+playerArmy.strength(), 25, 250);
        g.drawString("Defense: "+playerArmy.defense(), 25, 275);
        g.drawString("BUY SHIPS", 25, 325);
        for (int i = 0; i < 2; i++) {
            g.setFont(g.getFont().deriveFont(12.0f));
            g.drawString(store[i].name, 25, i*25 + 375);
            checkMouseInteraction(i, g);
        }
        if (MainFrame.classTwo) {
            for (int i = 2; i < 6; i++) {
                g.setFont(g.getFont().deriveFont(12.0f));
                g.drawString(store[i].name, 25, i*25 + 375);
                checkMouseInteraction(i, g);
            }
        }
        if (MainFrame.classThree) {
            for (int i = 6; i < store.length; i++) {
                g.setFont(g.getFont().deriveFont(12.0f));
                g.drawString(store[i].name, 25, i*25 + 375);
                checkMouseInteraction(i, g);
            }
        }
        
    }

    public void checkMouseInteraction (int index, Graphics g) {
            if (MouseInfo.getPointerInfo().getLocation().x - 13 < 200 && MouseInfo.getPointerInfo().getLocation().x - 13 > 25 && MouseInfo.getPointerInfo().getLocation().y - 35 < index * 25 + 375 && MouseInfo.getPointerInfo().getLocation().y - 35 > index * 25 + 350) {
                g.setColor(Color.WHITE);
                g.fillOval(5, index * 25 + 365, 10, 10);
                store[index].drawDescription(g);
            }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawScreen(g);
    }

    private class TimerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            repaint();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
