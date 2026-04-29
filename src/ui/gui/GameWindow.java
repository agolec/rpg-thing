package ui.gui;
import entity.Enemy;
import entity.Entity;
import entity.PlayerCharacter;
import map.Map;

import javax.swing.*;
import java.util.List;


public class GameWindow {

    public GameWindow(Map map, PlayerCharacter player ,List<Entity> entities) {
        JFrame frame = new JFrame("RPG");
        GamePanel panel = new GamePanel(map, player, entities);

        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}