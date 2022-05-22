package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;

import static pl.edu.mimuw.chess.V2.*;

public class Pawn extends AbstractPiece {
  public Pawn(V2 position, ChessColor color) {
    super(position, color, "♟", "♙");
  }

  @Override
  public List<V2> getPossibleMoves() {
    var result = new ArrayList<V2>();
    if (this.getColor() == ChessColor.WHITE) {
      result.add(S);
    } else {
      result.add(N);
    }
    return result;
  }

}
