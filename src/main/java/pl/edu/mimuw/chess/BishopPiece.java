package pl.edu.mimuw.chess;

import java.util.Set;

public class BishopPiece extends PieceAbstract {
  public BishopPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    return getDiagonalMoves();
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
