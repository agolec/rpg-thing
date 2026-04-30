package entity.movement;

import entity.base.Entity;
import overworld.map.Map;
import overworld.movement.Direction;

public class PlayerMovement implements MovementBehavior {
    private Direction currentDirection;

    public void setDirection(Direction direction){
        this.currentDirection = direction;
    }
    @Override
    public void move(Entity entity, Map map){
        if(currentDirection != null){
            map.moveEntity(entity,currentDirection);
            currentDirection = null; //prevent movement spam by player.
        }
    }
}
