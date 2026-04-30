package entity;

import entity.base.Entity;
import overworld.map.Position;

public class Door extends Entity {
    public Door(String name, char sprite, Position position,boolean collision){

        super(name,sprite,position,collision);
    }

}
