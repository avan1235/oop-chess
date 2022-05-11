package pl.edu.mimuw.chess;

import java.util.Set;

public abstract class Piece {
  private V2 position;
  private Color color;
  protected static String representation;

  public abstract Set<V2> getPossibleMoves();

  public String getRepresentation() {
    return representation;
  }

  public Color getColor() {
    return color;
  }

  enum Color {WHITE, BLACK}
}
