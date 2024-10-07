package Models.Tetronimos;

import Interfaces.ITetronimo;

public class CreateTetronimoS extends TetronimoFactory {
  @Override
  public ITetronimo createTetronimo() {
    return new TetronimoS();
  }
}
