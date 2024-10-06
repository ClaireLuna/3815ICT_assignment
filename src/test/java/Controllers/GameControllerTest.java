package Controllers;

import Models.GameModel;
import Models.Mp3Player;
import Models.Tetronimo;
import Views.PlayField;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

  private final GameModel mockGameModel = new GameModel(10, 10, 10, 1, true, true);
  private Mp3Player bgMusicPlayer;

  public GameControllerTest() {
    try {
      this.bgMusicPlayer = new Mp3Player(Objects.requireNonNull(getClass().getClassLoader().getResource("background.mp3")).openStream());
    }
    catch (Exception e) {
      fail("Failed to load mp3", e);
    }
  }

  @Test
  void startGame() {
    GameController gameController = new GameController(mockGameModel, new PlayField(mockGameModel), new JPanel(), new JLabel(), new JLabel(), new JLabel(), bgMusicPlayer);
    assertNotNull(gameController);
    gameController.startGame();
    assertNotNull(mockGameModel.getTetronimo());
    assertEquals(Tetronimo.class, mockGameModel.getTetronimo().getClass());
  }

  @Test
  void pauseGame() {
    GameController gameController = new GameController(mockGameModel, new PlayField(mockGameModel), new JPanel(), new JLabel(), new JLabel(), new JLabel(), bgMusicPlayer);
    assertNotNull(gameController);
    gameController.startGame();
    assertFalse(mockGameModel.isPaused);
    gameController.pauseGame();
    assertTrue(mockGameModel.isPaused);
    gameController.pauseGame();
    assertFalse(mockGameModel.isPaused);
  }

  @Test
  void endGame() {
    GameController gameController = new GameController(mockGameModel, new PlayField(mockGameModel), new JPanel(), new JLabel(), new JLabel(), new JLabel(), bgMusicPlayer);
    gameController.startGame();
    assertNotNull(gameController);
    assertFalse(mockGameModel.isGameEnded);
    assertTrue(gameController.timer.isRunning());
    mockGameModel.isGameEnded = true;
    gameController.actionPerformed(null);
    assertFalse(gameController.timer.isRunning());
  }
}