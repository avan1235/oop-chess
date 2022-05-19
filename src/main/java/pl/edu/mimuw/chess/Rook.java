package pl.edu.mimuw.chess;

public class Rook extends ChessPiece {
  public Rook(ChessColor color, int x, int y) {
    super(color, new Vector(x, y), '♖', '♜');
  }
}
