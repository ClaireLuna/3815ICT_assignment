package Models;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Mp3Player implements Runnable {
  private final String filePath;
  private Player player;
  private boolean isPaused;
  private boolean isStopped;
  private boolean repeat;

  public Mp3Player(String filePath) {
    this.filePath = filePath;
    this.isPaused = false;
    this.isStopped = false;
    this.repeat = false;
  }

  private synchronized void initPlayer() {
    try {
      FileInputStream fileInputStream = new FileInputStream(filePath);
      player = new Player(fileInputStream);
    } catch (FileNotFoundException | JavaLayerException e) {
      System.out.println("Error: fail load mp3 file." + e.getMessage());
    }
  }

  @Override
  public void run() {
    do {
      if (!isPaused && !isStopped) {
        try {
          initPlayer();
          player.play();
        } catch (JavaLayerException e) {
          throw new RuntimeException(e);
        } finally {
          player.close();
        }
      }
      System.out.println("Out of while loop...");
    } while (repeat && !isStopped);
  }

  public synchronized void pause() {
    isPaused = true;
    if (player != null) {
      player.close(); // Closing the player to pause
    }
  }

  public synchronized void resume() {
    isPaused = false;
    if (player != null) {
      new Thread(this).start(); // Restart the player in a new thread
    }
  }

  public synchronized void stop() {
    isStopped = true;
    if (player != null) {
      player.close();
    }
  }

  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }
}