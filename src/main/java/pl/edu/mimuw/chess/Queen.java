package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Queen extends Piece {

  Queen(Position pos, Player owner, Board board) {
    super(pos, owner, board, "\u2655", "\u265B");
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> res = new ArrayList<>();
    for (int i : new int[]{-1, 1}) {
      addLinearMoves(res, i, i, Board.size);
      addLinearMoves(res, i, -i, Board.size);
      addLinearMoves(res, i, 0, Board.size);
      addLinearMoves(res, 0, i, Board.size);
    }
    return res;
  }
}
