package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.board.Position;
import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.enums.Direction;
import pl.edu.mimuw.chess.enums.PieceType;

import java.util.ArrayList;

public class Pawn extends AbstractPiece {

  public Pawn(int row, int column, Colour colour) {
    super(row, column, PieceType.PAWN, colour);
  }

  public Pawn(Position position, Colour colour) {
    super(position, PieceType.PAWN, colour);
  }

  public ArrayList<Position> genMoves(IBoard board) {
    Colour oppositeColour = this.colour.oppose();
    ArrayList<Position> newPositions = new ArrayList<>();

    Direction center = Direction.N;
    Direction left = Direction.NW;
    Direction right = Direction.NE;

    if (this.colour == Colour.BLACK) {
      center = center.flip();
      left = left.flip();
      right = right.flip();
    }

    Position tempPosition;
    tempPosition = this.position.stepIntoDirection(center);
    if (board.insideBoard(tempPosition) && !board.isOccupied(tempPosition)) {
      newPositions.add(tempPosition);
    }

    tempPosition = this.position.stepIntoDirection(left);
    if (board.insideBoard(tempPosition) && board.isOccupiedBy(tempPosition) == oppositeColour) {
      newPositions.add(tempPosition);
    }

    tempPosition = this.position.stepIntoDirection(right);
    if (board.insideBoard(tempPosition) && board.isOccupiedBy(tempPosition) == oppositeColour) {
      newPositions.add(tempPosition);
    }

    return newPositions;
  }

  @Override
  public String toString() {
    if (this.colour == Colour.BLACK) {
      return "♟";
    }
    else if (this.colour == Colour.WHITE) {
      return "♙";
    }
    else {
      throw new RuntimeException("No colour for piece");
    }
  }
}
