package pl.edu.mimuw;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Util;
import pl.edu.mimuw.chess.Game;

import java.util.Scanner;

public class Main {

  public static void main(String[] args) {
    var game = new Game();
    System.out.println(game);

    for (int i = 0; i < 50; i++) {
      game.playTurn();
      game.playTurn();
      //Util.clearConsole();
      //System.out.println();
      //System.out.println(game);
      if(game.isGameOver())break;
    }
    System.out.println();
    System.out.println(game);
  }
}
