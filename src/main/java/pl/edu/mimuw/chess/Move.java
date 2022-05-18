package pl.edu.mimuw.chess;

public class Move {
  public final ChessPiece piece;
  public final V2 dest;

  public Move(ChessPiece piece, V2 dest){
    this.piece = piece;
    this.dest = dest;
  }
}
