package Interfaces;

import java.awt.*;
import java.awt.geom.Point2D;

public interface ITetronimo extends Cloneable {
  Color getColor();
  Point2D[] getBlockPositions();
  default void rotate() {
    for (Point2D blockPosition : getBlockPositions()) {
      double x = blockPosition.getX();
      double y = blockPosition.getY();
      blockPosition.setLocation(-y, x);
    }
  }
}
