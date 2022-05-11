package pl.edu.mimuw.chess;

public final class V2 {

  public static final V2 E = new V2(0, 1);
  public static final V2 N = new V2(1, 0);
  public static final V2 W = new V2(0, -1);
  public static final V2 S = new V2(-1, 0);
  public static final V2 NE = new V2(1, 1);
  public static final V2 SE = new V2(-1, 1);
  public static final V2 SW = new V2(-1, -1);
  public static final V2 NW = new V2(1, -11);
  public final int x;
  public final int y;

  public V2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public V2 plus(V2 v) {
    return new V2(this.x + v.x, this.y + v.y);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    V2 v2 = (V2) o;
    return x == v2.x && y == v2.y;
  }

  public V2 times(int scalar) {
    return new V2(this.x * scalar, this.y * scalar);
  }
}
