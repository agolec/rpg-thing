package ui.gui;

import combat.Combat;
import entity.base.Entity;
import entity.PlayerCharacter;
import entity.movement.PlayerMovement;
import entity.specialised.CombatEntity;
import overworld.map.Map;
import overworld.movement.Direction;
import overworld.movement.MoveResult;
import util.Debug;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private enum GameState {
        OVERWORLD,
        COMBAT
    }
    private GameState gameState;
    private Combat combat;
    private Map map;

    private long lastMoveTime = 0;
    private final int MOVE_DELAY = 150;

    private boolean up,down,left,right;
    private PlayerMovement playerMovement;
    private PlayerCharacter player;
    private List<Entity> entities;
    public GamePanel(Map map, PlayerCharacter player, List<Entity> entities){
        this.map = map;

        this.entities = entities;
        this.player = player;
        this.playerMovement = new PlayerMovement();
        this.player.setMovementBehavior(playerMovement);
        this.gameState = GameState.OVERWORLD;



        setFont(new java.awt.Font("Segoe UI Emoji", Font.PLAIN, 36));
        setFocusable(true);
        setupKeyInput();
        startGameLoop();
    }

    private void setupKeyInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(gameState == GameState.OVERWORLD){
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_W -> up = true;
                        case KeyEvent.VK_S -> down = true;
                        case KeyEvent.VK_A -> left = true;
                        case KeyEvent.VK_D -> right = true;
                    }
                } else if(gameState == GameState.COMBAT){
                    if(e.getKeyCode() == KeyEvent.VK_SPACE){
                        combat.playerAttack(combat.getEnemy());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> up = false;
                    case KeyEvent.VK_S -> down = false;
                    case KeyEvent.VK_A -> left = false;
                    case KeyEvent.VK_D -> right = false;
                }
            }
        });
    }
    private void startGameLoop() {
        int delay = 16; // ~60 FPS (1000ms / 60 ≈ 16)

        new Timer(delay, e -> {
            updateGame();
            repaint();
        }).start();
    }
    private void updateGame() {
        long now = System.currentTimeMillis();

        if (gameState == GameState.OVERWORLD) {

            if (now - lastMoveTime < MOVE_DELAY) return;

            Direction direction = null;

            if (up) direction = Direction.UP;
            else if (down) direction = Direction.DOWN;
            else if (left) direction = Direction.LEFT;
            else if (right) direction = Direction.RIGHT;

            if (direction != null) {
                MoveResult result = map.moveEntity(player, direction);
                handleMoveResult(result);
                lastMoveTime = now;
            }

            for (Entity e : entities) {
                e.updateMovement(map);
            }

        }
        else if (gameState == GameState.COMBAT) {

            combat.update(); // 👈 THIS WAS MISSING

            if (combat.isCombatOver()) {
                handleCombatEnd();
            }
        }
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int tileSize = 50;

        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getColumns(); col++) {

                char tile = map.getTile(row, col);

                String symbol = switch(tile){
                    case '.' -> "\uD83D\uDFEB";
                    case '@' -> "\uD83D\uDE42";
                    case 'O' -> "\uD83D\uDC79";
                    case 'G' -> "\uD83D\uDC3B";
                    case '│', '─', '┌', '┐', '└', '┘' -> "🧱";
                    default -> String.valueOf(tile);
                };

                g.drawString(symbol, col * tileSize, (row + 1) * tileSize);
            }
        }
    }
    private void handleMoveResult(MoveResult result){
        if(!result.didMove()){
            Entity encountered = result.getEncountered();

            if(encountered instanceof CombatEntity ce){
                startCombat(this.player, ce);
            }
        }
    }
    private void startCombat(CombatEntity player,CombatEntity enemy){
        this.combat = new Combat(player, enemy);
        this.gameState = GameState.COMBAT;
        System.out.println("combat started with " + enemy.getName());
    }
    private void handleCombatEnd(){
        Entity enemy = combat.getEnemy();
        if(!combat.getEnemy().isAlive()) {
            map.removeEntity(combat.getEnemy());
            entities.remove(enemy);
            System.out.println("Enemy defeated.");
        }
        if(!combat.getPlayer().isAlive()){
            System.out.println("Game over");
            System.exit(0);
        }
        combat = null;
        gameState = GameState.OVERWORLD;
    }
}
