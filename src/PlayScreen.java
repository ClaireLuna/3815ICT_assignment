import javax.swing.*;
import java.awt.*;

public class PlayScreen {
  private final JFrame frame;

  public PlayScreen(JFrame frame) {
    this.frame = frame;
  }

  public void showPlayScreen() {
    int BOARD_WIDTH = 10;
    int BOARD_HEIGHT = 20;
    int BLOCK_SIZE = 30;
    frame.setSize((BOARD_WIDTH * BLOCK_SIZE) + 100, (BOARD_HEIGHT * BLOCK_SIZE) + 100);

    // Create the main panel using BorderLayout
    JPanel panel = new JPanel(new BorderLayout());
    panel.setBackground(Color.WHITE);

    // Create a wrapper panel for centering the PlayField
    JPanel centerPanel = new JPanel(new GridBagLayout());
    centerPanel.setBackground(Color.WHITE);  // Match background to avoid visible lines

    // Create and add PlayField to the center of the centerPanel
    PlayField playField = new PlayField(BOARD_WIDTH, BOARD_HEIGHT, BLOCK_SIZE);

    // Add PlayField to the center of the center panel
    centerPanel.add(playField);  // No need for GridBagConstraints, default centering

    // Add the centerPanel to the main panel
    panel.add(centerPanel, BorderLayout.CENTER);

    // Create Back button
    JButton backButton = new JButton("Back");
    backButton.setPreferredSize(new Dimension(200, 50));
    backButton.setFocusPainted(false);
    backButton.addActionListener(e -> {
      if (!playField.isGameEnded) {
        boolean wasPaused = playField.isPaused;
        if (!wasPaused) {
          playField.pause();
        }
        int response = JOptionPane.showConfirmDialog(null,
            "Are you sure you want to end the game?", "End Game?",
            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (response == JOptionPane.YES_OPTION) {
          frame.setSize(450, 700);
          MainScreen mainScreen = new MainScreen(frame);
          mainScreen.showMainScreen();
        }
        else if (!wasPaused) {
          playField.pause();
        }
        playField.requestFocusInWindow();
      }
      else {
        MainScreen mainScreen = new MainScreen(frame);
        mainScreen.showMainScreen();
      }
    });

    // Add the button directly to the bottom (BorderLayout.SOUTH)
    panel.add(backButton, BorderLayout.SOUTH);

    // Update the content pane
    frame.setContentPane(panel);
    frame.revalidate();
    frame.repaint();
    playField.start();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Tetris Game");
      frame.setTitle("Tetris Game");
      frame.setSize(400, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);

      PlayScreen playScreen = new PlayScreen(frame);
      playScreen.showPlayScreen();

      frame.setVisible(true);
    });
  }
}