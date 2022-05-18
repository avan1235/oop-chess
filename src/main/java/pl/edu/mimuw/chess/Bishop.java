package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Bishop extends Piece {

  Bishop(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> res = new ArrayList<>();
    for (int i : new int[]{-1, 1}) {
      addLinearMoves(res, i, i, Board.size);
      addLinearMoves(res, i, -i, Board.size);
    }
    return res;
  }

  protected String whiteIcon() {
    return "\u2657";
  }

  protected String blackIcon() {
    return "\u265D";
  }
}
