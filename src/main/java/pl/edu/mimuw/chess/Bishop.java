package pl.edu.mimuw.chess;

import java.util.Set;

public class Bishop extends Piece {

  public Bishop(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> genPossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2657";
  }

  protected String blackIcon() {
    return "\u265D";
  }
}
