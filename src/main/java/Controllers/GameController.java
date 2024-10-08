package Controllers;

import Models.GameModel;
import Models.Mp3Player;
import Views.PlayField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class GameController implements ActionListener {
  public final PlayField playField;
  public final Timer timer;
  private final GameModel gameModel;
  private final JPanel infoPanel;
  private final JLabel scoreLabel;
  private final JLabel levelLabel;
  private final JLabel linesClearedLabel;
  private final Mp3Player bgMusicPlayer;
  private final Mp3Player moveOrTurnPlayer;

  public GameController(GameModel gameModel, PlayField playField, JPanel infoPanel, JLabel scoreLabel, JLabel levelLabel, JLabel linesClearedLabel, Mp3Player bgMusicPlayer) {
    this.gameModel = gameModel;
    this.playField = playField;
    this.infoPanel = infoPanel;
    this.scoreLabel = scoreLabel;
    this.levelLabel = levelLabel;
    this.bgMusicPlayer = bgMusicPlayer;
    this.linesClearedLabel = linesClearedLabel;
    this.timer = new Timer(20, this);
    playField.addKeyListener(new TAdapter());

    try {
      this.moveOrTurnPlayer = new Mp3Player(Objects.requireNonNull(getClass().getClassLoader().getResource("move-turn.mp3")).openStream());
    } catch (Exception e) {
      throw new RuntimeException("Failed to load mp3", e);
    }
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
    updateInfoPanel();
  }

  private void updateInfoPanel() {
    scoreLabel.setText("Score: " + gameModel.getScore());
    levelLabel.setText("Level: " + gameModel.getLevel());
    linesClearedLabel.setText("Lines Cleared: " + gameModel.getTotalLinesCleared());
    infoPanel.repaint();
  }

  private void playMoveOrTurn() {
    if (gameModel.isSoundOn) {
      if (moveOrTurnPlayer.isPlaying()) {
        moveOrTurnPlayer.stop();
      }
      moveOrTurnPlayer.seekToStart();
      moveOrTurnPlayer.play();
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    gameModel.drop();
    if (!gameModel.isGameEnded) {
      playField.repaint();
      updateInfoPanel();
    } else {
      timer.stop();
      playField.repaint();
      updateInfoPanel();
      bgMusicPlayer.stop();
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

      if (keycode == KeyEvent.VK_M) {
        if (gameModel.isMusicOn) {
          bgMusicPlayer.pause();
        } else {
          bgMusicPlayer.seekToStart();
          bgMusicPlayer.play();
        }
        gameModel.isMusicOn = !gameModel.isMusicOn;
      }

      if (keycode == KeyEvent.VK_S) {
        gameModel.isSoundOn = !gameModel.isSoundOn;
      }

      if (!gameModel.isPaused) {
        switch (keycode) {
          case KeyEvent.VK_LEFT:
            if (gameModel.canMove(gameModel.getTetronimo(), gameModel.getCurrentX() - 1, gameModel.getCurrentY())) {
              gameModel.setCurrentX(gameModel.getCurrentX() - 1);
            }
            playMoveOrTurn();
            break;
          case KeyEvent.VK_RIGHT:
            if (gameModel.canMove(gameModel.getTetronimo(), gameModel.getCurrentX() + 1, gameModel.getCurrentY())) {
              gameModel.setCurrentX(gameModel.getCurrentX() + 1);
            }
            playMoveOrTurn();
            break;
          case KeyEvent.VK_DOWN:
            gameModel.drop();
            break;
          case KeyEvent.VK_UP:
            gameModel.tryRotate();
            playMoveOrTurn();
            break;
        }
      }
    }
  }
}