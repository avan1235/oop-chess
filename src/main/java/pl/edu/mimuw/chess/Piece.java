package pl.edu.mimuw.chess;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public abstract class Piece {
  private final Player owner;
  private Position pos;
  protected Board board;
  protected Set<Position> possibleMoves;
  private int lastUpdated;

  Piece(Position pos, Player owner, Board board) {
    this.owner = owner;
    this.pos = pos;
    this.board = board;
    this.possibleMoves = new HashSet<>();
    this.lastUpdated = -1;
    owner.addToPieces(this);
  }

   void move(Position moveTo) {
    getPossibleMoves();
    assert possibleMoves.contains(moveTo);
    this.pos = moveTo;
  }

  public Position pos() {
    return this.pos;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Piece piece = (Piece) o;
    return this.owner.color().equals(piece.owner.color()) && this.pos.equals(piece.pos);
  }

  @Override
  public int hashCode() {
    return Objects.hash(pos, owner);
  }

  public Set<Position> getPossibleMoves() {
    if (this.lastUpdated != board.getMoveCount()) {
      this.lastUpdated = board.getMoveCount();
      this.possibleMoves = generatePossibleMoves();
    }
    return this.possibleMoves;
  }

  public abstract Set<Position> generatePossibleMoves();

  public String color() {
    return this.owner.color();
  }

  protected int orientation() {
    return this.color().equals(Player.white) ? 1 : -1;
  }

  @Override
  public String toString() {
    return icon() + this.pos;
  }

  public String icon() {
    return this.color().equals(Player.white) ? whiteIcon() : blackIcon();
  }

  public boolean isEnemyHere(Position pos) {
    Piece piece = board.get(pos);
    if (piece != null)
      return !this.color().equals(piece.color());
    else return false;
  }

  protected abstract String whiteIcon();

  protected abstract String blackIcon();
}