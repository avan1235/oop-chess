package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class PawnPiece extends Piece {
  public PawnPiece(Piece.Color color, V2 position) {
    super(color, position);
  }

  @Override
  public List<V2> getAvailableMoves(Function<V2, ChessBoard.FieldState> getFieldState) {
    List<V2> result = new ArrayList<>();
    int rotationScalar = color == Color.WHITE ? 1 : -1;

    V2 target = position.plus(V2.N.times(rotationScalar));
    if (target.isInBoard() && getFieldState.apply(target) == ChessBoard.FieldState.EMPTY) {
      result.add(target);
      target = position.plus(V2.N.times(2).times(rotationScalar));
      if (color == Color.WHITE && position.y == 1 && getFieldState.apply(target) == ChessBoard.FieldState.EMPTY)
        result.add(target);
      if (color == Color.BLACK && position.y == 6 && getFieldState.apply(target) == ChessBoard.FieldState.EMPTY)
        result.add(target);
    }
    target = position.plus(V2.NE.times(rotationScalar));
    if (target.isInBoard() && getFieldState.apply(target) == ChessBoard.FieldState.ENEMY_PIECE) result.add(target);
    target = position.plus(V2.NW.times(rotationScalar));
    if (target.isInBoard() && getFieldState.apply(target) == ChessBoard.FieldState.ENEMY_PIECE) result.add(target);

    return result;
  }

  @Override
  public String toString() {
    return this.color.equals(Color.WHITE) ? "♟" : "♙";
  }
}
