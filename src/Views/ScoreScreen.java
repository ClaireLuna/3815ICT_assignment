package Views;

import Models.HighScore;

import javax.swing.*;
import java.awt.*;

public class ScoreScreen {
  private final JFrame frame;
  private final HighScore[] mockScores =
      {
          new HighScore(1000, "Claire"),
          new HighScore(920, "Jeff"),
          new HighScore(925, "Jeff"),
          new HighScore(700, "Bobby"),
          new HighScore(675, "Jeff"),
          new HighScore(620, "Sam"),
          new HighScore(520, "Gregory"),
          new HighScore(525, "Cassandra"),
          new HighScore(200, "Emily"),
          new HighScore(400, "Jeff"),
      };

  public ScoreScreen(JFrame frame) {
    this.frame = frame;
  }

  public void showScoreScreen() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.WHITE);

    JPanel scoreTable = new JPanel();
    scoreTable.setLayout(new FlowLayout());
    scoreTable.setBackground(Color.WHITE);

    JPanel usernamePanel = new JPanel();
    usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
    usernamePanel.setBackground(Color.WHITE);
    JPanel scorePanel = new JPanel();
    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
    scorePanel.setBackground(Color.WHITE);

    JLabel usernameLabel = new JLabel("Player name");
    usernameLabel.setForeground(Color.BLACK);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(Box.createRigidArea(new Dimension(0, 20)));
    JLabel scoreLabel = new JLabel("Score");
    scoreLabel.setForeground(Color.BLACK);
    scorePanel.add(scoreLabel);
    scorePanel.add(Box.createRigidArea(new Dimension(0, 20)));

    for(HighScore score : mockScores) {
      JLabel userLabel = new JLabel(score.username);
      userLabel.setForeground(Color.BLACK);
      usernamePanel.add(userLabel);
      usernamePanel.add(Box.createRigidArea(new Dimension(0, 20)));
      JLabel userScoreLabel = new JLabel(String.valueOf(score.score));
      userScoreLabel.setForeground(Color.BLACK);
      scorePanel.add(userScoreLabel);
      scorePanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    scoreTable.add(usernamePanel);
    scoreTable.add(Box.createRigidArea(new Dimension(20, 0)));
    scoreTable.add(scorePanel);

    panel.add(scoreTable);

    // Add Back button
    JButton backButton = new JButton("Back");
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    backButton.setPreferredSize(new Dimension(200, 50));
    backButton.setMaximumSize(new Dimension(200, 50));
    backButton.setFocusPainted(false);
    backButton.addActionListener(e -> {
      MainScreen mainScreen = new MainScreen(frame);
      mainScreen.showMainScreen();
    }); // Go back to the main screen
    panel.add(Box.createRigidArea(new Dimension(0, 20)));
    panel.add(backButton);

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

      ScoreScreen scoreScreen = new ScoreScreen(frame);
      // Set the initial screen
      scoreScreen.showScoreScreen();

      frame.setVisible(true);
    });
  }

}
