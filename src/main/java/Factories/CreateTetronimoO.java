package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoO;

public class CreateTetronimoO extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoO();
  }
}
