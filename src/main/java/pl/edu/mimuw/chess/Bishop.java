package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.ChessBoard.BOARD_SIZE;
import static pl.edu.mimuw.chess.Vector.*;

public class Bishop extends ChessPiece {

  public Bishop(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♗', '♝');
  }

  @Override
  protected List<List<Vector>> generateAllMoves() {
    final var directions = new ArrayList<List<Vector>>();

    for (var direction : new Vector[]{UP_LEFT, UP_RIGHT, DOWN_LEFT, DOWN_RIGHT}) {
      final var thisDirection = new ArrayList<Vector>();
      for (int i = 1; i < BOARD_SIZE; i++) thisDirection.add(direction.times(i));
      directions.add(thisDirection);
    }

    return directions;
  }
}
