package entity;

import entity.movement.MovementBehavior;
import map.Map;
import map.Position;

public class Entity {
    private final String name;
    private char sprite;
    private final Position position;
    private final boolean canCollide;
    private MovementBehavior movementBehavior;
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
    public void onEnter(Entity entity){
        //default: do nothing
    };

    public void setMovementBehavior(MovementBehavior behavior){
        this.movementBehavior = behavior;
    }
    public void updateMovement(Map map){
        if(movementBehavior != null){
            this.movementBehavior.move(this,map);
        }
    }

    public String toString(){
        return "Entity(" +
                "name= '" + this.name + "\'" +
                ", sprite=" + this.sprite +
                ", can collide=" + this.canCollide +
                ")";
    }

}
