package Services;

import Models.ConfigModel;
import Models.HighScore;
import Models.Sort;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

public final class StorageService {
  private static StorageService instance;
  private final String SCORE_FILE = "score.json";
  private final String CONFIG_FILE = "config.json";

  private StorageService() {
  }

  public static synchronized StorageService getInstance() {
    if (instance == null) {
      instance = new StorageService();
    }
    return instance;
  }

  public ConfigModel getConfig() {
    StringBuilder content = new StringBuilder();
    try (FileReader reader = new FileReader(CONFIG_FILE)) {
      int i;
      while ((i = reader.read()) != -1) {
        content.append((char) i);
      }
      return new Gson().fromJson(content.toString(), ConfigModel.class);
    } catch (Exception e) {
      return new ConfigModel();
    }
  }

  public void saveConfig(ConfigModel config) {
    try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
      String configJson = new Gson().toJson(config);
      writer.write(configJson);
    } catch (Exception e) {
      System.out.println("Error saving config:" + e.getMessage());
    }
  }

  public HighScore[] getHighScores() {
    StringBuilder content = new StringBuilder();
    try (FileReader reader = new FileReader(SCORE_FILE)) {
      int i;
      while ((i = reader.read()) != -1) {
        content.append((char) i);
      }

      HighScore[] highScores = new Gson().fromJson(content.toString(), HighScore[].class);
      Sort sort = new Sort();
      sort.mergeSort(highScores, 0, highScores.length - 1);
      return highScores;
    } catch (Exception e) {
      return new HighScore[0];
    }
  }

  public void addHighScore(HighScore highScore) {
    HighScore[] highScores = getInstance().getHighScores();
    try (FileWriter writer = new FileWriter(SCORE_FILE)) {
      HighScore[] newHighScores = Arrays.copyOf(highScores, highScores.length + 1);
      newHighScores[newHighScores.length - 1] = highScore;

      // Sort the high scores
      Sort sort = new Sort();
      sort.mergeSort(newHighScores, 0, newHighScores.length - 1);

      writer.write(new Gson().toJson(newHighScores));
    } catch (Exception e) {
      System.out.println("Error saving high scores:" + e.getMessage());
    }
  }
}
