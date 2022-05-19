package pl.edu.mimuw.chess;

public class Knight extends ChessPiece {
  public Knight(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♘', '♞');
  }
}
