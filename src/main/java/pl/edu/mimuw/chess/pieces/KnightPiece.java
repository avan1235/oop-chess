package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;
import static pl.edu.mimuw.chess.V2.W;

public class KnightPiece extends AbstractPiece {
  private static final List<List<V2>> POSSIBLE_MOVES = generatePossibleMoves();

  public KnightPiece(V2 position, ChessColor color) {
    super(position, color, "♘", "♞");
  }

  private static List<List<V2>> generatePossibleMoves() {
    List<List<V2>> result = new ArrayList<>();

    V2 movesArray[] = {
      N.times(2).plus(E),
      N.times(2).plus(W),
      S.times(2).plus(E),
      S.times(2).plus(W),
      E.times(2).plus(N),
      E.times(2).plus(S),
      W.times(2).plus(N),
      W.times(2).plus(S)
    };

    for (final var d : movesArray) {
      List<V2> inDirection = new ArrayList<>();
      inDirection.add(d);
      result.add(inDirection);
    }
    return result;
  }

  @Override
  public List<List<V2>> getRelativeMoves() {
    return POSSIBLE_MOVES;
  }

  @Override
  public List<List<V2>> getRelativeAttackMoves() {
    return POSSIBLE_MOVES;
  }
}
