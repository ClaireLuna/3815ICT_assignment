package Models;

import Enums.PlayerType;

public class ConfigModel {
  public int FIELD_WIDTH;
  public int FIELD_HEIGHT;
  public int GAME_LEVEL;
  public boolean SOUND_ON;
  public boolean MUSIC_ON;
  public boolean EXTEND_ON;
  public PlayerType PLAYER_ONE_TYPE;
  public PlayerType PLAYER_TWO_TYPE;

  public ConfigModel() {
    this.FIELD_WIDTH = 10;
    this.FIELD_HEIGHT = 20;
    this.GAME_LEVEL = 1;
    this.SOUND_ON = true;
    this.MUSIC_ON = true;
    this.PLAYER_ONE_TYPE = PlayerType.HUMAN;
    this.PLAYER_TWO_TYPE = PlayerType.HUMAN;
    this.EXTEND_ON = false;
  }

}
