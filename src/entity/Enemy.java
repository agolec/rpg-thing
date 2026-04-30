package entity;

import entity.base.Entity;
import entity.specialised.CombatEntity;
import overworld.map.Position;

public class Enemy extends CombatEntity {
    public Enemy(String name, char sprite, Position position, boolean canCollide) {
        super(name, sprite, position, canCollide);
    }
    @Override
    public void onEnter(Entity movingEntity){
        System.out.println("You encountered a: " + this.getName());
    }


}
