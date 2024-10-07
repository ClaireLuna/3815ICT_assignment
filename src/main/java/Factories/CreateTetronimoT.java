package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoT;

public class CreateTetronimoT extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoT();
  }
}
