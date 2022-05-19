package pl.edu.mimuw.chess;

public class Queen extends ChessPiece {
  public Queen(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♕', '♛');
  }
}
