package Enums;

import java.util.List;
import java.util.Random;

public enum Shapes {
  O, I, S, Z, L, J, T;

  private static final List<Shapes> VALUES =
      List.of(values());
  private static final int SIZE = VALUES.size();
  private static final Random RANDOM = new Random();

  public static Shapes randomShape()  {
    return VALUES.get(RANDOM.nextInt(SIZE));
  }
}
