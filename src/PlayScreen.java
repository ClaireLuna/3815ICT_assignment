import javax.swing.*;
import java.awt.*;

public class PlayScreen {
  private final JFrame frame;

  public PlayScreen(JFrame frame) {
    this.frame = frame;
  }

  private JPanel renderPlayField() {
    JPanel playField = new JPanel();
    playField.setBackground(Color.GRAY);
    playField.setPreferredSize(new Dimension(300, 500));
    playField.setMaximumSize(new Dimension(300, 500));


    return playField;
  }

  public void showPlayScreen() {
    JPanel panel = new JPanel();
    panel.setLayout(new BorderLayout());
    panel.setBackground(Color.WHITE);

    JPanel playField = renderPlayField();
    panel.add(playField, BorderLayout.CENTER);

    // Add Back button
    JButton backButton = new JButton("Back");
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.setPreferredSize(new Dimension(200, 50));
    backButton.setMaximumSize(new Dimension(200, 50));
    backButton.setFocusPainted(false);
    backButton.addActionListener(e -> {
      MainScreen mainScreen = new MainScreen();
      mainScreen.showMainScreen();
    }); // Go back to the main screen
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(backButton, BorderLayout.SOUTH);

    // Update the content pane
    frame.setContentPane(panel);
    frame.revalidate();
    frame.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Tetris Game");
      frame.setTitle("Tetris Game");
      frame.setSize(400, 600);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);

      PlayScreen playScreen = new PlayScreen(frame);
      // Set the initial screen
      playScreen.showPlayScreen();

      frame.setVisible(true);
    });
  }
}
