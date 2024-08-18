import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
  private final Color[] colors = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE };

  public PlayField(int BOARD_WIDTH, int BOARD_HEIGHT, int BLOCK_SIZE) {
    this.BOARD_WIDTH = BOARD_WIDTH;
    this.BOARD_HEIGHT = BOARD_HEIGHT;
    this.BLOCK_SIZE = BLOCK_SIZE;
    board = new Color[BOARD_WIDTH][BOARD_HEIGHT];
    timer = new Timer(200, this);

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
  }

  @Override
  public void paint(Graphics g) {
    super.paint(g);

    for (int i = 0; i < BOARD_WIDTH; i++) {
      for (int j = 0; j < BOARD_HEIGHT; j++) {
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
    for (int x = 1; x < BOARD_WIDTH; x++) {
      g.drawLine(x * BLOCK_SIZE, 0, x * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE);
    }
    for (int y = 1; y < BOARD_HEIGHT; y++) {
      g.drawLine(0, y * BLOCK_SIZE, BOARD_WIDTH * BLOCK_SIZE, y * BLOCK_SIZE);
    }
  }

  private void drop() {
    int LEVEL = 15;
    yPosition += LEVEL;
    currentY = yPosition / BLOCK_SIZE;

    if (!tryDrop()) {
      pieceDropped();
    }
  }

  private boolean tryDrop() {
//    System.out.println(Arrays.toString(tetronimo.blockPositions));
    for (Point2D blockPosition: tetronimo.blockPositions) {
//      System.out.println(blockPosition);
      if ((int) (blockPosition.getY() + currentY) >= BOARD_HEIGHT) {
        return false;
      }
    }
    return true;
  }

//  private boolean checkFallenBlocks() {
//    for (Point2D blockPosition: tetronimo.blockPositions) {
//      if (board[(int) (blockPosition.getX() + currentX)][(int) (blockPosition.getY() + currentY) - 1] != null) {
//        return false;
//      }
//    }
//    return true;
//  }

  private void pieceDropped() {
    for (Point2D blockPosition: tetronimo.blockPositions) {
      board[(int) (blockPosition.getX() + currentX)][(int) (blockPosition.getY() + currentY - 1)] = tetronimo.color;
    }
    newPiece();
  }

  private void newPiece() {
    tetronimo = new Tetronimo(Tetronimo.shapesArray[new Random().nextInt(7)], colors[new Random().nextInt(colors.length)]);
    currentX = BOARD_WIDTH / 2;
    currentY = 0;
    yPosition = 0;
  }

  private void drawBlock(Graphics g, int x, int y, Color color) {
    g.setColor(color);
    g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
  }

  private void drawTetronimo(Graphics g) {
    g.setColor(tetronimo.color);
    for (Point2D blockPosition: tetronimo.blockPositions) {
      g.fillRect(((int) blockPosition.getX() + currentX) * BLOCK_SIZE, ((int) blockPosition.getY() * BLOCK_SIZE) + yPosition, BLOCK_SIZE, BLOCK_SIZE);
    }
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    drop();
    repaint();
  }
}