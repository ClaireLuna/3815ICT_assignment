package Factories;

import Enums.Shapes;
import Interfaces.ITetronimo;

public abstract class TetronimoFactory {
  public static TetronimoFactory getRandomTetronimo() {
    Shapes shape = Shapes.randomShape();
    return switch (shape) {
      case J -> new CreateTetronimoJ();
      case L -> new CreateTetronimoL();
      case O -> new CreateTetronimoO();
      case S -> new CreateTetronimoS();
      case T -> new CreateTetronimoT();
      case Z -> new CreateTetronimoZ();
      default -> new CreateTetronimoI();
    };
  }

  public abstract ITetronimo createTetronimo();
}
