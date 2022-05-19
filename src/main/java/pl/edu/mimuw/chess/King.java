package pl.edu.mimuw.chess;

public class King extends ChessPiece {
  public King(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♔', '♚');
  }
}
