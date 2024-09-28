// src/Views/PlayScreen.java
package Views;

import Controllers.GameController;
import Models.GameModel;

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
        int LEVEL = 1;
        frame.setSize((BOARD_WIDTH * BLOCK_SIZE) + 100, (BOARD_HEIGHT * BLOCK_SIZE) + 100);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.WHITE);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(Color.WHITE);

        GameModel gameModel = new GameModel(BOARD_WIDTH, BOARD_HEIGHT, BLOCK_SIZE, LEVEL);
        PlayField playField = new PlayField(gameModel);
        GameController gameController = new GameController(gameModel, playField);

        centerPanel.add(playField);
        panel.add(centerPanel, BorderLayout.CENTER);

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
                    frame.setSize(450, 700);
                    MainScreen mainScreen = new MainScreen(frame);
                    mainScreen.showMainScreen();
                } else if (!wasPaused) {
                    gameController.pauseGame();
                }
                playField.requestFocusInWindow();
            } else {
                frame.setSize(450, 700);
                MainScreen mainScreen = new MainScreen(frame);
                mainScreen.showMainScreen();
            }
        });

        panel.add(backButton, BorderLayout.SOUTH);

        frame.setContentPane(panel);
        frame.revalidate();
        frame.repaint();
        gameController.startGame();
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