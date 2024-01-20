package entity;

public class PlayerCharacter extends Entity {
    PlayerCharacter(String entityName,int movementSpeed,char spriteChar,int xPosition,int yPosition,boolean canCollide){
        super(entityName,movementSpeed,spriteChar,xPosition,yPosition,canCollide);
    }

}
