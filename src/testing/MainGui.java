package testing;

import entity.Enemy;
import entity.PlayerCharacter;
import entity.base.Entity;
import entity.movement.PlayerMovement;
import entity.movement.RandomMovement;
import overworld.map.Map;
import overworld.map.Position;
import ui.gui.GameWindow;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class MainGui {
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            final int SIZE = 5;
            final int MULTIPLIER = 2;
            List<Entity> entities = new ArrayList<>();
            Map map = new Map(SIZE * MULTIPLIER, SIZE * MULTIPLIER);
            PlayerCharacter player = new PlayerCharacter("Player",3,'@', new Position(2, 2), true);
            Enemy enemy = new Enemy("Gnoll",'G',new Position(5,5), true);
            enemy.setMovementBehavior(new RandomMovement());
            player.setMovementBehavior(new PlayerMovement());

            entities.add(enemy);

            map.placeEntity(player, 2, 2);
            map.placeEntity(enemy,enemy.getPosition().getRow(),enemy.getPosition().getColumn());
            new GameWindow(map,player, entities);
        });
    }
}
