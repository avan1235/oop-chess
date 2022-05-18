package pl.edu.mimuw.chess;

import java.util.Set;

public class Knight extends Piece {

  Knight(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> generatePossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2658";
  }

  protected String blackIcon() {
    return "\u265E";
  }
}