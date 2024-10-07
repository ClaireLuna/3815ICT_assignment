package Models.Tetronimos;

import Interfaces.ITetronimo;

import java.awt.*;
import java.awt.geom.Point2D;

public class TetronimoJ implements ITetronimo {
  private final Point2D[] blockPositions = {
      new Point2D.Double(0, 0),
      new Point2D.Double(0, 1),
      new Point2D.Double(0, 2),
      new Point2D.Double(-1, 2),
  };
  private final Color color = Color.GREEN;

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Point2D[] getBlockPositions() {
    return blockPositions;
  }
}
