package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.V2;

import java.util.List;
import java.util.function.Function;

public class BishopPiece extends Piece {
  public BishopPiece(Piece.Color color, V2 position) {
    super(color, position);
  }

  @Override
  public List<V2> getAvailableMoves(Function<V2, ChessBoard.FieldState> getFieldState) {
    return getMovesInDirections(getFieldState, new V2[]{V2.NE, V2.NW, V2.SE, V2.SW});
  }

  @Override
  public String toString() {
    return this.color.equals(Color.WHITE) ? "♝" : "♗";
  }
}
