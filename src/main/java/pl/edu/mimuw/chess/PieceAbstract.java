package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public abstract class PieceAbstract implements Piece {

  protected final Board board;
  protected final Player owner;
  protected XY position;

  public PieceAbstract(Board board, Player owner, XY position) {
    this.board = board;
    this.owner = owner;
    this.position = position;

    board.putPiece(this);
  }

  public XY getPosition() {
    return position;
  }

  public Player getOwner() {
    return owner;
  }

  // Checks if the move would result in the piece leaving the board, or landing
  // on top of a piece that cannot be captured.
  protected boolean canLand(XY move) {
    if (!((new XY(0, 0)).le(move) && move.lt(board.getDimensions())))
      return false;
    if (board.getPieceAt(move) == null) return true;
    return board.getPieceAt(move).getOwner() != owner;
  }

  private Set<Move> getMovesFromVector(XY vector) {
    var moves = new HashSet<Move>();
    for (var r = 0; r < 4; ++r) {
      for (var i = 1; ; ++i) {
        var p = position.plus(vector.times(i).rotateBy90Degrees(r));
        if (!canLand(p)) break;
        moves.add(new Move(this, p));
        if (board.getPieceAt(p) != null) break;
      }
    }
    return moves;
  }

  protected Set<Move> getCrosswiseMoves() {
    return getMovesFromVector(new XY(0, owner.getForward()));
  }

  protected Set<Move> getDiagonalMoves() {
    return getMovesFromVector(new XY(owner.getForward(), owner.getForward()));
  }

  // All the moves the piece can make right now.
  public Set<Move> getMoves() {
    return new HashSet<>();
  }

  public void doMove(XY newPosition) throws GameEnded {
    assert canLand(newPosition);
    if (board.getPieceAt(newPosition) != null) {
      assert board.getPieceAt(newPosition).getOwner() != owner;
      board.getPieceAt(newPosition).beCaptured(this);
    }
    board.removePiece(this);
    position = newPosition;
    board.putPiece(this);
  }

  public void beCaptured(Piece byWhom) throws GameEnded {
    assert owner != byWhom.getOwner();
    board.removePiece(this);
  }

  protected abstract String getRepresentationIfWhite();

  protected abstract String getRepresentationIfBlack();

  public String getRepresentation() {
    return switch (owner.getColor()) {
      case WHITE -> getRepresentationIfWhite();
      case BLACK -> getRepresentationIfBlack();
    };
  }
}
