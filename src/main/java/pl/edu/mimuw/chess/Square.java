package pl.edu.mimuw.chess;

public final class Square {

  public final int x;
  public final int y;

  public Square(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean isSquareInChessBoard() {
    return ((1 <= x) && (x <= 8) && (1 <= y) && (y <= 8));
  }

  public Square plus(Square v) {
    return new Square(this.x + v.x, this.y + v.y);
  }

  public Square times(int scale) {
    return new Square(this.x * scale, this.y * scale);
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    Square other = (Square) obj;
    return (x == other.x) && (y == other.y);
  }

  @Override
  public String toString() {
    return "abcdefgh".substring(x - 1, x) + y;
  }
}
