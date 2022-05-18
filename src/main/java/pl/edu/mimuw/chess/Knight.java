package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Knight extends Piece {

  Knight(Position pos, Player owner, Board board) {
    super(pos, owner, board, "\u2658", "\u265E");
  }

  public ArrayList<Position> generatePossibleMoves() {
    ArrayList<Position> moves = new ArrayList<>();
    Position toMove;
    for (int i = 0; i < 2; i++) {
      for (int j : new int[]{-1, 1}) {
        for (int k : new int[]{-1, 1}) {
          toMove = Position.moveFrom(this.pos(), (2 - i) * j, (i + 1) * k);
          if (toMove != null && (board.isFree(toMove) || this.isEnemyOnPos(toMove)))
            moves.add(toMove);
        }
      }
    }
    return moves;
  }
}
