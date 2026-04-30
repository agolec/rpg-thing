package entity.movement;

import entity.base.Entity;
import overworld.map.Map;
import overworld.movement.Direction;

import java.util.Random;

public class RandomMovement implements MovementBehavior {
    private Random random = new Random();
    private long lastMoveTime = 0;
    private final int MOVE_DELAY = 500; //milliseconds. Adjust this.
    @Override
    public void move(Entity entity, Map map) {
        long now = System.currentTimeMillis();

        if(now - lastMoveTime < MOVE_DELAY){
            return;
        }

        lastMoveTime = now;
        Direction[] directions = Direction.values();
        Direction direction = directions[random.nextInt(directions.length)];

        map.moveEntity(entity,direction);
    }
}
