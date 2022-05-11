package pl.edu.mimuw.chess;

import java.util.Objects;

public final class V2 {
  public final int x;
  public final int y;

  public V2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public V2 plus(V2 v) {
    return new V2(x + v.x, y + v.y);
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
