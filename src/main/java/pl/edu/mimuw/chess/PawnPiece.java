package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class PawnPiece extends AbstractPiece {

  private static final List<List<V2>> POSSIBLE_MOVES = generatePossibleMoves();

  public PawnPiece(V2 position, ChessColor color) {
    super(position, color, "♙", "♟");
  }

  @Override
  public List<List<V2>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }

  private static List<List<V2>> generatePossibleMoves() {
    List<List<V2>> result = new ArrayList<>();
    for (final var d : new V2[]{N, S}) {
      List<V2> inDirection = new ArrayList<>();
      inDirection.add(d);
      result.add(inDirection);
    }
    return result;
  }

}