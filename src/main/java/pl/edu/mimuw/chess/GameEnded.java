package pl.edu.mimuw.chess;

public class GameEnded extends Exception {
  private final Player winner;

  public GameEnded(Player winner) {
    super();
    this.winner = winner;
  }


  public Player getWinner() {
    return winner;
  }
}
