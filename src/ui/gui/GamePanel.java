package ui.gui;

import game.Game;
import overworld.map.Map;
import overworld.movement.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GamePanel extends JPanel {

    private final Game game;

    public GamePanel(Game game) {

        this.game = game;

        setFont(new Font("Segoe UI Emoji", Font.PLAIN, 36));
        setFocusable(true);
        setupKeyInput();
        startGameLoop();
    }

    private void setupKeyInput() {
        addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {

                game.handleInput(e.getKeyCode());
            }
        });
    }

    private void startGameLoop() {
        int delay = 16;

        new Timer(delay, e -> {
            game.update();
            repaint();
        }).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Map map = game.getMap(); // 👈 ONLY access through Game
        int tileSize = 50;

        for (int row = 0; row < map.getRows(); row++) {
            for (int col = 0; col < map.getColumns(); col++) {

                char tile = map.getTile(row, col);

                String symbol = switch (tile) {
                    case '.' -> "\uD83D\uDFEB";
                    case '@' -> "\uD83D\uDE42";
                    case 'O' -> "\uD83D\uDC79";
                    case 'G' -> "\uD83D\uDC3B";
                    case '│', '─', '┌', '┐', '└', '┘' -> "⛰️";
                    default -> String.valueOf(tile);
                };

                g.drawString(symbol, col * tileSize, (row + 1) * tileSize);
            }
        }
    }
}