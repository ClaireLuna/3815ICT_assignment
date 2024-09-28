// src/Controllers/GameController.java
package Controllers;

import Models.GameModel;
import Views.PlayField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController implements ActionListener {
    private final GameModel gameModel;
    private final PlayField playField;
    private final Timer timer;

    public GameController(GameModel gameModel, PlayField playField) {
        this.gameModel = gameModel;
        this.playField = playField;
        this.timer = new Timer(5, this);
        playField.addKeyListener(new TAdapter());
    }

    public void startGame() {
        if (gameModel.isPaused) return;
        gameModel.clearBoard();
        gameModel.newPiece();
        timer.start();
        playField.requestFocusInWindow();
    }

    public void pauseGame() {
        gameModel.isPaused = !gameModel.isPaused;
        if (gameModel.isPaused) {
            if (!gameModel.isGameEnded) {
                playField.showPauseText(true);
                playField.requestFocusInWindow();
            }
            timer.stop();
        } else if (!gameModel.isGameEnded) {
            timer.start();
            playField.showPauseText(false);
            playField.requestFocusInWindow();
        }
        playField.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        gameModel.drop();
        if (!gameModel.isGameEnded) {
            playField.repaint();
        }
    }

    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            if (keycode == KeyEvent.VK_P) {
                pauseGame();
                return;
            }
            if (!gameModel.isPaused) {
                switch (keycode) {
                    case KeyEvent.VK_LEFT:
                        if (gameModel.canMove(gameModel.getTetronimo(), gameModel.getCurrentX() - 1, gameModel.getCurrentY())) {
                            gameModel.setCurrentX(gameModel.getCurrentX() - 1);
                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (gameModel.canMove(gameModel.getTetronimo(), gameModel.getCurrentX() + 1, gameModel.getCurrentY())) {
                            gameModel.setCurrentX(gameModel.getCurrentX() + 1);
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        gameModel.drop();
                        break;
                    case KeyEvent.VK_UP:
                        gameModel.tryRotate();
                        break;
                }
            }
        }
    }
}