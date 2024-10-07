package Controllers;

import Models.GameModel;
import Models.Mp3Player;
import Models.Tetronimos.TetronimoFactory;
import Views.PlayField;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class GameControllerTest {

  private GameModel mockGameModel;
  private GameController gameController;
  private Mp3Player bgMusicPlayer;

  @BeforeEach
  void setUp() {
    try {
      this.bgMusicPlayer =
          new Mp3Player(Objects.requireNonNull(getClass().getClassLoader()
              .getResource("background.mp3")).openStream());
    } catch (Exception e) {
      fail("Failed to load mp3", e);
    }

    mockGameModel = new
        GameModel(10, 10, 10, 1, true, true);
    gameController = new
        GameController(mockGameModel, new PlayField(mockGameModel),
        new JPanel(), new JLabel(), new JLabel(), new JLabel(), bgMusicPlayer);
  }

  @Test
  void startGame() {
    assertNotNull(gameController);
    gameController.startGame();
    assertNotNull(mockGameModel.getTetronimo());
    assertEquals(TetronimoFactory.class, mockGameModel.getTetronimo().getClass());
  }

  @Test
  void pauseGame() {
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
    gameController.startGame();
    assertNotNull(gameController);
    assertFalse(mockGameModel.isGameEnded);
    assertTrue(gameController.timer.isRunning());
    mockGameModel.isGameEnded = true;
    gameController.actionPerformed(null);
    assertFalse(gameController.timer.isRunning());
  }
}