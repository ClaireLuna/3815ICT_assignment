package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoJ extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoJ();
  }
}
