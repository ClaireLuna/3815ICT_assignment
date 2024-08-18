import javax.swing.*;
import java.awt.*;

public class PlayField extends JPanel {
  private final int BOARD_WIDTH;
  private final int BOARD_HEIGHT;
  private final int BLOCK_SIZE;

  public PlayField(int BOARD_WIDTH, int BOARD_HEIGHT, int BLOCK_SIZE) {
    this.BOARD_WIDTH = BOARD_WIDTH;
    this.BOARD_HEIGHT = BOARD_HEIGHT;
    this.BLOCK_SIZE = BLOCK_SIZE;

    setFocusable(true);
    setBackground(Color.LIGHT_GRAY);

    setPreferredSize(new Dimension(BOARD_WIDTH * BLOCK_SIZE, BOARD_HEIGHT * BLOCK_SIZE));
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
}