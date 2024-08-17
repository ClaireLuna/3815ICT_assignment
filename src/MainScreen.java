import javax.swing.*;
import java.awt.*;

public class MainScreen {
  private final JFrame frame;

  public MainScreen() {
    frame = new JFrame("Tetris Game");
    frame.setTitle("Tetris Game");
    frame.setSize(400, 600);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocationRelativeTo(null);

    // Set the initial screen
    showMainScreen();

    frame.setVisible(true);
  }

  public void showMainScreen() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.BLACK);

    JLabel logoLabel = new JLabel("TETRIS");
    logoLabel.setFont(new Font("Serif", Font.BOLD, 40));
    logoLabel.setForeground(Color.WHITE);
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(Box.createRigidArea(new Dimension(0, 50)));
    panel.add(logoLabel);

    JButton playButton = new JButton("Play");
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.setPreferredSize(new Dimension(200, 50));
    playButton.setMaximumSize(new Dimension(200, 50));
    playButton.setFocusPainted(false);
    panel.add(Box.createRigidArea(new Dimension(0, 50)));
    panel.add(playButton);

    JButton configButton = new JButton("Configuration");
    configButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    configButton.setPreferredSize(new Dimension(200, 50));
    configButton.setMaximumSize(new Dimension(200, 50));
    configButton.setFocusPainted(false);
    configButton.addActionListener(e -> {
      ConfigScreen configScreen = new ConfigScreen(frame);
      configScreen.showConfigScreen();
    });
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(configButton);

    JButton highScoresButton = new JButton("High Scores");
    highScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    highScoresButton.setPreferredSize(new Dimension(200, 50));
    highScoresButton.setMaximumSize(new Dimension(200, 50));
    highScoresButton.setFocusPainted(false);
    highScoresButton.addActionListener(e -> {
      ScoreScreen scoreScreen = new ScoreScreen(frame);
      scoreScreen.showScoreScreen();
    });
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(highScoresButton);

    JButton exitButton = new JButton("Exit");
    exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    exitButton.setPreferredSize(new Dimension(200, 50));
    exitButton.setMaximumSize(new Dimension(200, 50));
    exitButton.setFocusPainted(false);
    exitButton.addActionListener(e -> System.exit(0));
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(exitButton);

    // Update the content pane
    frame.setContentPane(panel);
    frame.revalidate();
    frame.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainScreen::new);
  }
}
