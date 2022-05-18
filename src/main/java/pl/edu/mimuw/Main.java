package pl.edu.mimuw;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.Util;

public class Main {
  public static void main(String[] args){
    ChessBoard game = new ChessBoard();
    do {
      System.out.print(game);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
      Util.clearConsole();
    }while(game.playTurn());
    System.out.print(game);
  }
}
