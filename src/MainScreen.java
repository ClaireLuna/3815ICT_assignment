import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends JFrame {
  public MainScreen() {
    setTitle("Tetris Game");
    setSize(400, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Create a panel to hold all components
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBackground(Color.BLACK);

    // Add a logo (as an example, we'll use a JLabel with text, replace with an ImageIcon if you have an image)
    JLabel logoLabel = new JLabel("TETRIS");
    logoLabel.setFont(new Font("Serif", Font.BOLD, 40));
    logoLabel.setForeground(Color.WHITE);
    logoLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
    panel.add(Box.createRigidArea(new Dimension(0, 50))); // Spacer
    panel.add(logoLabel);

    // Add Play button
    JButton playButton = new JButton("Play");
    playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    playButton.setPreferredSize(new Dimension(200, 50));
    playButton.setMaximumSize(new Dimension(200, 50));
    playButton.setFocusPainted(false);
    panel.add(Box.createRigidArea(new Dimension(0, 50))); // Spacer
    panel.add(playButton);

    // Add Configuration button
    JButton configButton = new JButton("Configuration");
    configButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    configButton.setPreferredSize(new Dimension(200, 50));
    configButton.setMaximumSize(new Dimension(200, 50));
    configButton.setFocusPainted(false);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
    panel.add(configButton);

    // Add High Scores button
    JButton highScoresButton = new JButton("High Scores");
    highScoresButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    highScoresButton.setPreferredSize(new Dimension(200, 50));
    highScoresButton.setMaximumSize(new Dimension(200, 50));
    highScoresButton.setFocusPainted(false);
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
    panel.add(highScoresButton);

    // Add Exit button
    JButton exitButton = new JButton("Exit");
    exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    exitButton.setPreferredSize(new Dimension(200, 50));
    exitButton.setMaximumSize(new Dimension(200, 50));
    exitButton.setFocusPainted(false);
    exitButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    });
    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
    panel.add(exitButton);

    // Add panel to the frame
    add(panel);

    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(MainScreen::new);
  }
}
