package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoL extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoL();
  }
}
