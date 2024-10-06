import Views.MainScreen;
import Views.SplashScreen;

import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    SplashScreen splashScreen = new SplashScreen(5000);
    splashScreen.showSplash();

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