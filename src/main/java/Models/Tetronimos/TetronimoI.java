package Models.Tetronimos;

import Interfaces.ITetronimo;

import java.awt.*;
import java.awt.geom.Point2D;

public class TetronimoI implements ITetronimo {
  private final Point2D[] blockPositions = {
      new Point2D.Double(0, 0),
      new Point2D.Double(0, 1),
      new Point2D.Double(0, 2),
      new Point2D.Double(0, 3),
  };
  private final Color color = Color.RED;

  @Override
  public Color getColor() {
    return color;
  }

  @Override
  public Point2D[] getBlockPositions() {
    return blockPositions;
  }
}
