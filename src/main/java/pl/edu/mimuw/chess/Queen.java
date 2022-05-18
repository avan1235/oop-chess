package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public class Queen extends Piece {

  Queen(Position pos, Player owner, Board board) {
    super(pos, owner, board);
  }

  public Set<Position> generatePossibleMoves() {
    Set<Position> res = new HashSet<>();
    for (int i : new int[] {-1, 1}) {
      tryToAddMoves(res, i, i);
      tryToAddMoves(res, i, -i);
      tryToAddMoves(res, i, 0);
      tryToAddMoves(res, 0, i);
    }
    return res;
  }

  private void tryToAddMoves(Set<Position> moves, int rowDirection, int columnDirection) {
    Position toMove;
    for (int i = 1; i < Board.size; i++) {
      toMove = Position.move(this.pos(), rowDirection * i, columnDirection * i);
      if (toMove == null) break;
      if (board.isFree(toMove))
        moves.add(toMove);
      else if (this.isEnemyHere(toMove)) {
        moves.add(toMove);
        break;
      }
      else break;
    }
  }

  protected String whiteIcon() {
    return "\u2655";
  }

  protected String blackIcon() {
    return "\u265B";
  }
}
