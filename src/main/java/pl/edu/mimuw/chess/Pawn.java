package pl.edu.mimuw.chess;

public class Pawn extends ChessPiece {
  public Pawn(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♙', '♟');
  }
}
