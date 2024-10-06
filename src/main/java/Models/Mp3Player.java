package Models;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;

public class Mp3Player implements Runnable {
  private MediaPlayer mediaPlayer;
  private boolean isPaused;
  private boolean repeat;
  private final Media media;

  public Mp3Player(InputStream inputStream) throws Exception {
    // Initialize JavaFX runtime
    new JFXPanel();

    // Create a temporary file
    File tempFile = Files.createTempFile("temp", ".mp3").toFile();
    tempFile.deleteOnExit();

    // Write InputStream to the temporary file
    try (FileOutputStream out = new FileOutputStream(tempFile)) {
      byte[] buffer = new byte[1024];
      int bytesRead;
      while ((bytesRead = inputStream.read(buffer)) != -1) {
        out.write(buffer, 0, bytesRead);
      }
    }

    // Convert File to Media
    this.media = new Media(tempFile.toURI().toString());
    this.mediaPlayer = new MediaPlayer(media);
    this.isPaused = false;
    this.repeat = false;

    // Set the repeat behavior
    mediaPlayer.setOnEndOfMedia(() -> {
      if (repeat) {
        mediaPlayer.seek(mediaPlayer.getStartTime());
        mediaPlayer.play();
      }
    });
  }

  @Override
  public void run() {
    if (mediaPlayer.getStatus() == MediaPlayer.Status.STOPPED) {
      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setOnEndOfMedia(() -> {
        if (repeat) {
          mediaPlayer.seek(mediaPlayer.getStartTime());
          mediaPlayer.play();
        }
      });
    }
    mediaPlayer.play();
  }

  public void pause() {
    if (mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
      mediaPlayer.pause();
      isPaused = true;
    }
  }

  public void resume() {
    if (isPaused) {
      mediaPlayer.play();
      isPaused = false;
    }
  }

  public void stop() {
    mediaPlayer.stop();
  }

  public void setRepeat(boolean repeat) {
    this.repeat = repeat;
  }

  public boolean isPlaying() {
    return mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING;
  }

  public void seekToStart() {
    mediaPlayer.seek(mediaPlayer.getStartTime());
  }

  public void play() {
    mediaPlayer.play();
  }
}