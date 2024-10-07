package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoZ;

public class CreateTetronimoZ extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoZ();
  }
}
