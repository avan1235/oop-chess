package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
  protected final Player owner;
  protected Board board;
  protected boolean wasMoved = false;
  private Position pos;
  private ArrayList<Position> possibleMoves;
  private int lastUpdated = -1;

  Piece(Position pos, Player owner, Board board) {
    this.owner = owner;
    this.pos = pos;
    this.board = board;
    this.possibleMoves = new ArrayList<>();
    owner.addToPieces(this);
  }

  void move(Position moveTo) {
    this.getPossibleMoves();
    assert possibleMoves.contains(moveTo);
    this.wasMoved = true;
    this.pos = moveTo;
  }

  public Position pos() {
    return this.pos;
  }

  protected void addLinearMoves(ArrayList<Position> moves, int rowDirection, int columnDirection, int radius) {
    Position toMove;
    for (int i = 1; i <= radius; i++) {
      toMove = Position.moveFrom(this.pos(), rowDirection * i, columnDirection * i);
      if (toMove == null) break;
      if (board.isFree(toMove))
        moves.add(toMove);
      else if (this.isEnemyHere(toMove)) {
        moves.add(toMove);
        break;
      }
      else break;
    }
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Piece piece = (Piece) o;
    return this.pos.equals(piece.pos) && this.icon().equals(piece.icon());
  }

  @Override
  public int hashCode() {
    return Objects.hash(pos.row, pos.column, this.icon());
  }

  public ArrayList<Position> getPossibleMoves() {
    if (this.lastUpdated != board.getTotalMoveCount()) {
      this.lastUpdated = board.getTotalMoveCount();
      this.possibleMoves = generatePossibleMoves();
    }
    return this.possibleMoves;
  }

  protected abstract ArrayList<Position> generatePossibleMoves();

  public void becomeCaptured() {
    this.owner.losePiece(this);
  }

  public String getColor() {
    return this.owner.color();
  }

  @Override
  public String toString() {
    return icon() + this.pos;
  }

  public String icon() {
    return this.getColor().equals(Player.white) ? whiteIcon() : blackIcon();
  }

  public boolean isEnemyHere(Position pos) {
    Piece piece = board.get(pos);
    if (piece != null)
      return !this.getColor().equals(piece.getColor());
    else return false;
  }

  protected abstract String whiteIcon();

  protected abstract String blackIcon();
}
