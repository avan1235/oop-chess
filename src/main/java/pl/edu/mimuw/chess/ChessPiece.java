package pl.edu.mimuw.chess;

import java.util.List;

public interface ChessPiece {

  void take();
  ChessColor getColor();
  void setPosition(V2 v);
  V2 getPosition();

  List<Move> getPossibleMoves();

  String representation();

  String toString();

}
