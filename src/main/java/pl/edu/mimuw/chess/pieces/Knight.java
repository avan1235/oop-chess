package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.board.Position;
import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.enums.Direction;
import pl.edu.mimuw.chess.enums.PieceType;

import java.util.ArrayList;
import java.util.EnumSet;

public class Knight extends AbstractPiece {
  public Knight(int row, int column, Colour colour) {
    super(row, column, PieceType.KNIGHT, colour);
  }

  public Knight(Position position, Colour colour) {
    super(position, PieceType.KNIGHT, colour);
  }

  @Override
  public ArrayList<Position> genMoves(IBoard board) {
    ArrayList<Position> newPositions = new ArrayList<>();

    EnumSet<Direction> FirstDirections = EnumSet.of(Direction.N, Direction.S);
    EnumSet<Direction> SecondDirections = EnumSet.of(Direction.E, Direction.W);

    for (int i = 0; i < 2; i++) {
      for (var dir1 : FirstDirections) {
        var newPos = this.position.stepIntoDirection(dir1).stepIntoDirection(dir1);
        for (var dir2 : SecondDirections) {
          var finalPos = newPos.stepIntoDirection(dir2);
          if (board.insideBoard(finalPos)) {
            if (board.isOccupiedBy(finalPos) != this.colour) {
              newPositions.add(finalPos);
            }
          }
        }
      }
      var tmp = FirstDirections;
      FirstDirections = SecondDirections;
      SecondDirections = tmp;
    }

    return newPositions;
  }

  @Override
  public String toString() {
    if (this.colour == Colour.BLACK) {
      return "♞";
    }
    else if (this.colour == Colour.WHITE) {
      return "♘";
    }
    else {
      throw new RuntimeException("No colour for piece");
    }
  }
}
