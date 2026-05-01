package game;

import combat.Combat;
import entity.base.Entity;
import entity.specialised.CombatEntity;
import overworld.map.Map;
import overworld.movement.Direction;
import overworld.movement.MoveResult;

import java.awt.event.KeyEvent;
import java.util.List;

public class Game {



    public enum GameState {
        OVERWORLD,
        COMBAT
    }

    private GameState gameState = GameState.OVERWORLD;

    private final Map map;
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
        if (gameState != GameState.OVERWORLD) return;

        MoveResult result = map.moveEntity(player, direction);

        if (!result.didMove()) {
            Entity encountered = result.getEncountered();

            if (encountered instanceof CombatEntity ce) {
                startCombat(player, ce);
            }
        }
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

    public GameState getGameState() {
        return gameState;
    }

    public Combat getCombat() {
        return combat;
    }

    public Map getMap() {
        return map;
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