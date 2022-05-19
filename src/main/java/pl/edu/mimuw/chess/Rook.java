package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.ChessBoard.BOARD_SIZE;
import static pl.edu.mimuw.chess.Vector.*;

public class Rook extends ChessPiece {
  private static final List<List<Vector>> ALL_MOVES = generateAllMoves();

  public Rook(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♖', '♜');
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
      result.add(thisDirection);
    }
    return result;
  }

  private static List<List<Vector>> generateAllMoves() {
    final var directions = new ArrayList<List<Vector>>();

    for (var direction : new Vector[] {UP, RIGHT, DOWN, LEFT}) {
      final var thisDirection = new ArrayList<Vector>();
      for (int i = 1; i < BOARD_SIZE; i++) thisDirection.add(direction.times(i));
      directions.add(thisDirection);
    }

    return directions;
  }
}
