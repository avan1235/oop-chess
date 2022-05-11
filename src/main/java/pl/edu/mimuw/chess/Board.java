package pl.edu.mimuw.chess;

import java.util.*;

public class Board {
  private final V2 dimensions;
  private Map<V2, Piece> pieces;

  public Board(V2 dimensions) {
    this.dimensions = dimensions;
    this.pieces = new HashMap<>();
  }

  public String getRepresentation() {
    var s = new StringBuilder();
    s.append(" ╔" + String.join("╤", Collections.nCopies(dimensions.x, "═══")) + "╗ \n");
    for (var y = 0; y < dimensions.y; ++y) {
      if (y > 0)
        s.append(" ╟" + String.join("┼", Collections.nCopies(dimensions.x, "───")) + "╢ \n");

      var cellsInLine = new ArrayList<>();
      for(var x = 0; x < dimensions.x; ++x) {
        var piece = pieces.get(new V2(x, y));
        cellsInLine.add(piece != null ? piece.getRepresentation() : " ");
      }
      s.append(" ║" + String.join("│", Collections.nCopies(dimensions.x, "   ")) + "║ \n");
    }
    s.append(" ╚" + String.join("╧", Collections.nCopies(dimensions.x, "═══")) + "╝ \n");
    return s.toString();
  }
}
