package pl.edu.mimuw.chess.board;

import pl.edu.mimuw.chess.enums.Direction;

public class Position {
  int row;
  int column;

  public Position(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public Position(Position position) {
    this.row = position.getRow();
    this.column = position.getColumn();
  }

  public int getRow() {
    return row;
  }

  public int getColumn() {
    return column;
  }

  public void setPosition(int row, int column) {
    this.row = row;
    this.column = column;
  }

  public void setPosition(Position position) {
    this.row = position.getRow();
    this.column = position.getColumn();
  }

  public Position stepIntoDirection(Direction direction) {
    switch (direction) {
      case E -> {return new Position(row, column + 1);}
      case N -> {return new Position(row + 1, column);}
      case S -> {return new Position(row - 1, column);}
      case W -> {return new Position(row, column - 1);}
      case NE -> {return new Position(row + 1, column + 1);}
      case NW -> {return new Position(row + 1, column - 1);}
      case SE -> {return new Position(row - 1, column + 1);}
      case SW -> {return new Position(row - 1, column - 1);}
    }
    throw new RuntimeException("Case not implemented");
  }
}
