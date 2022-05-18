package pl.edu.mimuw.chess;

import java.util.Objects;

import static java.lang.Character.digit;

public final class Position {
  public final int row;
  public final int column;
  private final int radix = 32;
  private final int a_NUM_VAL = digit('a', radix);

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public static boolean isValidPos(int row, int column) {
    return row >= 0 && column >= 0 && row < Board.size && column < Board.size;
  }

  public static Position moveFrom(Position pos, int rowsUp, int columnsRight) {
    if (!isValidPos(pos.row + rowsUp, pos.column + columnsRight)) return null;
    return new Position(pos.row + rowsUp, pos.column + columnsRight);
  }

  @Override
  public String toString() {
    return Character.toString(Character.forDigit(a_NUM_VAL + column, radix)) + (row + 1);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Position position = (Position) o;
    return this.row == position.row && this.column == position.column;
  }

  @Override
  public int hashCode() {
    return Objects.hash(row, column);
  }
}
