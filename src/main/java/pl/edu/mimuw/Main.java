package pl.edu.mimuw;

import static pl.edu.mimuw.chess.PieceColor.*;

import pl.edu.mimuw.chess.*;

public class Main {

  public static void main(String[] args) {
    ChessPlayer playerWhite = new ChessPlayer(WHITE);
    ChessPlayer playerBlack = new ChessPlayer(BLACK);
    ChessBoard board = new ChessBoard();
    Game game = new Game(board, playerWhite, playerBlack);

    for (int i = 0; i < 100; i++) {
      boolean isItOver = game.simulateNextMove();
      if(isItOver){
        PieceColor winner = game.getCurrentTurn();
        System.out.println("Winner is player with " + winner);
        try {
          Thread.sleep(5000);
        } catch (InterruptedException e) {}
        break;
      }
      else{
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {}
      Util.clearConsole();
      }
    }
    
  }
}
