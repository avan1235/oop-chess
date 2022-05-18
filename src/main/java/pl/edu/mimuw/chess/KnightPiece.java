package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public class KnightPiece extends AbstractPiece {


  public KnightPiece(ChessBoard board, V2 position, ChessColor color) {
    super(board, position, color);
    if (color == ChessColor.WHITE)
      this.representation = "♘";
    else
      this.representation = "♞";
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> result = new ArrayList<>();
    for (final var d :
        new V2[]{
          N.plus(NE),
          E.plus(NE),
          E.plus(SE),
          S.plus(SE),
          S.plus(SW),
          W.plus(SW),
          W.plus(NW),
          N.plus(NW)}) {
      V2 dest = getPosition().plus(d);
      ChessPiece target = board.getPieceOnTile(dest);
      if ((target == null || target.getColor() != this.color) && board.isOnBoard(dest))
        result.add(new Move(this, dest));
    }
    return result;
  }
}
