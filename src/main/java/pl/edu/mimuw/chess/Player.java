package pl.edu.mimuw.chess;

import java.util.List;
import java.util.Random;

public class Player {
  private static final Random RANDOM = new Random();

  private final ChessColor color;
  private final List<ChessPiece> pieces;

  public Player(ChessColor color, List<ChessPiece> pieces) {
    this.color = color;
    this.pieces = pieces;
  }

  public List<ChessPiece> getPieces() {
    return this.pieces;
  }

  public void makeRandomMove() {
    pieces.get(RANDOM.nextInt(1)).setPosition(new Vector(5, 5));
  }
}
