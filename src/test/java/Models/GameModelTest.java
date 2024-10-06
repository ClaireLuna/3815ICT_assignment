package Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GameModelTest {
  private GameModel gameModel;

  @BeforeEach
  void setUp() {
    gameModel = new GameModel(10, 20, 30, 1, true, true);
  }

  @Test
  void testInitialGameState() {
    assertNotNull(gameModel.getBoard());
    assertEquals(20, gameModel.getBoard().length);
    assertEquals(10, gameModel.getBoard()[0].length);
    assertEquals(0, gameModel.getScore());
    assertEquals(1, gameModel.getLevel());
    assertFalse(gameModel.isGameEnded);
  }

  @Test
  void testClearBoard() {
    gameModel.clearBoard();
    for (int i = 0; i < gameModel.getBoard().length; i++) {
      for (int j = 0; j < gameModel.getBoard()[i].length; j++) {
        assertNull(gameModel.getBoard()[i][j]);
      }
    }
  }

  @Test
  void testNewPiece() {
    gameModel.newPiece();
    assertNotNull(gameModel.getTetronimo());
    assertTrue(gameModel.getCurrentX() >= 0 && gameModel.getCurrentX() < gameModel.getBoard()[0].length);
    assertTrue(gameModel.getCurrentY() >= 0 && gameModel.getCurrentY() < gameModel.getBoard().length);

    gameModel.isGameEnded = true;
    gameModel.newPiece();
  }

  @Test
  void testMovePiece() {
    gameModel.newPiece();
    int initialX = gameModel.getCurrentX();
    int initialY = gameModel.getCurrentY();
    gameModel.setCurrentX(initialX + 1);
    assertEquals(initialX + 1, gameModel.getCurrentX());
    assertEquals(initialY, gameModel.getCurrentY());
  }

  @Test
  void testCheckBoard() {
    gameModel.clearBoard();
    // Simulate a filled line
    Arrays.fill(gameModel.getBoard()[0], Color.RED);
    gameModel.checkBoard();
    for (int i = 0; i < gameModel.getBoard()[0].length; i++) {
      assertNull(gameModel.getBoard()[0][i]);
    }
  }

  @Test
  void testDropPiece() {
    gameModel.newPiece();
    int initialY = gameModel.getYPosition();
    gameModel.drop();
    assertTrue(gameModel.getYPosition() > initialY);
  }
}