import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JWindow {

  private final int duration;

  public SplashScreen(int duration) {
    this.duration = duration;
  }

  public void showSplash() {
    JPanel content = (JPanel) getContentPane();
    content.setBackground(Color.white);

    int width = 450;
    int height = 700;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (screenSize.width - width) / 2;
    int y = (screenSize.height - height) / 2;
    setBounds(x, y, width, height);

    JLabel label = new JLabel(new ImageIcon("src/resources/_c523d561-f13f-46b3-8923-82dcad276dfd.jpg"));
    JLabel copyrt = new JLabel("2024, 3815ICT");
    copyrt.setFont(new Font("Sans-Serif", Font.BOLD, 15));

    content.add(label, BorderLayout.CENTER);
    content.add(copyrt, BorderLayout.SOUTH);

    Color oraRed = new Color(156, 20, 20, 255);
    content.setBorder(BorderFactory.createLineBorder(oraRed, 10));

    setVisible(true);

    try {
      Thread.sleep(duration);
    } catch (Exception e) {
      e.printStackTrace();
    }

    setVisible(false);
  }

  public void showSplashAndExit() {
    showSplash();
    System.exit(0);
  }

  public static void main(String[] args) {
    SplashScreen splashScreen = new SplashScreen(5000);
    splashScreen.showSplashAndExit();
  }

}
