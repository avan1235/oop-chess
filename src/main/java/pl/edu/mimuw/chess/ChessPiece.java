package pl.edu.mimuw.chess;

import java.util.List;

public interface ChessPiece {

  V2 getPosition();

  void setPosition(V2 v);

  List<List<V2>> getPossibleMoves();

  List<List<V2>> getPossibleAttackMoves();

  String representation();
}
