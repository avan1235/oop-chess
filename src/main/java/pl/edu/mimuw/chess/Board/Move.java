package pl.edu.mimuw.chess.Board;

public class Move {
  public final Field from;
  public final Field to;

  public Move(Field from, Field to) {
    this.from = from;
    this.to = to;
  }
}
