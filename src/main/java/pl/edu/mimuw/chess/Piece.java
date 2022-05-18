package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Piece {
  protected final Board board;
  protected final boolean isWhite;
  final Player owner;
  private final String whiteIcon;
  private final String blackIcon;
  protected boolean wasMoved = false;
  private Position pos;

  private ArrayList<Position> possibleMoves;
  private int movesLastUpdated = -1;

  Piece(Position pos, Player owner, Board board, String whiteIcon, String blackIcon) {
    this.pos = pos;
    this.owner = owner;
    this.board = board;
    this.isWhite = owner.getClass() == White.class;
    this.whiteIcon = whiteIcon;
    this.blackIcon = blackIcon;
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
      else if (this.isEnemyOnPos(toMove)) {
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
    if (this.movesLastUpdated != board.getTotalMoveCount()) {
      this.movesLastUpdated = board.getTotalMoveCount();
      this.possibleMoves = generatePossibleMoves();
    }
    return new ArrayList<>(this.possibleMoves);
  }

  protected abstract ArrayList<Position> generatePossibleMoves();

  public void becomeCaptured() {
    this.owner.losePiece(this);
  }

  @Override
  public String toString() {
    return icon() + this.pos;
  }

  public String icon() {
    return this.isWhite ? this.whiteIcon : this.blackIcon;
  }

  public boolean isEnemyOnPos(Position pos) {
    Piece piece = board.get(pos);
    if (piece != null)
      return this.isWhite != piece.isWhite;
    else return false;
  }
}
