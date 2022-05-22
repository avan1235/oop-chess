package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

import java.util.*;

public class Knight extends AbstractPiece {
  public Knight(V2 position, ChessColor color) {
    super(position, color, "♞", "♘");
  }

  @Override
  public List<V2> getPossibleMoves() {
    List<V2> result = new ArrayList<V2>();
    for (final V2 d : new V2[]{
      N.plus(NW), N.plus(NE),
      S.plus(SE), S.plus(SW),
      W.plus(NW), W.plus(SW),
      E.plus(NE), E.plus(SE)
    }) {
      result.add(d);
    }
    return result;
  }
}
