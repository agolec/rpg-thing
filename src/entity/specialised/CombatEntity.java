package entity.specialised;

import entity.base.Entity;
import overworld.map.Position;

public class CombatEntity extends Entity {
    private int health;
    private final int LOST_ALL_HEALTH = 0;
    public CombatEntity(String name, char sprite, Position position, boolean canCollide) {
        super(name, sprite, position, canCollide);
        this.setHealth(10);
    }
    public void takeDamage(int damage){
        this.health -= damage;
        System.out.println(this.getName() + " takes 1 damage");
        System.out.println(this.getName() + " at " + this.getHealth() + " health");
    }
    public boolean isAlive(){
        return this.health > this.LOST_ALL_HEALTH;
    }
    public void setHealth(int health){
        this.health = health;
    }
    public int getHealth(){
        return this.health;
    }
}
