package pl.edu.mimuw;

import pl.edu.mimuw.chess.ChessGame;
import pl.edu.mimuw.chess.Util;

import java.util.Random;

public class Main {

  public static void main(String[] args) {
    long seed = args.length > 0 ? Long.parseLong(args[0]) : new Random().nextInt();
    Random rng = new Random(seed);
    ChessGame game = new ChessGame(rng);
    game.printBoard();
    for (int i = 0; i < 100; i++) {
      Util.waitBetweenFrames();
      try {
        game.nextMove();
      } catch (ChessGame.NoAvailableMoveException e) {
        System.out.println("No available move!");
        break;
      } catch (ChessGame.CapturedKingException e) {
        game.printBoard();
        System.out.println(e.color.name() + " king got captured!");
        break;
      }
      game.printBoard();
    }

    System.out.println("The game is over. Game seed was: " + seed);
  }
}
