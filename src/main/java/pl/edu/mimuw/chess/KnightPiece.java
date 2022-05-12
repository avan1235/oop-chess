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
      var p1 = position.plus((new XY(1, 2)).rotateBy90Degrees(r));
      if (canLand(p1)) moves.add(new Move(this, p1));
      var p2 = position.plus((new XY(2, 1)).rotateBy90Degrees(r));
      if (canLand(p2)) moves.add(new Move(this, p1));
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
