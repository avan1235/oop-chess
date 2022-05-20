package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class Knight extends AbstractPiece{
  private static final List<List<V2>> POSSIBLE_MOVES = generatePossibleMoves();

  protected Knight(V2 position, ChessColor color) {
    super(position, color, "♘","♞");
  }

  public List<List<V2>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }

  private static List<List<V2>> generatePossibleMoves() {
    List<List<V2>> result = new ArrayList<>();
    /*for (final var d : new V2[]{N, S, W, E}) {
      List<V2> inDirection = new ArrayList<>();
      inDirection.add(d.times(2))
      //for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) inDirection.add(d.times(i));
     // result.add(inDirection);*
    }*/
    for (final var d1 : new V2[]{N, S, W, E}) {
      List<V2> inDirection = new ArrayList<>();
      for(final var d2 : new V2[]{N, S, W, E}){
        if(d1.dotProduct(d2)==0){
          inDirection.add(d1.times(2).plus(d2));
          inDirection.add(d1.plus(d2.times(2)));
        }
      }
      //for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) inDirection.add(d.times(i));
      // result.add(inDirection);*
    }
    /*List<V2>north=new ArrayList<>();//GENERALIZE!!!!
    north.add(N.times(2).plus(W));
    north.add(N.times(2).plus(E));
    north.add(N.plus(W.times(2)));
    north.add(N.plus(E.times(2)));*/
    return result;
  }
}
