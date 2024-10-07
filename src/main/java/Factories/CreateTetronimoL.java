package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoL;

public class CreateTetronimoL extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoL();
  }
}
