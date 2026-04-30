package combat;

import entity.base.Entity;
import entity.specialised.CombatEntity;

public class Combat {
    CombatEntity player;
    CombatEntity enemy;
    boolean playerTurn = true;
    public Combat(CombatEntity player, CombatEntity enemy){
        this.player = player;
        this.enemy = enemy;
    }
    public void playerAttack(CombatEntity target){
        target.takeDamage(2);
        playerTurn = false;
    }
    public void update(){
        if(!playerTurn){
            player.takeDamage(1);
            playerTurn = true;
            System.out.println("enemy attacks!");
        }
    }
    public boolean isCombatOver(){
        return !player.isAlive() || !enemy.isAlive();
    }
    public CombatEntity getEnemy(){
        return enemy;
    }
    public CombatEntity getPlayer(){
        return player;
    }
}
