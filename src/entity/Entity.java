package entity;

import map.Position;

public class Entity {
    private final String name;
    private char sprite;
    private final Position position;
    private final boolean canCollide;
    public Entity(String name,char sprite,Position position,boolean canCollide){
        this.name = name;
        this.position = position;
        this.sprite = sprite;
        this.canCollide = canCollide;

    }
    public String getName(){ return this.name; }
    public char getSprite(){
        return this.sprite;
    }
    public Position getPosition(){
        return this.position;
    }

    public boolean canCollide(){
        return this.canCollide;
    }

    public String toString(){
        return "Entity(" +
                "name= '" + this.name + "\'" +
                ", sprite=" + this.sprite +
                ", can collide=" + this.canCollide +
                ")";
    }

}
