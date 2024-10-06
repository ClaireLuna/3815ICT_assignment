// src/Models/GameModel.java
package Models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.File;
import java.util.Arrays;
import java.util.Objects;

public class GameModel {
    private final int BOARD_WIDTH;
    private final int BOARD_HEIGHT;
    private final int BLOCK_SIZE;
    private int LEVEL;
    private final int STARTING_LEVEL;
    private Color[][] board;
    private Tetronimo tetronimo;
    private int currentX = 0;
    private int currentY = 0;
    private int yPosition = 0;
    public boolean isPaused = false;
    public boolean isGameEnded = false;
    public boolean isSoundOn;
    public boolean isMusicOn;
    private final Mp3Player lineErasePlayer;
    private final Mp3Player levelUpPlayer;
    private final Mp3Player gameOverPlayer;
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
            File lineEraseFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/erase-line.wav")).toURI());
            this.lineErasePlayer = new Mp3Player(lineEraseFile);

            File levelUpFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/level-up.wav")).toURI());
            this.levelUpPlayer = new Mp3Player(levelUpFile);

            File gameOverFile = new File(Objects.requireNonNull(getClass().getClassLoader().getResource("resources/game-finish.wav")).toURI());
            this.gameOverPlayer = new Mp3Player(gameOverFile);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load mp3", e);
        }
    }

    public void clearBoard() {
        board = new Color[BOARD_HEIGHT][BOARD_WIDTH];
    }

    public void newPiece() {
        tetronimo = new Tetronimo();
        currentX = BOARD_WIDTH / 2;
        currentY = 0;
        yPosition = 0;

        for (Point2D blockPosition : tetronimo.blockPositions) {
            isGameEnded = (board[(int) (blockPosition.getY() + currentY)][(int) (blockPosition.getX() + currentX)] != null);
            if (isGameEnded) {
                playGameOver();
                break;
            }
            break;
        }
    }

    public boolean canMove(Tetronimo tetronimo, int x, int y) {
        for (Point2D blockPosition : tetronimo.blockPositions) {
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
        for (Point2D blockPosition : tetronimo.blockPositions) {
            if (((int) blockPosition.getY() + currentY) >= BOARD_HEIGHT - 1) {
                return false;
            }
        }
        return checkFallenBlocks();
    }

    private boolean checkFallenBlocks() {
        for (Point2D blockPosition : tetronimo.blockPositions) {
            if (board[(int) (blockPosition.getY() + currentY) + 1][(int) (blockPosition.getX() + currentX)] != null) {
                return false;
            }
        }
        return true;
    }

    private void checkBoard() {
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
        for (Point2D blockPosition : tetronimo.blockPositions) {
            board[(int) (blockPosition.getY() + currentY)][(int) (blockPosition.getX() + currentX)] = tetronimo.color;
        }
        checkBoard();
        newPiece();
    }

    public void tryRotate() {
        Tetronimo rotatedTetronimo = new Tetronimo(tetronimo);
        rotatedTetronimo.rotate();

        if (canMove(rotatedTetronimo, currentX, currentY)) {
            tetronimo = rotatedTetronimo;
        } else if (canMove(rotatedTetronimo, currentX + 1, currentY)) {
            currentX += 1;
            tetronimo = rotatedTetronimo;
        } else if (canMove(rotatedTetronimo, currentX - 1, currentY)) {
            currentX -= 1;
            tetronimo = rotatedTetronimo;
        }
    }

    public Color[][] getBoard() {
        return board;
    }

    public Tetronimo getTetronimo() {
        return tetronimo;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public int getYPosition() {
        return yPosition;
    }

    public void setCurrentX(int i) {
        currentX = i;
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