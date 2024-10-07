package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoO extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoO();
  }
}
