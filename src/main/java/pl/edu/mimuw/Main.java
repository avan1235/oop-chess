package pl.edu.mimuw;

import pl.edu.mimuw.chess.*;

public class Main {

  public static void main(String[] args) throws InterruptedException {
    ChessGame game = new ChessGame();
    White white = game.getWhite();
    Black black = game.getBlack();

    while (game.getMoveCount() < 100 && !game.isFinished())
      for (Player player : new Player[]{white, black}) {
        game.printBoard();
        Thread.sleep(100);
        player.makeRandomMove();
        Util.clearConsole();
    }

    game.printBoard();
    game.printResult();
  }
}
