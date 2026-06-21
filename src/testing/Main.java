package testing;

import entity.base.Entity;
import overworld.map.Map;
import overworld.map.Position;
import overworld.movement.Direction;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // 1. Create map
        List<Entity> lmao = new ArrayList<>();
        Map map = new Map(5, 5,lmao);

        // 2. Create entity at (row=2, col=2)
        Entity player = new Entity("Player", '@', new Position(2, 2), true);

        // 3. Place entity
        //map.placeEntity(player, 2, 2);

        // 4. Print initial state
        System.out.println("Initial Map:");
        map.printMap();

        // 5. Simulate movement manually
        testMove(map, player, Direction.UP);
        testMove(map, player, Direction.RIGHT);
        testMove(map, player, Direction.DOWN);
        testMove(map, player, Direction.LEFT);
    }

    private static void testMove(Map map, Entity player, Direction dir) {
        System.out.println("\nMoving: " + dir);

        // TEMP: directly call movement logic
        int newRow = player.getPosition().getRow();
        int newCol = player.getPosition().getColumn();

        switch (dir) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
        }

        if (map.canPlaceEntityOnMap(player, newRow, newCol)) {
            map.moveEntityToPosition(player, newRow, newCol);
        } else {
            System.out.println("Blocked!");
        }

        map.printMap();
    }
}