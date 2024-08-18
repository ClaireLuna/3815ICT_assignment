import javax.swing.*;
import java.awt.*;

public class ConfigScreen {
  private final JFrame frame;

  public ConfigScreen(JFrame frame) {
    this.frame = frame;
  }

  public void showConfigScreen() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Field Width Slider
    JLabel fieldWidthLabel = new JLabel("Field Width:");
    JSlider fieldWidthSlider = new JSlider(5, 15, 10);
    fieldWidthSlider.setMajorTickSpacing(1);
    fieldWidthSlider.setPaintTicks(true);
    fieldWidthSlider.setPaintLabels(true);
    panel.add(fieldWidthLabel);
    panel.add(fieldWidthSlider);

    // Field Height Slider
    JLabel fieldHeightLabel = new JLabel("Field Height:");
    JSlider fieldHeightSlider = new JSlider(15, 30, 20);
    fieldHeightSlider.setMajorTickSpacing(1);
    fieldHeightSlider.setPaintTicks(true);
    fieldHeightSlider.setPaintLabels(true);
    panel.add(fieldHeightLabel);
    panel.add(fieldHeightSlider);

    // Game Level Slider
    JLabel gameLevelLabel = new JLabel("Game Level:");
    JSlider gameLevelSlider = new JSlider(1, 10, 1);
    gameLevelSlider.setMajorTickSpacing(1);
    gameLevelSlider.setPaintTicks(true);
    gameLevelSlider.setPaintLabels(true);
    panel.add(gameLevelLabel);
    panel.add(gameLevelSlider);

    // Music Checkbox
    JCheckBox musicCheckBox = new JCheckBox("Music On/Off", true);
    panel.add(musicCheckBox);

    // Sound Effects Checkbox
    JCheckBox soundEffectsCheckBox = new JCheckBox("Sound Effects On/Off", true);
    panel.add(soundEffectsCheckBox);

    // AI Play Checkbox
    JCheckBox aiPlayCheckBox = new JCheckBox("AI Play On/Off", false);
    panel.add(aiPlayCheckBox);

    // Extend Mode Checkbox
    JCheckBox extendModeCheckBox = new JCheckBox("Extend Mode On/Off", false);
    panel.add(extendModeCheckBox);

    // Add Back button
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      MainScreen mainScreen = new MainScreen(frame);
      mainScreen.showMainScreen();
    }); // Go back to the main screen
    backButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the button

    panel.add(Box.createRigidArea(new Dimension(0, 20))); // Spacer
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

      ConfigScreen configScreen = new ConfigScreen(frame);
      // Set the initial screen
      configScreen.showConfigScreen();

      frame.setVisible(true);
    });
  }

}
