package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public class BishopPiece extends Piece {
  public BishopPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    var moves = new HashSet<Move>();

    // We can go diagonally in any direction but can't jump over anyone.
    for (var r = 0; r < 4; ++r) {
      for (var i = 1; ; ++i) {
        var p = position.plus((new XY(i * owner.getForward(), i * owner.getForward())).rotateBy90Degrees(r));
        if (!canLand(p)) break;
        moves.add(new Move(this, p));
        if (board.getPieceAt(p) != null) break;
      }
    }
    return moves;
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♗";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♝";
  }
}
