package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoT extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoT();
  }
}
