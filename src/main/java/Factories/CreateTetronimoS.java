package Factories;

import Interfaces.ITetronimo;
import Models.Tetronimos.TetronimoS;

public class CreateTetronimoS extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoS();
  }
}
