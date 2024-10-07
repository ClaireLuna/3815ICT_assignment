package Views;

import Enums.PlayerType;
import Models.ConfigModel;
import Services.StorageService;

import javax.swing.*;
import java.awt.*;

public class ConfigScreen {
  private final JFrame frame;
  private final ConfigModel configModel;

  public ConfigScreen(JFrame frame) {
    this.frame = frame;
    this.configModel = StorageService.getInstance().getConfig();
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

  public void showConfigScreen() {
    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

    // Field Width Slider
    JLabel fieldWidthLabel = new JLabel("Field Width:");
    JSlider fieldWidthSlider = new JSlider(5, 15, configModel.FIELD_WIDTH);
    fieldWidthSlider.setMajorTickSpacing(1);
    fieldWidthSlider.setPaintTicks(true);
    fieldWidthSlider.setPaintLabels(true);
    fieldWidthSlider.addChangeListener(e -> {
      JSlider source = (JSlider) e.getSource();
      if (!source.getValueIsAdjusting()) {
        configModel.FIELD_WIDTH = source.getValue();
      }
    });
    panel.add(fieldWidthLabel);
    panel.add(fieldWidthSlider);

    // Field Height Slider
    JLabel fieldHeightLabel = new JLabel("Field Height:");
    JSlider fieldHeightSlider = new JSlider(15, 30, configModel.FIELD_HEIGHT);
    fieldHeightSlider.setMajorTickSpacing(1);
    fieldHeightSlider.setPaintTicks(true);
    fieldHeightSlider.setPaintLabels(true);
    fieldHeightSlider.addChangeListener(e -> {
      JSlider source = (JSlider) e.getSource();
      if (!source.getValueIsAdjusting()) {
        configModel.FIELD_HEIGHT = source.getValue();
      }
    });
    panel.add(fieldHeightLabel);
    panel.add(fieldHeightSlider);

    // Game Level Slider
    JLabel gameLevelLabel = new JLabel("Game Level:");
    JSlider gameLevelSlider = new JSlider(1, 10, configModel.GAME_LEVEL);
    gameLevelSlider.setMajorTickSpacing(1);
    gameLevelSlider.setPaintTicks(true);
    gameLevelSlider.setPaintLabels(true);
    gameLevelSlider.addChangeListener(e -> {
      JSlider source = (JSlider) e.getSource();
      if (!source.getValueIsAdjusting()) {
        configModel.GAME_LEVEL = source.getValue();
      }
    });
    panel.add(gameLevelLabel);
    panel.add(gameLevelSlider);

    // Music Checkbox
    JCheckBox musicCheckBox = new JCheckBox("Music On/Off", configModel.MUSIC_ON);
    musicCheckBox.addActionListener(e -> configModel.MUSIC_ON = musicCheckBox.isSelected());
    panel.add(musicCheckBox);

    // Sound Effects Checkbox
    JCheckBox soundEffectsCheckBox = new JCheckBox("Sound Effects On/Off", configModel.SOUND_ON);
    soundEffectsCheckBox.addActionListener(e -> configModel.SOUND_ON = soundEffectsCheckBox.isSelected());
    panel.add(soundEffectsCheckBox);

    // Extend Mode Checkbox
    JCheckBox extendModeCheckBox = new JCheckBox("Extend Mode On/Off", configModel.EXTEND_ON);
    panel.add(extendModeCheckBox);

    // Player One Type Radio Buttons
    JLabel playerTypeLabel = new JLabel("Player One Type:");
    ButtonGroup playerTypeGroup = new ButtonGroup();
    JRadioButton humanRadioButton = new JRadioButton("Human", configModel.PLAYER_ONE_TYPE == PlayerType.HUMAN);
    humanRadioButton.addActionListener(e -> {
      if (humanRadioButton.isSelected()) {
        configModel.PLAYER_ONE_TYPE = PlayerType.HUMAN;
      }
    });
    JRadioButton aiRadioButton = new JRadioButton("AI", configModel.PLAYER_ONE_TYPE == PlayerType.AI);
    aiRadioButton.addActionListener(e -> {
      if (aiRadioButton.isSelected()) {
        configModel.PLAYER_ONE_TYPE = PlayerType.AI;
      }
    });
    JRadioButton externalRadioButton = new JRadioButton("External", configModel.PLAYER_ONE_TYPE == PlayerType.EXTERNAL);
    externalRadioButton.addActionListener(e -> {
      if (externalRadioButton.isSelected()) {
        configModel.PLAYER_ONE_TYPE = PlayerType.EXTERNAL;
      }
    });
    playerTypeGroup.add(humanRadioButton);
    playerTypeGroup.add(aiRadioButton);
    panel.add(playerTypeLabel);
    panel.add(humanRadioButton);
    panel.add(aiRadioButton);
    panel.add(externalRadioButton);

    // Player Two Type Radio Buttons
    JLabel playerTwoTypeLabel = new JLabel("Player Two Type:");
    ButtonGroup playerTwoTypeGroup = new ButtonGroup();
    JRadioButton humanTwoRadioButton = new JRadioButton("Human", configModel.PLAYER_TWO_TYPE == PlayerType.HUMAN);
    humanTwoRadioButton.addActionListener(e -> {
      if (humanTwoRadioButton.isSelected()) {
        configModel.PLAYER_TWO_TYPE = PlayerType.HUMAN;
      }
    });
    JRadioButton aiTwoRadioButton = new JRadioButton("AI", configModel.PLAYER_TWO_TYPE == PlayerType.AI);
    aiTwoRadioButton.addActionListener(e -> {
      if (aiTwoRadioButton.isSelected()) {
        configModel.PLAYER_TWO_TYPE = PlayerType.AI;
      }
    });
    JRadioButton externalTwoRadioButton = new JRadioButton("External", configModel.PLAYER_TWO_TYPE == PlayerType.EXTERNAL);
    externalTwoRadioButton.addActionListener(e -> {
      if (externalTwoRadioButton.isSelected()) {
        configModel.PLAYER_TWO_TYPE = PlayerType.EXTERNAL;
      }
    });
    playerTwoTypeGroup.add(humanTwoRadioButton);
    playerTwoTypeGroup.add(aiTwoRadioButton);
    humanTwoRadioButton.setEnabled(configModel.EXTEND_ON);
    aiTwoRadioButton.setEnabled(configModel.EXTEND_ON);
    externalTwoRadioButton.setEnabled(configModel.EXTEND_ON);
    panel.add(playerTwoTypeLabel);
    panel.add(humanTwoRadioButton);
    panel.add(aiTwoRadioButton);
    panel.add(externalTwoRadioButton);

    // Extend Mode Checkbox Action Listener
    extendModeCheckBox.addActionListener(e -> {
      boolean extendMode = extendModeCheckBox.isSelected();
      configModel.EXTEND_ON = extendMode;
      humanTwoRadioButton.setEnabled(extendMode);
      aiTwoRadioButton.setEnabled(extendMode);
      externalTwoRadioButton.setEnabled(extendMode);
    });

    // Add Back button
    JButton backButton = new JButton("Back");
    backButton.addActionListener(e -> {
      StorageService.getInstance().saveConfig(configModel);
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

}
