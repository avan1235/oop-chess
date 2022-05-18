package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public abstract class Piece {
  public final Color color;
  protected V2 position;

  protected Piece(Color color, V2 position) {
    this.color = color;
    this.position = position;
  }

  public abstract List<V2> getAvailableMoves(Function<V2, ChessBoard.FieldState> getFieldState);

  protected List<V2> getMovesInDirections(Function<V2, ChessBoard.FieldState> getFieldState, V2[] directions) {
    List<V2> result = new ArrayList<>();
    for (var direction : directions) {
      V2 target = position.plus(direction);
      while (target.isInBoard() && getFieldState.apply(target) == ChessBoard.FieldState.EMPTY) {
        result.add(target);
        target = target.plus(direction);
      }
      if (target.isInBoard() && getFieldState.apply(target) == ChessBoard.FieldState.ENEMY_PIECE)
        result.add(target);
    }
    return result;
  }

  public V2 getPosition() {
    return position;
  }

  public void moveTo(V2 position) {
    this.position = position;
  }

  public enum Color {WHITE, BLACK}
}
