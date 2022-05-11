package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public final class PawnPiece extends Piece {

  private boolean isFirstMove;

  public PawnPiece(Board board, Player owner, XY position) {
    super(board, owner, position);

    isFirstMove = true;
  }

  @Override
  public Set<Move> getMoves() {
    var moves = new HashSet<Move>();

    // We can move forward one (or sometimes two) fields.
    for (var i = 1; i <= 2; ++i) {
      var p = position.plus(new XY(0, owner.getForward() * i));
      if (!canLand(p)) break;
      if (board.getPieceAt(p) != null) break;
      moves.add(new Move(this, p));
    }
    // We can capture diagonally.
    for (var d = -1; d <= 1; d += 2) {
      var p = position.plus(new XY(d, owner.getForward()));
      if (!canLand(p)) continue;
      if (board.getPieceAt(p) == null) continue;
      if (board.getPieceAt(p).getOwner() == owner) continue;
      moves.add(new Move(this, p));
    }

    return moves;
  }

  @Override
  public void doMove(XY newPosition) throws GameEnded {
    super.doMove(newPosition);
    isFirstMove = false;
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♙";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♟";
  }
}
