package pl.edu.mimuw.chess;

import java.util.Set;

public class Bishop extends Piece {

  Bishop(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> generatePossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2657";
  }

  protected String blackIcon() {
    return "\u265D";
  }
}
