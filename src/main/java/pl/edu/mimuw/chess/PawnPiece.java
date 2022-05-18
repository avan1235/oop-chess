package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public class PawnPiece extends AbstractPiece {

  private boolean hasMoved;

  public PawnPiece(ChessBoard board, V2 position, ChessColor color) {
    super(board, position, color);
    if (color == ChessColor.WHITE)
      this.representation = "♙";
    else
      this.representation = "♟";
    hasMoved = false;
  }

  @Override
  public void setPosition(V2 v) {
    hasMoved = true;
    super.setPosition(v);
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> result = new ArrayList<>();
    V2 genDirection;
    if (color == ChessColor.BLACK)
      genDirection = S;
    else
      genDirection = N;
    if (board.getPieceOnTile(getPosition().plus(genDirection)) == null
      && board.isOnBoard(getPosition().plus(genDirection))) {
      result.add(new Move(this, getPosition().plus(genDirection)));
      if (board.getPieceOnTile(getPosition().plus(genDirection.times(2))) == null
        && !hasMoved)
        result.add(new Move(this, getPosition().plus(genDirection.times(2))));
    }
    for (V2 dest : new V2[]{
      getPosition().plus(genDirection.plus(W)),
      getPosition().plus(genDirection.plus(E))}) {
      ChessPiece target = board.getPieceOnTile(dest);
      if (target != null && target.getColor() != color)
        result.add(new Move(this, dest));
    }
    return result;
  }
}
