package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public final class RookPiece extends AbstractPiece {

  private static final List<List<Square>> possibleMoves = generatePossibleMoves();

  private static List<List<Square>> generatePossibleMoves() {
    List<List<Square>> result = new ArrayList<>();
    for (final var d : new Square[] { N, S, E, W }) {
      List<Square> inDirection = new ArrayList<>();
      for (int i = 1; i < ChessBoard.boardSize; i++) {
        inDirection.add(d.times(i));
      }
      result.add(inDirection);
    }
    return result;
  }

  public RookPiece(Square position, PieceColor color) {
    super(position, color, "♜", "♖");
  }

  @Override
  public List<List<Square>> getPossibleMoves() {
    return possibleMoves;
  }
}
