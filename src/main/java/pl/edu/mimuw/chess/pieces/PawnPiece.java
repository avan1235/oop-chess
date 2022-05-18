package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.V2;

import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class PawnPiece extends AbstractPiece {
  public PawnPiece(V2 position, ChessColor color) {
    super(position, color, "♙", "♟");
  }

  @Override
  public List<List<V2>> getRelativeMoves() {
    if (this.color == ChessColor.WHITE) {
      return List.of(List.of(N));
    } else {
      return List.of(List.of(S));
    }
  }

  @Override
  public List<List<V2>> getRelativeAttackMoves() {
    if (this.color == ChessColor.WHITE) {
      return List.of(
        List.of(NE),
        List.of(NW)
      );
    } else {
      return List.of(
        List.of(SE),
        List.of(SW)
      );
    }
  }

}
