package ui.gui;

import entity.Entity;
import map.Map;
import movement.Direction;
import util.Debug;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {
    private Map map;
    private Entity player;
    private long lastMoveTime = 0;
    private final int MOVE_DELAY = 150;

    private boolean up,down,left,right;
    public GamePanel(Map map, Entity player){
        this.map = map;
        this.player = player;

        setFont(new java.awt.Font("Segoe UI Emoji", Font.PLAIN, 36));
        setFocusable(true);
        setupKeyInput();
        startGameLoop();
    }

    private void setupKeyInput() {
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_W -> up = true;
                    case KeyEvent.VK_S -> down = true;
                    case KeyEvent.VK_A -> left = true;
                    case KeyEvent.VK_D -> right = true;
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

        if (now - lastMoveTime < MOVE_DELAY) return;

        if (up) {
            map.moveEntity(player, Direction.UP);
            lastMoveTime = now;
        }
        else if (down) {
            map.moveEntity(player, Direction.DOWN);
            lastMoveTime = now;
        }
        else if (left) {
            map.moveEntity(player, Direction.LEFT);
            lastMoveTime = now;
        }
        else if (right) {
            map.moveEntity(player, Direction.RIGHT);
            lastMoveTime = now;
        }
        if(Debug.ENABLED){
            if(Debug.MOVEMENT && Debug.ENTITY){
                map.debugPrintEntityLocations();
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
                    case '│', '─', '┌', '┐', '└', '┘' -> "🧱";
                    default -> String.valueOf(tile);
                };

                g.drawString(symbol, col * tileSize, (row + 1) * tileSize);
            }
        }
    }
}
