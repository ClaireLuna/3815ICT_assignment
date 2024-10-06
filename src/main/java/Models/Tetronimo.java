package Models;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.Random;

public class Tetronimo {
  public static Shapes[] shapesArray = Shapes.values();
  private final Shapes shape;
  public Point2D[] blockPositions;
  public Color color;

  public Tetronimo(Tetronimo tetronimo) {
    this.color = tetronimo.color;
    this.blockPositions = new Point2D[tetronimo.blockPositions.length];
    this.shape = tetronimo.shape;

    for (int i = 0; i < tetronimo.blockPositions.length; i++) {
      this.blockPositions[i] = (Point2D) tetronimo.blockPositions[i].clone();
    }
  }

  public enum Shapes {
    O, I, S, Z, L, J, T
  }

  public void rotate() {
    if (shape != Shapes.O) {
      for (Point2D blockPosition : blockPositions) {
        double x = blockPosition.getX();
        double y = blockPosition.getY();
        blockPosition.setLocation(-y, x);
      }
    }
  }

  public Tetronimo() {
    Color[] colors = {Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.CYAN, Color.MAGENTA, Color.ORANGE};

    int shapeIndex = new Random().nextInt(Tetronimo.shapesArray.length);
    this.shape = shapesArray[shapeIndex];
    this.color = colors[shapeIndex];

    switch (shape) {
      case Shapes.O:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(1, 0),
            new Point2D.Double(1, 1),
        };
        break;
      case Shapes.I:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(0, 2),
            new Point2D.Double(0, 3),
        };
        break;
      case Shapes.S:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(-1, 1),
        };
        break;
      case Shapes.Z:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(-1, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(1, 1),
        };
        break;
      case Shapes.L:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(0, 2),
            new Point2D.Double(1, 2),
        };
        break;
      case Shapes.J:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(0, 1),
            new Point2D.Double(0, 2),
            new Point2D.Double(-1, 2),
        };
        break;
      case Shapes.T:
        blockPositions = new Point2D[]{
            new Point2D.Double(0, 0),
            new Point2D.Double(1, 0),
            new Point2D.Double(-1, 0),
            new Point2D.Double(0, 1),
        };
        break;
    }
  }
}
