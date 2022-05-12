package pl.edu.mimuw.chess;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class QueenPiece extends PieceAbstract {
  public QueenPiece(Board board, Player owner, XY position) {
    super(board, owner, position);
  }

  @Override
  public Set<Move> getMoves() {
    return Stream.concat(getCrosswiseMoves().stream(), getDiagonalMoves().stream()).collect(Collectors.toSet());
  }

  @Override
  public String getRepresentationIfWhite() {
    return "♕";
  }

  @Override
  public String getRepresentationIfBlack() {
    return "♛";
  }
}
