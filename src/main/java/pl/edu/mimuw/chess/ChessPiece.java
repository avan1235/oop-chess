package pl.edu.mimuw.chess;

import java.util.List;
import java.util.Set;

public interface ChessPiece {
  List<V2> getPossibleMoves();
  V2 getPosition();
   void setPosition(V2 p);
  String representation();
  ChessColor getColor();

}
