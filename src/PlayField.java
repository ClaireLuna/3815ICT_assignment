import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.Point2D;
import java.util.Random;

public class PlayField extends JPanel implements ActionListener {
  private final int BOARD_WIDTH;
  private final int BOARD_HEIGHT;
  private final int BLOCK_SIZE;
  private Color[][] board;
  private final Timer timer;
  private boolean isPaused = false;
  private int currentX = 0;
  private int currentY = 0;
  private int yPosition = 0;
  private Tetronimo tetronimo;
  private final Color[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE };

  public PlayField(int BOARD_WIDTH, int BOARD_HEIGHT, int BLOCK_SIZE) {
    this.BOARD_WIDTH = BOARD_WIDTH;
    this.BOARD_HEIGHT = BOARD_HEIGHT;
    this.BLOCK_SIZE = BLOCK_SIZE;
    board = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    timer = new Timer(5, this);
    addKeyListener(new TAdapter());

    setFocusable(true);
    setBackground(Color.LIGHT_GRAY);

    setPreferredSize(new Dimension(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE));
  }

  private void clearBoard() {
    board = new Color[BOARD_WIDTH][BOARD_HEIGHT];
  }

  public void start() {
    if (isPaused) return;
    clearBoard();
    newPiece();
    timer.start();
    requestFocusInWindow();
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    for (int i = 0; i < BOARD_WIDTH; i++) {
      for (int j = 0; j < BOARD_HEIGHT; j++) {
        if (board[i][j] != null) {
          drawBlock(g, i * BLOCK_SIZE, j * BLOCK_SIZE, board[i][j]);
        }
      }
    }

    drawTetronimo(g);
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    g.setColor(Color.GRAY);
    for (int x = 1; x < BOARD_WIDTH; x++) {
      g.drawLine(x * BLOCK_SIZE, 0, x * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);
    }
    for (int y = 1; y < BOARD_HEIGHT; y++) {
      g.drawLine(0, y * BLOCK_SIZE, BOARD_WIDTH * BLOCK_SIZE, y * BLOCK_SIZE);
    }
  }

  private void drop() {
    int LEVEL = 1;
    yPosition += LEVEL;
    currentY = (yPosition / BLOCK_SIZE);

    if (!tryDrop()) {
      pieceDropped();
    }
  }

  private boolean tryDrop() {
    for (Point2D blockPosition: tetronimo.blockPositions) {
      if (((int) blockPosition.getY() + currentY) >= BOARD_HEIGHT - 1) {
        return false;
      }
    }
    return checkFallenBlocks();
  }

  private boolean canMove(Tetronimo tetronimo, int x, int y) {
    for (Point2D blockPosition : tetronimo.blockPositions) {
      int newX = (int) (blockPosition.getX() + x);
      int newY = (int) (blockPosition.getY() + y);

      if (newX < 0 || newX >= BOARD_WIDTH || newY < 0 || newY >= BOARD_HEIGHT) {
        System.out.println("Out of bounds: " + newX + ", " + newY);
        return false;
      }

      if (board[newX][newY] != null) {
        System.out.println("Collision detected at: " + newX + ", " + newY);
        return false;
      }
    }
    return true;
  }

  private void tryRotate() {
    Tetronimo rotatedTetronimo = new Tetronimo(tetronimo);
    rotatedTetronimo.rotate();

    if (canMove(rotatedTetronimo, currentX, currentY)) {
      tetronimo = rotatedTetronimo;
    }
    else if (canMove(rotatedTetronimo, currentX + 1, currentY)) {
      currentX += 1;
      tetronimo = rotatedTetronimo;
    }
    else if (canMove(rotatedTetronimo, currentX - 1, currentY)) {
      currentX -= 1;
      tetronimo = rotatedTetronimo;
    }
  }

  private boolean checkFallenBlocks() {
    for (Point2D blockPosition: tetronimo.blockPositions) {
      if (board[(int) (blockPosition.getX() + currentX)][(int) (blockPosition.getY() + currentY) + 1] != null) {
        return false;
      }
    }
    return true;
  }

  private void pieceDropped() {
    for (Point2D blockPosition: tetronimo.blockPositions) {
      board[(int) (blockPosition.getX() + currentX)][(int) (blockPosition.getY() + currentY)] = tetronimo.color;
    }
    newPiece();
  }

  private void newPiece() {
    tetronimo = new Tetronimo(Tetronimo.shapesArray[new Random().nextInt(Tetronimo.shapesArray.length)], colors[new Random().nextInt(colors.length)]);
    currentX = BOARD_WIDTH / 2;
    currentY = 0;
    yPosition = 0;
  }

  private void drawBlock(Graphics g, int x, int y, Color color) {
    g.setColor(color);
    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
  }

  private void drawTetronimo(Graphics g) {
    for (Point2D blockPosition: tetronimo.blockPositions) {
      g.setColor(tetronimo.color);
      g.fillRect(((int) blockPosition.getX() + currentX) * BLOCK_SIZE, ((int) blockPosition.getY() * BLOCK_SIZE) + yPosition, BLOCK_SIZE, BLOCK_SIZE);
      g.setColor(Color.BLACK);
      g.drawRect(((int) blockPosition.getX() + currentX) * BLOCK_SIZE, ((int) blockPosition.getY() * BLOCK_SIZE) + yPosition, BLOCK_SIZE, BLOCK_SIZE);
    }
  }

  public void pause() {
    isPaused = !isPaused;
    if (isPaused) {
      timer.stop();
    } else {
      timer.start();
    }
    repaint();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    drop();
    repaint();
  }

  class TAdapter extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {

      int keycode = e.getKeyCode();

      switch (keycode) {
        case KeyEvent.VK_LEFT:
          if (canMove(tetronimo, currentX - 1, currentY)) {
            currentX--;
          }
          break;
        case KeyEvent.VK_RIGHT:
          if (canMove(tetronimo, currentX + 1, currentY)) {
            currentX++;
          }
          break;
        case KeyEvent.VK_DOWN:
          drop();
          break;
        case KeyEvent.VK_UP:
          tryRotate();
          break;
        case KeyEvent.VK_P:
          pause();
          break;
      }

      repaint();
    }
  }
}