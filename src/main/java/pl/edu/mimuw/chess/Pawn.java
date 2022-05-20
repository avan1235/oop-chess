package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.Vector.*;

public class Pawn extends ChessPiece {

  public Pawn(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♙', '♟');
  }

  @Override
  protected List<List<Vector>> generateAllMoves() {
    final var directions = new ArrayList<List<Vector>>();

    for (var direction : new Vector[]{UP, DOWN}) {
      final var thisDirection = new ArrayList<Vector>();
      thisDirection.add(direction);
      directions.add(thisDirection);
    }

    return directions;
  }
}
