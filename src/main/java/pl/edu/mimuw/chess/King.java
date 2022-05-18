package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class King extends Piece {

  King(Position pos, Player owner, Board board) {
    super(pos, owner, board, "\u2654", "\u265A");
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> res = new ArrayList<>();
    for (int i : new int[]{-1, 1}) {
      addLinearMoves(res, i, i, 1);
      addLinearMoves(res, i, -i, 1);
      addLinearMoves(res, i, 0, 1);
      addLinearMoves(res, 0, i, 1);
    }
    return res;
  }

  @Override
  public void becomeCaptured() {
    this.owner.losePiece(this);
    owner.loseGame();
  }
}
