package pl.edu.mimuw;

import pl.edu.mimuw.chess.Board;
import pl.edu.mimuw.chess.Util;
import pl.edu.mimuw.chess.V2;

public class Main {

  public static void main(String[] args) {
    var board = new Board(new V2(8, 8));
    try {
      for (var i = 0; i < 50; ++i) {
        Thread.sleep(1000);
        Util.clearConsole();
        System.out.println("i = " + i);
        System.out.println(board.getRepresentation());
      }
    } catch (InterruptedException exception) {

    }
  }
}
