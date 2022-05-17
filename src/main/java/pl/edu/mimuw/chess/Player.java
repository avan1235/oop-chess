package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Set;

public abstract class Player {
  public static final String white = "white";
  public static final String black = "black";
  protected String color;
  private Set<Piece> pieces;

  public Player() {
    this.pieces = new HashSet<>();
  }

  public void addToPieces(Piece piece) {
    assert piece.color().equals(this.color());
    pieces.add(piece);
  }

  public void makeRandomMove() {

  }

  public String color() {
    return this.color;
  }
}
