import javax.swing.*;

public class Main {
  public static void main(String[] args) {
    SplashScreen splashScreen = new SplashScreen(5000);
    splashScreen.showSplash();

    SwingUtilities.invokeLater(MainScreen::new);
  }
}