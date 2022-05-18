package pl.edu.mimuw.chess;

import java.util.Set;

public class King extends Piece {

  King(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> generatePossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2654";
  }

  protected String blackIcon() {
    return "\u265A";
  }
}