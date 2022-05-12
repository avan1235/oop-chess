package pl.edu.mimuw.chess;

import java.util.Set;

public interface Piece {
  XY getPosition();

  Player getOwner();

  Set<Move> getMoves();

  void doMove(XY newPosition) throws GameEnded;

  void beCaptured(Piece byWhom) throws GameEnded;

  String getRepresentation();
}
