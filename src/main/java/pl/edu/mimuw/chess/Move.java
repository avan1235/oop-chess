package pl.edu.mimuw.chess;

public class Move {
  private final Piece what;
  private final XY whereTo;

  public Move(Piece what, XY whereTo) {
    this.what = what;
    this.whereTo = whereTo;
  }

  public Piece getWhat() {
    return what;
  }

  public XY getWhereTo() {
    return whereTo;
  }
}
