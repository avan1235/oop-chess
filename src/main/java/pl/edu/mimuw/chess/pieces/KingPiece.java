package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KingPiece extends Piece {
  public KingPiece(Piece.Color color, V2 position) {
    super(color, position);
  }

  @Override
  public List<V2> getAvailableMoves(Function<V2, ChessBoard.FieldState> getFieldState) {
    List<V2> result = new ArrayList<>();
    for (var direction : new V2[]{V2.N, V2.NE, V2.NW, V2.E, V2.W, V2.SE, V2.SW, V2.S}) {
      V2 target = position.plus(direction);
      if (target.isInBoard() && getFieldState.apply(target) != ChessBoard.FieldState.ALLY_PIECE) result.add(target);
    }
    return result;
  }

  @Override
  public String toString() {
    return this.color.equals(Color.WHITE) ? "♚" : "♔";
  }
}
