// src/Views/PlayScreen.java
package Views;

import Controllers.GameController;
import Models.ConfigModel;
import Models.GameModel;
import Models.HighScore;
import Services.StorageService;

import javax.swing.*;
import java.awt.*;

public class PlayScreen {
    private final JFrame frame;
    private final StorageService storageService = StorageService.getInstance();

    public PlayScreen(JFrame frame) {
        this.frame = frame;
    }

    public void showPlayScreen() {
        ConfigModel config = storageService.getConfig();
        int BLOCK_SIZE = 30;
        frame.setSize((config.FIELD_WIDTH * BLOCK_SIZE) + 300, (config.FIELD_HEIGHT * BLOCK_SIZE) + 100);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GameModel gameModel = new GameModel(config.FIELD_WIDTH, config.FIELD_HEIGHT, BLOCK_SIZE, config.GAME_LEVEL);

        JPanel infoPanel = new JPanel(new GridLayout(4, 1));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setPreferredSize(new Dimension(200, 0));
        JLabel scoreLabel = new JLabel("Score: " + gameModel.getScore());
        infoPanel.add(scoreLabel);
        JLabel startingLevelLabel = new JLabel("Initial Level: " + gameModel.getStartingLevel());
        infoPanel.add(startingLevelLabel);
        JLabel levelLabel = new JLabel("Level: " + gameModel.getLevel());
        infoPanel.add(levelLabel);
        JLabel linesClearedLabel = new JLabel("Lines Cleared: " + gameModel.getTotalLinesCleared());
        infoPanel.add(linesClearedLabel);

        PlayField playField = new PlayField(gameModel);
        GameController gameController = new GameController(gameModel, playField, infoPanel, scoreLabel, levelLabel, linesClearedLabel);


        centerPanel.add(playField);
        panel.add(centerPanel, BorderLayout.CENTER);
        panel.add(infoPanel, BorderLayout.EAST);

        JButton backButton = new JButton("Back");
        backButton.setPreferredSize(new Dimension(200, 50));
        backButton.setFocusPainted(false);
        backButton.addActionListener(e -> {
            if (!gameModel.isGameEnded) {
                boolean wasPaused = gameModel.isPaused;
                if (!wasPaused) {
                    gameController.pauseGame();
                }
                int response = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to end the game?", "End Game?",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (response == JOptionPane.YES_OPTION) {
                    endGame(gameModel.getScore());
                } else if (!wasPaused) {
                    gameController.pauseGame();
                }
                playField.requestFocusInWindow();
            } else {
                endGame(gameModel.getScore());
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
        gameController.startGame();
    }

    private void endGame(int score) {
        if (!storageService.getConfig().AI_ON && score > 0) {
            String username = JOptionPane.showInputDialog("Enter your name:");
            storageService.addHighScore(new HighScore(username, score));
        } else if (storageService.getConfig().AI_ON && score > 0) {
            storageService.addHighScore(new HighScore("AI", score));
        }
        frame.setSize(450, 700);
        MainScreen mainScreen = new MainScreen(frame);
        mainScreen.showMainScreen();
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