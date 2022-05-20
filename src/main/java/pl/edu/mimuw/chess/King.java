package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.Vector.*;
import static pl.edu.mimuw.chess.Vector.DOWN_RIGHT;

public class King extends ChessPiece {

  public King(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♔', '♚');
  }

  @Override
  public boolean isKing() {
    return true;
  }

  @Override
  protected List<List<Vector>> generateAllMoves() {
    final var directions = new ArrayList<List<Vector>>();

    for (var direction : new Vector[]{UP, UP_RIGHT, RIGHT, DOWN_RIGHT, DOWN, DOWN_LEFT, LEFT, UP_LEFT}) {
      final var thisDirection = new ArrayList<Vector>();
      thisDirection.add(direction);
      directions.add(thisDirection);
    }

    return directions;
  }
}
