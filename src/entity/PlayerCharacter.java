package entity;

public class PlayerCharacter extends Entity {
    public PlayerCharacter(String entityName, int movementSpeed, char spriteChar, int columnPosition, int rowPosition, boolean canCollide){
        super(entityName,movementSpeed,spriteChar,columnPosition,rowPosition,canCollide);
    }

}
