package ui.gui;
import entity.base.Entity;
import entity.PlayerCharacter;
import game.Game;
import overworld.map.Map;

import javax.swing.*;
import java.util.List;


public class GameWindow {

    public GameWindow(Map map, PlayerCharacter player ,List<Entity> entities) {
        JFrame frame = new JFrame("RPG");
        Game game = new Game(map,player,entities);
        GamePanel panel = new GamePanel(game);

        frame.add(panel);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel.requestFocusInWindow();
    }
}