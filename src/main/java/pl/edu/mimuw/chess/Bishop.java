package pl.edu.mimuw.chess;

public class Bishop extends ChessPiece {
  public Bishop(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♗', '♝');
  }
}
