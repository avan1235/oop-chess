package pl.edu.mimuw.chess;

import java.util.List;
import java.util.Random;

public class ChessPlayer {

  private final PieceColor color;

  private static Random RANDOM = new Random();

  public PieceColor getColor() {
    return color;
  }

  public Move makeAMove(List<Move> possibleMoves) {
    if (possibleMoves.isEmpty()) {
      return null;
    }
    return possibleMoves.get(RANDOM.nextInt(possibleMoves.size()));
  }

  public ChessPlayer(PieceColor color) {
    this.color = color;
  }
}
