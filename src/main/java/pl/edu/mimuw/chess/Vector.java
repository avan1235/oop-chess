package pl.edu.mimuw.chess;

import java.util.Objects;

public class Vector {
  public static final Vector UP = new Vector(0, 1);
  public static final Vector DOWN = new Vector(0, -1);
  public static final Vector LEFT = new Vector(-1, 0);
  public static final Vector RIGHT = new Vector(1, 0);

  public static final Vector UP_LEFT = new Vector(-1, 1);
  public static final Vector UP_RIGHT = new Vector(1, 1);
  public static final Vector DOWN_LEFT = new Vector(-1, -1);
  public static final Vector DOWN_RIGHT = new Vector(1, -1);

  public final int x;
  public final int y;

  public Vector(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public Vector plus(Vector v) {
    return new Vector(x + v.x, y + v.y);
  }

  public Vector times(int scalar) {
    return new Vector(x * scalar, y * scalar);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || getClass() != obj.getClass()) return false;
    Vector other = (Vector) obj;
    return x == other.x && y == other.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
