package pl.edu.mimuw.chess;

import java.util.Set;

public class Rook extends Piece {

  public Rook(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> genPossibleMoves() {
    return null;
  }

  protected String whiteIcon() {
    return "\u2656";
  }

  protected String blackIcon() {
    return "\u265C";
  }
}
