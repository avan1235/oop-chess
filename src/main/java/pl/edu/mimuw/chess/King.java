package pl.edu.mimuw.chess;
import pl.edu.mimuw.chess.V2;
import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class King extends AbstractPiece{

  public King(V2 position, ChessColor color){
    super(position, color, "♚", "♔");
  }

  public List<V2> getPossibleMoves(){
    List<V2> result= new ArrayList<>();
    for(final var d: new V2 [] {N, E, S, W, NE, NW, SE, SW}){
      result.add(d);
    }
    return result;
  }

}
