package pl.edu.mimuw.chess;

import java.util.Set;

public class Queen extends Piece {

  public Queen(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> genPossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2655";
  }

  protected String blackIcon() {
    return "\u265B";
  }
}
