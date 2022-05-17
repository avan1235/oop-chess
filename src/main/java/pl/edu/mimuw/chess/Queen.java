package pl.edu.mimuw.chess;

import java.util.Set;

public class Queen extends Piece {

  Queen(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> generatePossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2655";
  }

  protected String blackIcon() {
    return "\u265B";
  }
}
