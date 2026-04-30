package entity;

import entity.specialised.CombatEntity;
import overworld.map.Position;

public class PlayerCharacter extends CombatEntity {
    public PlayerCharacter(String entityName, int movementSpeed, char spriteChar, Position initialPosition, boolean canCollide){
        super(entityName,spriteChar,initialPosition,canCollide);
    }

}
