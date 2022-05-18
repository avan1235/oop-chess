package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.V2;

import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class PawnPiece extends AbstractPiece {
    private static final List<List<V2>> POSSIBLE_MOVES = List.of(List.of(N));
    private static final List<List<V2>> POSSIBLE_ATTACKS = List.of(
      List.of(NE),
      List.of(NW)
    );

    public PawnPiece(V2 position, ChessColor color) {
      super(position, color, "♙", "♟");
    }

  @Override
  public List<List<V2>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }

  @Override
  public List<List<V2>> getPossibleAttackMoves() {
    return POSSIBLE_ATTACKS;
  }

}
