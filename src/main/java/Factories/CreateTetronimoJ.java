package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoJ;

public class CreateTetronimoJ extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoJ();
  }
}
