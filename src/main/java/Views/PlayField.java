// src/Views/PlayField.java
package Views;

import Models.GameModel;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;

public class PlayField extends JPanel {
  private final GameModel gameModel;
  private final int BLOCK_SIZE;
  private final JLabel pauseText = new JLabel("Game Paused");

  public PlayField(GameModel gameModel) {
    this.gameModel = gameModel;
    this.BLOCK_SIZE = gameModel.getBlockSize();
    setFocusable(true);
    setBackground(Color.LIGHT_GRAY);
    this.add(pauseText);
    pauseText.setVisible(false);
    setPreferredSize(new Dimension(gameModel.getBoardWidth() * BLOCK_SIZE, gameModel.getBoardHeight() * BLOCK_SIZE));
    setLayout(new GridBagLayout());
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);
    Color[][] board = gameModel.getBoard();
    for (int i = 0; i < gameModel.getBoardHeight(); i++) {
      for (int j = 0; j < gameModel.getBoardWidth(); j++) {
        if (board[i][j] != null) {
          drawBlock(g, j * BLOCK_SIZE, i * BLOCK_SIZE, board[i][j]);
        }
      }
    }
    drawTetronimo(g);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.GRAY);
    for (int x = 1; x < gameModel.getBoardWidth(); x++) {
      g.drawLine(x * BLOCK_SIZE, 0, x * BLOCK_SIZE, gameModel.getBoardHeight() * BLOCK_SIZE);
    }
    for (int y = 1; y < gameModel.getBoardHeight(); y++) {
      g.drawLine(0, y * BLOCK_SIZE, gameModel.getBoardWidth() * BLOCK_SIZE, y * BLOCK_SIZE);
    }
  }

  private void drawBlock(Graphics g, int x, int y, Color color) {
    g.setColor(color);
    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
  }

  private void drawTetronimo(Graphics g) {
    for (Point2D blockPosition : gameModel.getTetronimo().getBlockPositions()) {
      g.setColor(gameModel.getTetronimo().getColor());
      g.fillRect(((int) blockPosition.getX() + gameModel.getCurrentX()) * BLOCK_SIZE, ((int) blockPosition.getY() * BLOCK_SIZE) + gameModel.getYPosition(), BLOCK_SIZE, BLOCK_SIZE);
      g.setColor(Color.BLACK);
      g.drawRect(((int) blockPosition.getX() + gameModel.getCurrentX()) * BLOCK_SIZE, ((int) blockPosition.getY() * BLOCK_SIZE) + gameModel.getYPosition(), BLOCK_SIZE, BLOCK_SIZE);
    }
  }

  public void showPauseText(boolean show) {
    pauseText.setVisible(show);
  }
}