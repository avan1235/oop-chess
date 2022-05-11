package pl.edu.mimuw;

import pl.edu.mimuw.chess.Checkboard;
import pl.edu.mimuw.chess.ChessPiece;
import pl.edu.mimuw.chess.Util;

public class Main {

  public static void main(String[] args) {
    Checkboard newGame = new Checkboard();
    int licznik = 0;
    while (newGame.play() && licznik <= 100) {
      licznik++;
      try {
        Thread.sleep(10);
      } catch (InterruptedException e) {
      }
      Util.clearConsole();
    }
    ;
    System.out.println(newGame.toString());
  }

}
