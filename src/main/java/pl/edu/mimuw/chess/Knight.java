package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends ChessPiece {
  private static final List<List<Vector>> ALL_MOVES = generateAllMoves();

  public Knight(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♘', '♞');
  }

  public List<List<Vector>> getPossibleMoves(ChessBoard board) {
    final var result = new ArrayList<List<Vector>>();
    for (var direction : ALL_MOVES) {
      final var thisDirection = new ArrayList<Vector>();
      for (var move : direction) {
        final var newPosition = getPosition().plus(move);
        if (!board.isOnBoard(newPosition)) break;
        final var piece = board.getPiece(newPosition);
        if (piece == null) {
          thisDirection.add(move);
        } else if (piece.getColor() != getColor()) {
          thisDirection.add(move);
          break;
        } else {
          break;
        }
      }
      if (thisDirection.size() > 0) result.add(thisDirection);
    }
    return result;
  }

  private static List<List<Vector>> generateAllMoves() {
    final var directions = new ArrayList<List<Vector>>();

    directions.add(Collections.singletonList(new Vector(1, 2)));
    directions.add(Collections.singletonList(new Vector(2, 1)));
    directions.add(Collections.singletonList(new Vector(2, -1)));
    directions.add(Collections.singletonList(new Vector(1, -2)));
    directions.add(Collections.singletonList(new Vector(-1, -2)));
    directions.add(Collections.singletonList(new Vector(-2, -1)));
    directions.add(Collections.singletonList(new Vector(-2, 1)));
    directions.add(Collections.singletonList(new Vector(-1, 2)));

    return directions;
  }
}
