package Models;

public class HighScore implements Comparable<HighScore> {
  public int score;
  public String username;
  
  public HighScore(String username, int score) {
    this.score = score;
    this.username = username;
  }

  @Override
  public int compareTo(HighScore other) {
    return Integer.compare(this.score, other.score);
  }
}
