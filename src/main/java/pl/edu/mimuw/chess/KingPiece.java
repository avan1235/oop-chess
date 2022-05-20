package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public final class KingPiece extends AbstractPiece {

  private static final List<List<Square>> POSSIBLE_MOVES = generatePossibleMoves();

  private static List<List<Square>> generatePossibleMoves() {
    List<List<Square>> result = new ArrayList<>();
    for (final var d : new Square[] { NW, SW, NE, SE, N, W, S, E }) {
      List<Square> inDirection = new ArrayList<>();
      inDirection.add(d);
      result.add(inDirection);
    }
    return result;
  }

  public KingPiece(Square position, PieceColor color) {
    super(position, color, "♚", "♔");
  }

  @Override
  public List<List<Square>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }
}
