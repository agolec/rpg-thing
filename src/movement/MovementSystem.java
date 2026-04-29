package movement;

import entity.Entity;
import map.Map;
import map.Position;


public class MovementSystem {
    void move(Entity entity, Direction dir){

    }
    public boolean tryMove(Entity entity, Direction direction, Map map) {
        Position pos = entity.getPosition();
        int newCol = pos.getColumn();
        int newRow = pos.getRow();

        switch (direction) {
            case UP -> newRow--;
            case DOWN -> newRow++;
            case LEFT -> newCol--;
            case RIGHT -> newCol++;
        }

        if (map.canPlaceEntityOnMap(entity, newRow, newCol)) {
            pos.set(newCol, newRow);
            return true;
        }

        return false;
    }
}
