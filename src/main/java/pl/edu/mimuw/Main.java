package pl.edu.mimuw;

import pl.edu.mimuw.chess.ChessGame;

public class Main {
  public static void main(String[] args) throws InterruptedException {
    var game = new ChessGame();
    game.runSimulation();
  }
}
