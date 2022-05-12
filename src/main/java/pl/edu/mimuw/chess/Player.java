package pl.edu.mimuw.chess;

public class Player {
  private final Color color;
  // How the Y coordinate changes when this player's piece is moving forward.
  private final int forward;

  public Player(Color color, int forward) {
    this.color = color;
    this.forward = forward;
  }

  public Color getColor() {
    return color;
  }

  public int getForward() {
    return forward;
  }

  public void winTheGame() throws GameEnded {
    throw new GameEnded(this);
  }

  @Override
  public String toString() {
    return "the " + color.toString().toUpperCase();
  }

  enum Color {
    WHITE,
    BLACK
  }
}
