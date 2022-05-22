package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class KnightPiece extends AbstractPiece {

  private static final List<List<V2>> POSSIBLE_MOVES = generatePossibleMoves();

  public KnightPiece(V2 position, ChessColor color) {
    super(position, color, "♘", "♞");
  }

  @Override
  public List<List<V2>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }

  private static List<List<V2>> generatePossibleMoves() {
    List<List<V2>> result = new ArrayList<>();
    for (final var d1 : new V2[]{N, S}) {
      for (final var d2 : new V2[]{E, W}) {
        List<V2> inDirection = new ArrayList<>();
        inDirection.add(d1.plus(d1.plus(d2)));
        result.add(inDirection);
        inDirection = new ArrayList<>();
        inDirection.add(d1.plus(d2.plus(d2)));
        result.add(inDirection);
      }
    }
    return result;
  }
}
