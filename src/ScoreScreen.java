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
    panel.setBackground(Color.BLACK);

    JPanel scoreTable = new JPanel();
    scoreTable.setLayout(new FlowLayout());
    scoreTable.setBackground(Color.BLACK);

    JPanel usernamePanel = new JPanel();
    usernamePanel.setLayout(new BoxLayout(usernamePanel, BoxLayout.Y_AXIS));
    usernamePanel.setBackground(Color.BLACK);
    JPanel scorePanel = new JPanel();
    scorePanel.setLayout(new BoxLayout(scorePanel, BoxLayout.Y_AXIS));
    scorePanel.setBackground(Color.BLACK);

    JLabel usernameLabel = new JLabel("Username: ");
    usernameLabel.setForeground(Color.WHITE);
    usernamePanel.add(usernameLabel);
    usernamePanel.add(Box.createRigidArea(new Dimension(0, 20)));
    JLabel scoreLabel = new JLabel("Score: ");
    scoreLabel.setForeground(Color.WHITE);
    scorePanel.add(scoreLabel);
    scorePanel.add(Box.createRigidArea(new Dimension(0, 20)));

    for(HighScore score : mockScores) {
      JLabel userLabel = new JLabel(score.username);
      userLabel.setForeground(Color.WHITE);
      usernamePanel.add(userLabel);
      usernamePanel.add(Box.createRigidArea(new Dimension(0, 20)));
      JLabel userScoreLabel = new JLabel(String.valueOf(score.score));
      userScoreLabel.setForeground(Color.WHITE);
      scorePanel.add(userScoreLabel);
      scorePanel.add(Box.createRigidArea(new Dimension(0, 20)));
    }

    scoreTable.add(usernamePanel);
    scoreTable.add(scorePanel);

    panel.add(scoreTable);


    // Add Back button
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      MainScreen mainScreen = new MainScreen();
      mainScreen.showMainScreen();
    }); // Go back to the main screen
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button
    panel.add(backButton);

    // Update the content pane
    frame.setContentPane(panel);
    frame.revalidate();
    frame.repaint();
  }

}
