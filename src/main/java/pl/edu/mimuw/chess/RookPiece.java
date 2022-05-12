package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public class RookPiece extends PieceAbstract {
  public RookPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    return getCrosswiseMoves();
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♖";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♜";
  }
}
