package pl.edu.mimuw;

import pl.edu.mimuw.chess.Board;

public class Main {

  public static void main(String[] args) {
    final int seed = 2137;
    var board = new Board();
    board.simulateGame(seed);
  }
}
