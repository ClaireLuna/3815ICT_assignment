package Views;

import javax.swing.*;
import java.awt.*;

public class MainScreen {
  private final JFrame frame;

  public MainScreen(JFrame frame) {
    this.frame = frame;
  }

  public void showMainScreen() {
    frame.setLocationRelativeTo(null);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.WHITE);

    JLabel logoLabel = new JLabel("TETRIS");
    logoLabel.setFont(new Font("Serif", Font.BOLD, 40));
    logoLabel.setForeground(Color.BLACK);
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(Box.createRigidArea(new Dimension(0, 50)));
    panel.add(logoLabel);

    JButton playButton = new JButton("Play");
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.setPreferredSize(new Dimension(200, 50));
    playButton.setMaximumSize(new Dimension(200, 50));
    playButton.setFocusPainted(false);
    playButton.addActionListener(e -> {
      PlayScreen playScreen = new PlayScreen(frame);
      playScreen.showPlayScreen();
    });
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
    exitButton.addActionListener(e -> {
      int response = JOptionPane.showConfirmDialog(null,
          "Are you sure you want to exit?", "Exit?",
          JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
      if (response == JOptionPane.YES_OPTION) {
        System.exit(0);
      }
    });
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(exitButton);

    // Update the content pane
    frame.setContentPane(panel);
    frame.revalidate();
    frame.repaint();
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      JFrame frame = new JFrame("Tetris Game");
      frame.setTitle("Tetris Game");
      frame.setSize(450, 700);
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setLocationRelativeTo(null);

      MainScreen mainScreen = new MainScreen(frame);
      mainScreen.showMainScreen();

      frame.setVisible(true);
    });
  }
}
