package pl.edu.mimuw.chess;

import java.util.Objects;

public final class XY {
  public final int x;
  public final int y;

  public XY(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public XY plus(XY v) {
    return new XY(x + v.x, y + v.y);
  }

  public XY times(int c) {
    return new XY(x * c, y * c);
  }

  public boolean le(XY v) {
    return x <= v.x && y <= v.y;
  }

  public boolean lt(XY v) {
    return x < v.x && y < v.y;
  }

  public XY rotateBy90Degrees(int nTimes) {
    XY v = this;
    for (var i = 0; i < nTimes % 4; ++i)
      v = v.rotateBy90Degrees();
    return v;
  }

  public XY rotateBy90Degrees() {
    return new XY(-y, x);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    XY v2 = (XY) o;
    return x == v2.x && y == v2.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
