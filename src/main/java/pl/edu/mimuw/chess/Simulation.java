package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.Util.*;

public class Simulation {
  private final ChessBoard board;
  private final Player black;
  private final Player white;
  private Player winner;

  private static void announceWinner(Player winner) {
    if (winner == null) System.out.println("50 turns have elapsed with no winner.");
    else if (winner.getColor() == ChessColor.WHITE) System.out.println("Winner: WHITE");
    else System.out.println("Winner: BLACK");
  }

  public Simulation(ChessBoard board, Player black, Player white) {
    this.board = board;
    this.black = black;
    this.white = white;
    this.winner = null;
  }

  public static Simulation newGame() {
    PlayerPieceSet blackSet = new PlayerPieceSet(ChessColor.BLACK);
    PlayerPieceSet whiteSet = new PlayerPieceSet(ChessColor.WHITE);
    return new Simulation(new ChessBoard(blackSet, whiteSet), new Player(blackSet), new Player(whiteSet));
  }

  public void run() {
    for (int i = 0; i < 50; i++) {
      if (!white.makeMove(board, black)) {
        this.winner = black;
        this.board.printCurrentState();
        break;
      }
      if (!black.isKingAlive()) {
        this.winner = white;
        this.board.printCurrentState();
        break;
      }
      if (!black.makeMove(board, white)) {
        this.winner = white;
        this.board.printCurrentState();
        break;
      }
      if (!white.isKingAlive()) {
        this.winner = black;
        this.board.printCurrentState();
        break;
      }
      this.board.printCurrentState();
      sleep(100);
      clearConsole();
    }
    announceWinner(winner);

  }

  private static void sleep(long millis) {
    try {
      Thread.sleep(millis);
    } catch (InterruptedException ignored) {
    }
  }
}
