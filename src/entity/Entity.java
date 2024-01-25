package entity;

import java.util.Arrays;

public abstract class Entity {
    private String name;
    private int movementSpeed;
    private char sprite;
    int[] position;
    private boolean canCollide;
    Entity(String name,int movementSpeed,char sprite,int columnPosition,int rowPosition,boolean canCollide){
        this.name = name;
        this.movementSpeed = movementSpeed;
        this.sprite = sprite;
        this.position = new int[2];
        setPosition(columnPosition,rowPosition);
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
    public void setColumnPosition(int columnPosition){
        this.position[0] = columnPosition;
    }
    public void setRowPosition(int rowPosition){
        this.position[1] = rowPosition;
    }
    public int getMovementSpeed(){
        return this.movementSpeed;
    }
    public boolean canEntityCollide(){
        return this.canCollide;
    }

    public String toString(){
        return "Entity(" +
                "name= '" + this.name + "\'" +
                ", movementSpeed= " + this.movementSpeed +
                ", sprite=" + this.sprite +
                ", position: " + Arrays.toString(this.getPosition())  +
                ", can collide=" + this.canCollide +
                ")";
    }

}
