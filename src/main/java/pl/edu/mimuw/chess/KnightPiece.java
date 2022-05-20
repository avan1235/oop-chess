package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public final class KnightPiece extends AbstractPiece {

  private static final List<List<Square>> POSSIBLE_MOVES = generatePossibleMoves();

  private static List<List<Square>> generatePossibleMoves() {
    List<List<Square>> result = new ArrayList<>();
    for (final var d : new Square[] {
      NW.plus(N),
      NW.plus(W),
      NE.plus(N),
      NE.plus(E),
      SW.plus(S),
      SW.plus(W),
      SE.plus(S),
      SE.plus(E),
    }) {
      List<Square> inDirection = new ArrayList<>();
      inDirection.add(d);
      result.add(inDirection);
    }
    return result;
  }

  public KnightPiece(Square position, PieceColor color) {
    super(position, color, "♞", "♘");
  }

  @Override
  public List<List<Square>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }
}
