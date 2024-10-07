package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoZ extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoZ();
  }
}
