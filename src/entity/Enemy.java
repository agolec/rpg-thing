package entity;

import entity.movement.MovementBehavior;
import map.Map;
import map.Position;
import movement.Direction;

public class Enemy extends Entity {
    public Enemy(String name, char sprite, Position position, boolean canCollide) {
        super(name, sprite, position, canCollide);
    }
    @Override
    public void onEnter(Entity movingEntity){
        System.out.println("You encountered a: " + this.getName());
    }


}
