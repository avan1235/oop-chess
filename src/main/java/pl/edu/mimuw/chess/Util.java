package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.ChessBoard.isOnBoard;

public class Util {

  private Util() {
  }

  public static String square(V2 position) {
    assert isOnBoard(position);
    if ((position.x + position.y) % 2 == 0) return "█";
    else return " ";
  }

  /**
   * Clearing the console content that works only in terminal mode
   * for applications run manually from command line (not in IDE)
   */
  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }
}
