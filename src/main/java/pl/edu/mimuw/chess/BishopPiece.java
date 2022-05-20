package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public final class BishopPiece extends AbstractPiece {

  private static final List<List<Square>> POSSIBLE_MOVES = generatePossibleMoves();

  private static List<List<Square>> generatePossibleMoves() {
    List<List<Square>> result = new ArrayList<>();
    for (final var d : new Square[] { NW, SW, NE, SE }) {
      List<Square> inDirection = new ArrayList<>();
      for (int i = 1; i < ChessBoard.boardSize; i++) inDirection.add(
        d.times(i)
      );
      result.add(inDirection);
    }
    return result;
  }

  public BishopPiece(Square position, PieceColor color) {
    super(position, color, "♝", "♗");
  }

  @Override
  public List<List<Square>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }
}
