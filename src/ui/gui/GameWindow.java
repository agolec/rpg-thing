package ui.gui;
import entity.Entity;
import map.Map;

import javax.swing.*;



public class GameWindow {

    public GameWindow(Map map, Entity player) {
        JFrame frame = new JFrame("RPG");
        GamePanel panel = new GamePanel(map, player);

        frame.add(panel);
        frame.setSize(400, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}