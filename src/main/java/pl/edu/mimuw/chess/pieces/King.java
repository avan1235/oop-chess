package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.board.Position;
import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.enums.Direction;
import pl.edu.mimuw.chess.enums.PieceType;

import java.util.ArrayList;

public class King extends AbstractPiece {
  public King(int row, int column, Colour colour) {
    super(row, column, PieceType.KING, colour);
  }

  public King(Position position, Colour colour) {
    super(position, PieceType.KING, colour);
  }

  @Override
  public ArrayList<Position> genMoves(IBoard board) {
    ArrayList<Position> newPositions = new ArrayList<>();

    Position newPos;

    for (var dir: Direction.values()) {
      newPos = this.position.stepIntoDirection(dir);
      if (board.insideBoard(newPos)) {
        if (board.isOccupiedBy(newPos) != this.colour) {
          newPositions.add(newPos);
        }
      }
    }

    return newPositions;
  }

  @Override
  public String toString() {
    if (this.colour == Colour.BLACK) {
      return "♚";
    }
    else if (this.colour == Colour.WHITE) {
      return "♔";
    }
    else {
      throw new RuntimeException("No colour for piece");
    }
  }
}
