package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.enums.PieceType;
import pl.edu.mimuw.chess.board.Position;

import java.util.ArrayList;

public class Bishop extends DiagRowColPiece {
  public Bishop(int row, int column, Colour colour) {
    super(row, column, PieceType.BISHOP, colour);
  }

  public Bishop(Position position, Colour colour) {
    super(position, PieceType.BISHOP, colour);
  }

  @Override
  public ArrayList<Position> genMoves(IBoard board) {
    return this.diagonalMoves(board);
  }

  @Override
  public String toString() {
    if (this.colour == Colour.BLACK) {
      return "♝";
    }
    else if (this.colour == Colour.WHITE) {
      return "♗";
    }
    else {
      throw new RuntimeException("No colour for piece");
    }
  }
}
