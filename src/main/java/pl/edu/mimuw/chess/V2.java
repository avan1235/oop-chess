package pl.edu.mimuw.chess;

import java.util.Objects;

public class V2 {
  public static final V2 N = new V2(0, 1);
  public static final V2 S = new V2(0, -1);
  public static final V2 E = new V2(1, 0);
  public static final V2 W = new V2(-1, 0);
  public static final V2 NE = new V2(1, 1);
  public static final V2 NW = new V2(-1, 1);
  public static final V2 SE = new V2(1, -1);
  public static final V2 SW = new V2(-1, -1);
  public final int x;
  public final int y;

  public V2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public V2 plus(V2 other) {
    return new V2(x + other.x, y + other.y);
  }

  public V2 plus(int x, int y) {
    return new V2(this.x + x, this.y + y);
  }

  public V2 times(int scalar) {
    return new V2(x * scalar, y * scalar);
  }

  public boolean isInBoard() {
    return x < 8 && x >= 0 && y < 8 && y >= 0;
  }

  @Override
  public String toString() {
    return "V2{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    V2 v2 = (V2) o;
    return x == v2.x && y == v2.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
