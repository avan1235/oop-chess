package pl.edu.mimuw.chess.Board;

import java.io.IOException;

public class Util {

  private Util() {
  }

  /**
   * Clearing the console content that works only in terminal mode
   * for applications run manually from command line (not in IDE)
   */
  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static String rowSeparator(int rowNr){
    if(rowNr == 0) {
      return "  ╔═══╤═══╤═══╤═══╤═══╤═══╤═══╤═══╗\n";
    }
    if (rowNr == 8){
      return "  ╚═══╧═══╧═══╧═══╧═══╧═══╧═══╧═══╝\n";
    }
    return "  ╟───┼───┼───┼───┼───┼───┼───┼───╢\n";
  }
}
