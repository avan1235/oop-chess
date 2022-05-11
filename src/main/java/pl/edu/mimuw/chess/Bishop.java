package pl.edu.mimuw.chess;
import pl.edu.mimuw.chess.V2;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class Bishop extends AbstractPiece {


  public Bishop(V2 position, ChessColor color) {
    super(position, color, "♝", "♗");
  }

  public  List<V2> getPossibleMoves() {
    List<V2> result = new ArrayList<>();
    for (final var d : new V2[]{NW, SW, NE, SE}) {
      for (int i = 1; i < Checkboard.BOARD_SIZE; i++) result.add(d.times(i));
    }
    return result;
  }

}
