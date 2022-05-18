package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class KnightPiece extends Piece {
  public KnightPiece(Piece.Color color, V2 position) {
    super(color, position);
  }

  @Override
  public List<V2> getAvailableMoves(Function<V2, ChessBoard.FieldState> getFieldState) {
    List<V2> result = new ArrayList<>();
    for (int x = -2; x <= 2; x++) {
      for (int y = -2; y <= 2; y++) {
        if (Math.abs(x) + Math.abs(y) != 3) continue;
        V2 target = position.plus(x, y);
        if (target.isInBoard() && getFieldState.apply(target) != ChessBoard.FieldState.ALLY_PIECE) result.add(target);
      }
    }
    return result;
  }

  @Override
  public String toString() {
    return this.color.equals(Color.WHITE) ? "♞" : "♘";
  }
}
