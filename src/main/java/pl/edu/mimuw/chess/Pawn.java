package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public class Pawn extends Piece {

  public Pawn(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> genPossibleMoves() {
    Set<Position> res = new HashSet<>();
    Position toMove;
    for (int i : new int[]{1, 2}) {
      toMove = Position.move(this.pos(), orientation() * i, 0);
      if (toMove != null) {
        if (board.isFree(toMove))
          res.add(toMove);
        else if (this.isEnemyHere(toMove)) {
          res.add(toMove);
          break;
        }
      }
    }
    this.possibleMoves = res;
    return res;
  }

  protected String whiteIcon() {
    return "\u2659";
  }

  protected String blackIcon() {
    return "\u265F";
  }
}
