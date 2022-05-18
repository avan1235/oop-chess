package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Rook extends Piece {

  Rook(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> res = new ArrayList<>();
    for (int i : new int[]{-1, 1}) {
      addLinearMoves(res, i, 0, Board.size);
      addLinearMoves(res, 0, i, Board.size);
    }
    return res;
  }

  protected String whiteIcon() {
    return "\u2656";
  }

  protected String blackIcon() {
    return "\u265C";
  }
}
