package Services;

import Models.ConfigModel;
import Models.HighScore;
import com.google.gson.Gson;

import java.io.FileReader;
import java.io.FileWriter;

public final class StorageService {
  private static StorageService instance;
  private final String SCORE_FILE = "score.json";
  private final String CONFIG_FILE = "config.json";

  private StorageService() {
  }

  public static StorageService getInstance() {
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
      return new Gson().fromJson(content.toString(), HighScore[].class);
    } catch (Exception e) {
      return null;
    }
  }

  public void saveHighScores(HighScore[] highScores) {
    try (FileWriter writer = new FileWriter(SCORE_FILE)) {
      writer.write(new Gson().toJson(highScores));
    } catch (Exception e) {
      System.out.println("Error saving high scores:" + e.getMessage());
    }
  }
}
