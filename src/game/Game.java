package game;

import combat.Combat;
import entity.Door;
import entity.Enemy;
import entity.base.Entity;
import entity.movement.RandomMovement;
import entity.specialised.CombatEntity;
import overworld.map.Map;
import overworld.map.Position;
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

    private GameState gameState;

    private Map map;
    private final CombatEntity player;
    private Combat combat;
    private Map overworldMap;
    private Map houseMap;

    public Game(Map map, CombatEntity player) {

        this.player = player;
        this.gameState = GameState.OVERWORLD;
        this.overworldMap = map;
        this.houseMap = createHouseMap();
        this.map = overworldMap;
        this.map.placeEntity(player);
    }


    public void update() {
        if (gameState == GameState.OVERWORLD) {
            for (Entity e : map.getEntities()) {
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
            case "house" -> newMap = houseMap;
            case "overworld" -> newMap = overworldMap;
            default -> throw new IllegalArgumentException("Unknown map");
        }

        this.map.removeEntity(player);
        this.map = newMap;
        this.map.placeEntity(player);
    }
    private Map createHouseMap(){
        Map houseMap;
        List<Entity> entities = new ArrayList<>();
        Door exitDoor = new Door("door",'D',new Position(3,3),"overworld",new Position(2,2));

        //entities.add(player);
        entities.add(exitDoor);
        houseMap = new Map(4,4, entities);

        entities.forEach( entity -> houseMap.placeEntity(entity));
        return houseMap;
    }
    private Map createOverworldMap(){
        Map overworld = new Map(10, 10);

        Door houseDoor = new Door(
                "door",
                'D',
                new Position(1,1),
                "house",
                new Position(1,1));

        Enemy enemy = new Enemy(
                "Gnoll",
                'G',
                new Position(5,5),
                true);

        enemy.setMovementBehavior(new RandomMovement());

        overworld.placeEntity(enemy);

        overworld.placeEntity(houseDoor);

        return overworld;
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
//            map.removeEntity(enemy);
            System.out.println("Enemy defeated.");
        }

        if (!combat.getPlayer().isAlive()) {
            System.out.println("Game Over");
            System.exit(0);
        }

        combat = null;
        gameState = GameState.OVERWORLD;
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
                case KeyEvent.VK_ESCAPE -> System.exit(0);
            }
        }

        else if (this.getGameState() == Game.GameState.COMBAT) {
            if (keyCode == KeyEvent.VK_SPACE) {
                this.getCombat().playerAttack();
            }
        }
    }
}