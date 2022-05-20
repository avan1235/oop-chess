package pl.edu.mimuw;

import pl.edu.mimuw.chess.ChessGame;
import pl.edu.mimuw.chess.Util;

public class Main {

  public static void main(String[] args) throws InterruptedException {

    ChessGame game = new ChessGame();
    game.play();

//    for (String arg : new String[]{"some", "example", "strings"}) {
//      sleep(1_000);
//      Util.clearConsole();
//      System.out.println(arg);
//    }
  }

//  private static void sleep(long millis) {
//    try {
//      Thread.sleep(millis);
//    } catch (InterruptedException ignored) {
//    }
//  }
}
