// src/Models/GameModel.java
package Models;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoFactory;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Arrays;
import java.util.Objects;

public class GameModel {
  private final int BOARD_WIDTH;
  private final int BOARD_HEIGHT;
  private final int BLOCK_SIZE;
  private final int STARTING_LEVEL;
  private final Mp3Player lineErasePlayer;
  private final Mp3Player levelUpPlayer;
  private final Mp3Player gameOverPlayer;
  public boolean isPaused = false;
  public boolean isGameEnded = false;
  public boolean isSoundOn;
  public boolean isMusicOn;
  private int LEVEL;
  private Color[][] board;
  private ITetronimo tetronimo;
  private int currentX = 0;
  private int currentY = 0;
  private int yPosition = 0;
  private int score = 0;
  private int totalLinesCleared = 0;

  public GameModel(int BOARD_WIDTH, int BOARD_HEIGHT, int BLOCK_SIZE, int LEVEL, boolean isMusicOn, boolean isSoundOn) {
    this.BOARD_WIDTH = BOARD_WIDTH;
    this.BOARD_HEIGHT = BOARD_HEIGHT;
    this.BLOCK_SIZE = BLOCK_SIZE;
    this.LEVEL = this.STARTING_LEVEL = LEVEL;
    this.isSoundOn = isSoundOn;
    this.isMusicOn = isMusicOn;
    board = new Color[BOARD_HEIGHT][BOARD_WIDTH];

    try {
      this.lineErasePlayer = new Mp3Player(Objects.requireNonNull(getClass().getClassLoader().getResource("erase-line.mp3")).openStream());

      this.levelUpPlayer = new Mp3Player(Objects.requireNonNull(getClass().getClassLoader().getResource("level-up.mp3")).openStream());

      this.gameOverPlayer = new Mp3Player(Objects.requireNonNull(getClass().getClassLoader().getResource("game-finish.mp3")).openStream());
    } catch (Exception e) {
      throw new RuntimeException("Failed to load mp3", e);
    }
  }

  public void clearBoard() {
    board = new Color[BOARD_HEIGHT][BOARD_WIDTH];
  }

  public void newPiece() {
    TetronimoFactory tetronimoFactory = TetronimoFactory.getRandomTetronimo();
    tetronimo = tetronimoFactory.createTetronimo();
    currentX = BOARD_WIDTH / 2;
    currentY = 0;
    yPosition = 0;

    for (Point2D blockPosition : tetronimo.getBlockPositions()) {
      isGameEnded = (board[(int) (blockPosition.getY() + currentY)][(int) (blockPosition.getX() + currentX)] != null);
      if (isGameEnded) {
        playGameOver();
        break;
      }
      break;
    }
  }

  public boolean canMove(ITetronimo tetronimo, int x, int y) {
    for (Point2D blockPosition : tetronimo.getBlockPositions()) {
      int newX = (int) (blockPosition.getX() + x);
      int newY = (int) (blockPosition.getY() + y);

      if (newX < 0 || newX >= BOARD_WIDTH || newY < 0 || newY >= BOARD_HEIGHT) {
        return false;
      }

      if (board[newY][newX] != null) {
        return false;
      }
    }
    return true;
  }

  public void drop() {
    yPosition += LEVEL;
    currentY = (yPosition / BLOCK_SIZE);

    if (!tryDrop()) {
      pieceDropped();
    }
  }

  private boolean tryDrop() {
    for (Point2D blockPosition : tetronimo.getBlockPositions()) {
      if (((int) blockPosition.getY() + currentY) >= BOARD_HEIGHT - 1) {
        return false;
      }
    }
    return checkFallenBlocks();
  }

  private boolean checkFallenBlocks() {
    for (Point2D blockPosition : tetronimo.getBlockPositions()) {
      if (board[(int) (blockPosition.getY() + currentY) + 1][(int) (blockPosition.getX() + currentX)] != null) {
        return false;
      }
    }
    return true;
  }

  void checkBoard() {
    int linesCleared = 0;
    for (int i = BOARD_HEIGHT - 1; i >= 0; i--) {
      boolean canClear = true;

      for (int j = 0; j < BOARD_WIDTH; j++) {
        if (board[i][j] == null) {
          canClear = false;
          break;
        }
      }

      if (canClear) {
        for (int y = i; y > 0; y--) {
          board[y] = Arrays.copyOf(board[y - 1], BOARD_WIDTH);
        }

        board[0] = new Color[BOARD_WIDTH];

        i++;
        linesCleared++;
      }
    }

    if (linesCleared > 0) {
      playLineClear();
    }

    switch (linesCleared) {
      case 1 -> score += 100;
      case 2 -> score += 300;
      case 3 -> score += 600;
      case 4 -> score += 1000;
    }

    totalLinesCleared += linesCleared;
    int newLevel = STARTING_LEVEL + (totalLinesCleared / 10);
    if (newLevel > LEVEL) {
      LEVEL = newLevel;
      playLevelUp();
    }
  }

  private void pieceDropped() {
    for (Point2D blockPosition : tetronimo.getBlockPositions()) {
      board[(int) (blockPosition.getY() + currentY)][(int) (blockPosition.getX() + currentX)] = tetronimo.getColor();
    }
    checkBoard();
    newPiece();
  }

  public void tryRotate() {
    tetronimo.rotate();

    if (canMove(tetronimo, currentX, currentY)) {
      return;
    } else if (canMove(tetronimo, currentX + 1, currentY)) {
      currentX += 1;
    } else if (canMove(tetronimo, currentX - 1, currentY)) {
      currentX -= 1;
    } else {
      tetronimo.rotate();
      tetronimo.rotate();
      tetronimo.rotate();
    }
  }

  public Color[][] getBoard() {
    return board;
  }

  public ITetronimo getTetronimo() {
    return tetronimo;
  }

  public int getCurrentX() {
    return currentX;
  }

  public void setCurrentX(int i) {
    currentX = i;
  }

  public int getCurrentY() {
    return currentY;
  }

  public int getYPosition() {
    return yPosition;
  }

  public int getBlockSize() {
    return BLOCK_SIZE;
  }

  public int getBoardWidth() {
    return BOARD_WIDTH;
  }

  public int getBoardHeight() {
    return BOARD_HEIGHT;
  }

  public int getScore() {
    return score;
  }

  public int getStartingLevel() {
    return STARTING_LEVEL;
  }

  public int getLevel() {
    return LEVEL;
  }

  public int getTotalLinesCleared() {
    return totalLinesCleared;
  }

  public void playLineClear() {
    if (isSoundOn) {
      if (lineErasePlayer.isPlaying()) {
        lineErasePlayer.stop();
      }
      lineErasePlayer.seekToStart();
      lineErasePlayer.play();
    }
  }

  public void playLevelUp() {
    if (isSoundOn) {
      if (levelUpPlayer.isPlaying()) {
        levelUpPlayer.stop();
      }
      levelUpPlayer.seekToStart();
      levelUpPlayer.play();
    }
  }

  private void playGameOver() {
    if (isSoundOn) {
      gameOverPlayer.play();
    }
  }
}