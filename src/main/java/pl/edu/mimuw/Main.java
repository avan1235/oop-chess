package pl.edu.mimuw;

import pl.edu.mimuw.chess.*;

public class Main {

  public static void main(String[] args) {
    White white = new White();
    Black black = new Black();

    Board board = new Board(white, black);
    Position pos = new Position("f5");
    System.out.println(pos);

    Piece pawn = board.get(new Position("a2"));
    System.out.println(pawn.getPossibleMoves());
    System.out.println(board);
    board.movePiece(pawn, pawn.getPossibleMoves().toArray(Position[]::new)[0]);
    System.out.println(board);
  }
}
