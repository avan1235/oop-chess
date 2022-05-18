package pl.edu.mimuw.chess;

public class ChessBoard {
  public static final int BOARD_SIZE = 8;

  public static boolean isOnBoard(V2 position) {
    return position.x > -1 && position.x < BOARD_SIZE &&
      position.y > -1 && position.y < BOARD_SIZE;
  }
}
