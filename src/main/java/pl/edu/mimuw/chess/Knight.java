package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Knight extends ChessPiece {

  public Knight(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♘', '♞');
  }

  @Override
  protected List<List<Vector>> generateAllMoves() {
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
