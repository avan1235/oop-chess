package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public final class PawnPiece extends AbstractPiece {

  private final List<List<Square>> POSSIBLE_MOVES;

  public PawnPiece(Square position, PieceColor color) {
    super(position, color, "♟", "♙");
    Square d;
    if (color == PieceColor.WHITE) {
      d = N;
    } else {
      d = S;
    }
    List<List<Square>> result = new ArrayList<>();
    List<Square> inDirection = new ArrayList<>();
    inDirection.add(d);
    result.add(inDirection);
    POSSIBLE_MOVES = result;
  }

  @Override
  public List<List<Square>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }
}
