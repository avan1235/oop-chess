package pl.edu.mimuw.chess;

import java.util.Objects;

import static java.lang.Character.*;

public final class Position {
  public final int row;
  public final int column;
  private final int radix = 32;
  private final int a_NUM_VAL = digit('a', radix);
  private final int h_NUM_VAL = digit('h', radix);
  public String pos;

  public Position(String pos) {
    if (pos.length() != 2)
      throw new IllegalArgumentException();

    this.row = parseRow(pos);
    this.column = parseColumn(pos);
    this.pos = pos;
  }

  public Position(int row, int column) {
    this.pos = Character.toString(Character.forDigit(a_NUM_VAL + column, radix)) + (row + 1);
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

  private int parseColumn(String pos) {
    char column = pos.charAt(0);
    if (!isAlphabetic(column) || getNumericValue(column) > h_NUM_VAL)
      throw new IllegalArgumentException();
    return getNumericValue(pos.charAt(0)) - a_NUM_VAL;
  }

  private int parseRow(String pos) {
    char row = pos.charAt(1);
    int rowVal = digit(row, 10);
    if (!isDigit(row) || rowVal > 8)
      throw new IllegalArgumentException();
    return rowVal - 1;
  }

  @Override
  public String toString() {
    return this.pos;
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
