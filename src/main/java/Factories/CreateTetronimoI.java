package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoI;

public class CreateTetronimoI extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoI();
  }
}
