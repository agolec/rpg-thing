package entity;

import map.Position;
import movement.MovementComponent;

public class PlayerCharacter extends Entity {
    private final MovementComponent movement;
    public PlayerCharacter(String entityName, int movementSpeed, char spriteChar, Position initialPosition, boolean canCollide){
        super(entityName,spriteChar,initialPosition,canCollide);
        this.movement = new MovementComponent(movementSpeed);
    }

}
