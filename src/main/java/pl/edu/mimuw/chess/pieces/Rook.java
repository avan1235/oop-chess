package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.enums.PieceType;
import pl.edu.mimuw.chess.board.Position;

import java.util.ArrayList;

public class Rook extends DiagRowColPiece {
  public Rook(int row, int column, Colour colour) {
    super(row, column, PieceType.ROOK, colour);
  }

  public Rook(Position position, Colour colour) {
    super(position, PieceType.ROOK, colour);
  }

  @Override
  public ArrayList<Position> genMoves(IBoard board) {
    return this.diagRowColMoves(board);
  }

  @Override
  public String toString() {
    if (this.colour == Colour.BLACK) {
      return "♜";
    }
    else if (this.colour == Colour.WHITE) {
      return "♖";
    }
    else {
      throw new RuntimeException("No colour for piece");
    }
  }
}
