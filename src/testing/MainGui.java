package testing;

import entity.Entity;
import map.Map;
import map.Position;
import ui.gui.GameWindow;

import javax.swing.*;

public class MainGui {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            Map map = new Map(5, 5);
            Entity player = new Entity("Player", '@', new Position(2, 2), true);

            map.placeEntity(player, 2, 2);
            new GameWindow(map,player);
        });
    }
}
