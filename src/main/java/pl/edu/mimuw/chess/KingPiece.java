package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public class KingPiece extends AbstractPiece {


  public KingPiece(ChessBoard board, V2 position, ChessColor color) {
    super(board, position, color);
    if (color == ChessColor.WHITE)
      this.representation = "♔";
    else
      this.representation = "♚";
  }

  @Override
  public List<Move> getPossibleMoves() {
    List<Move> result = new ArrayList<>();
    for (final var d : new V2[]{N, NE, E, SE, S, SW, W, NW}) {
      V2 dest = getPosition().plus(d);
      ChessPiece target = board.getPieceOnTile(dest);
      if ((target == null || target.getColor()!= this.color) && board.isOnBoard(dest))
        result.add(new Move(this, dest));
    }
    return result;
  }
}
