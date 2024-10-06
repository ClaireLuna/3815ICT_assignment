package Models;

import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class Mp3Player implements Runnable {
  private MediaPlayer mediaPlayer;
  private boolean isPaused;
  private boolean repeat;
  private final Media media;

  public Mp3Player(File file) {
    // Initialize JavaFX runtime
    new JFXPanel();

    // Convert File to Media
    this.media = new Media(file.toURI().toString());
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