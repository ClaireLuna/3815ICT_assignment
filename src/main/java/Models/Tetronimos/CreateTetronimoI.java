package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoI extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoI();
  }
}
