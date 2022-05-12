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
