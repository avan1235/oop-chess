package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public final class KingPiece extends Piece {

  public KingPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    var moves = new HashSet<Move>();
    for (var r = 0; r < 4; ++r) {
      for (var i = 0; i < 2; ++i) {
        var p = position.plus((new XY(i, 1)).rotateBy90Degrees(r));
        if (canLand(p)) moves.add(new Move(this, p));
      }
    }
    return moves;
  }

  @Override
  public void beCaptured(Piece byWhom) throws GameEnded {
    super.beCaptured(byWhom);

    byWhom.getOwner().winTheGame();
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♔";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♚";
  }
}
