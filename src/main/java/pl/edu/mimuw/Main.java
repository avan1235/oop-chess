package pl.edu.mimuw;

import pl.edu.mimuw.chess.*;

public class Main {

  public static void main(String[] args) {
    var board = new Board(new XY(8, 8));
    try {
      boolean ended = false;
      for (var i = 0; i < 50; ++i) {
        Console.clear();
        System.out.println("i = " + i);
        System.out.println(board.getRepresentation());
        Thread.sleep(500);

        try {
          board.doMove();
        } catch (NoMovesLeft e) {
          System.out.println("No moves left!");
          ended = true;
        } catch (GameEnded gameEnded) {
          System.out.println(gameEnded.getWinner() + " has won!");
          ended = true;
        }
        if(ended) break;
      }
      if(!ended) {
        System.out.println("Game aborted!");
      }
    } catch (InterruptedException ignored) {
    }
  }
}
