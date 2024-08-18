import java.awt.*;
import java.awt.geom.Point2D;

public class Tetronimo {
  public static Shapes[] shapesArray = Shapes.values();
  public Point2D[] blockPositions;
  public Color color;

  public enum Shapes {
    O, I, S, Z, L, J, T
  }

  public Tetronimo(Shapes shape, Color color) {
    this.color = color;

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
