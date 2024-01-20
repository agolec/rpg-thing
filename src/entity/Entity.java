package entity;

import java.util.Arrays;

public abstract class Entity {
    private String name;
    private int movementSpeed;
    private char sprite;
    int[] position;
    private boolean canCollide;
    Entity(String name,int movementSpeed,char sprite,int xPosition,int yPosition,boolean canCollide){
        this.name = name;
        this.movementSpeed = movementSpeed;
        this.sprite = sprite;
        this.position = new int[2];
        setPosition(xPosition,yPosition);
        this.canCollide = canCollide;

    }
    public char getEntitySprite(){
        return this.sprite;
    }
    public int[] getPosition(){
        return Arrays.copyOf(position,position.length);
    }
    public boolean hasSamePosition(Entity otherEntity){
        return (this.getEntityColumnPosition() == otherEntity.getEntityColumnPosition() &&
                this.getEntityRowPosition() == otherEntity.getEntityRowPosition());
    }
    public boolean entityHasDesiredPositionOfAnotherEntity(int desiredColumnPosition,int desiredRowPosition){
        return (this.getEntityColumnPosition() == desiredColumnPosition &&
                this.getEntityRowPosition() == desiredRowPosition);
    }
    public int getEntityColumnPosition(){
        return this.position[0];
    }
    public int getEntityRowPosition(){
        return this.position[1];
    }
    public void setPosition(int columnPosition,int rowPosition){
        this.position[0] = columnPosition;
        this.position[1] = rowPosition;
    }
    public boolean canEntityCollide(){
        return this.canCollide;
    }
    public String toString(){
        return "Entity: " + this.name + "\n" +
                "Row: " + this.getEntityColumnPosition() +  "\n" +
                "Column: " + this.getEntityRowPosition() + "\n"
                + "Sprite: " + this.getEntitySprite();
    }

}
