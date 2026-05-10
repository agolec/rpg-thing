package game;

import combat.Combat;
import entity.Door;
import entity.base.Entity;
import entity.specialised.CombatEntity;
import overworld.map.Map;
import overworld.movement.Direction;
import overworld.movement.MoveResult;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

public class Game {



    public enum GameState {
        OVERWORLD,
        COMBAT
    }

    private GameState gameState = GameState.OVERWORLD;

    private Map map;
    private final List<Entity> entities;
    private final CombatEntity player;

    private Combat combat;

    public Game(Map map, CombatEntity player, List<Entity> entities) {
        this.map = map;
        this.player = player;
        this.entities = entities;
    }

    public void update() {
        if (gameState == GameState.OVERWORLD) {
            for (Entity e : entities) {
                e.updateMovement(map);
            }
        } else if (gameState == GameState.COMBAT) {
            combat.update();

            if (combat.isCombatOver()) {
                handleCombatEnd();
            }
        }
    }

    public void handlePlayerMove(Direction direction) {

        if(gameState != GameState.OVERWORLD){
            return;
        }

        MoveResult result = map.moveEntity(player, direction);

        Entity interacted = result.getInteractedEntity();

        if(interacted instanceof Door door){
            loadMap(door.getDestination(),3,3);
        }

        if(!result.didMove()){

            Entity encountered = result.getEncountered();

            if(encountered instanceof CombatEntity ce){
                startCombat(player, ce);
            }
        }
    }
    private void loadMap(String mapId, int row, int col){

        Map newMap;

        switch(mapId){
            case "house" -> newMap = createHouseMap();
            case "overworld" -> newMap = createOverworldMap();
            default -> throw new IllegalArgumentException("Unknown map");
        }

        entities.clear();
        entities.add(player);

        this.map = newMap;

        map.placeEntity(player,row,col);
    }
    private Map createHouseMap(){
        List<Entity> entities = new ArrayList<>();
        entities.add(player);
        return new Map(4,4);
    }
    private Map createOverworldMap(){
        List<Entity> entities = new ArrayList<>();
        entities.add(player);
        return new Map(10,10);
    }

    private void startCombat(CombatEntity player, CombatEntity enemy) {
        this.combat = new Combat(player, enemy);
        this.gameState = GameState.COMBAT;
        System.out.println("Combat started with " + enemy.getName());
    }

    private void handleCombatEnd() {
        CombatEntity enemy = combat.getEnemy();

        if (!enemy.isAlive()) {
            map.removeEntity(enemy);
            entities.remove(enemy);
            System.out.println("Enemy defeated.");
        }

        if (!combat.getPlayer().isAlive()) {
            System.out.println("Game Over");
            System.exit(0);
        }

        combat = null;
        gameState = GameState.OVERWORLD;
    }
//    private void checkMapTransition(){
//        if(this.player == null){
//            return;
//        }
//        int row = this.player.getPosition().getRow();
//        int column = this.player.getPosition().getColumn();
//
//        if(map.){
//            System.out.println("sequence engaged to load next map...");
//            loadSecondMap();
//        }
//    }
    private void loadSecondMap(){
        Map secondmap = new Map(5,5);
        entities.clear();
        entities.add(player);
        this.map = secondmap;

        map.placeEntity(player,2,2);
        System.out.println("entered the second map.");
    }

    public GameState getGameState() {
        return gameState;
    }

    public Combat getCombat() {
        return combat;
    }

    public Map getMap() {
        return map;
    }
    public void setMap(Map map){
        this.map = map;
    }
    public void handleInput(int keyCode) {
        if (this.getGameState() == Game.GameState.OVERWORLD) {
            switch (keyCode) {
                case KeyEvent.VK_W -> this.handlePlayerMove(Direction.UP);
                case KeyEvent.VK_S -> this.handlePlayerMove(Direction.DOWN);
                case KeyEvent.VK_A -> this.handlePlayerMove(Direction.LEFT);
                case KeyEvent.VK_D -> this.handlePlayerMove(Direction.RIGHT);
            }
        }

        else if (this.getGameState() == Game.GameState.COMBAT) {
            if (keyCode == KeyEvent.VK_SPACE) {
                this.getCombat().playerAttack();
            }
        }
    }
}