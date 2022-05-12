package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public class KnightPiece extends PieceAbstract {
  public KnightPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    var moves = new HashSet<Move>();
    for (var r = 0; r < 4; ++r) {
      for (var vector : new XY[]{new XY(1, 2), new XY(2, 1)}) {
        var p = position.plus(vector.rotateBy90Degrees(r));
        if (canLand(p)) moves.add(new Move(this, p));
      }
    }
    return moves;
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♘";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♞";
  }
}
