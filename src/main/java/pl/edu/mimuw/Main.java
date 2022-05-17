package pl.edu.mimuw;

import pl.edu.mimuw.chess.*;

public class Main {

  public static void main(String[] args) {
    White white = new White();
    Black black = new Black();

    Board board = new Board(white, black);
    Position pos = new Position("f5");
    System.out.println(pos);

    Piece pawn = new Pawn(pos, white, board);
    System.out.println(pawn.genPossibleMoves());

    System.out.println(board);
  }
}
