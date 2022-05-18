package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Pawn extends Piece {

  Pawn(Position pos, Player owner, Board board) {
    super(pos, owner, board, "\u2659", "\u265F");
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> res = new ArrayList<>();

    Position toMove;
    for (int i = 1; i <= (this.wasMoved ? 1 : 2); i++) {
      toMove = Position.moveFrom(this.pos(), orientation() * i, 0);
      if (toMove != null) {
        if (board.isFree(toMove))
          res.add(toMove);
        else
          break;
      }
    }

    Position toAttack;
    for (int i : new int[]{-1, 1}) {
      toAttack = Position.moveFrom(this.pos(), orientation(), i);
      if (toAttack != null && this.isEnemyOnPos(toAttack))
        res.add(toAttack);
    }

    return res;
  }

  private int orientation() {
    return this.isWhite ? 1 : -1;
  }
}
