package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.ChessPiece;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractPiece implements ChessPiece {
  protected final ChessColor color;
  private final String whiteRepresentation;
  private final String blackRepresentation;
  private V2 position;

  protected AbstractPiece(V2 position, ChessColor color, String whiteRepresentation, String blackRepresentation) {
    this.position = position;
    this.color = color;
    this.whiteRepresentation = whiteRepresentation;
    this.blackRepresentation = blackRepresentation;
  }

  @Override
  public V2 getPosition() {
    return position;
  }

  @Override
  public void setPosition(V2 v) {
    this.position = v;
  }

  @Override
  public String representation() {
    switch (color) {
      case BLACK:
        return blackRepresentation;
      case WHITE:
        return whiteRepresentation;
    }
    throw new IllegalStateException("unknown color " + color);
  }

  public abstract List<List<V2>> getRelativeMoves();

  public abstract List<List<V2>> getRelativeAttackMoves();

  public List<List<V2>> movesFromCurrentPosition(List<List<V2>> relativeMoves) {
    List<List<V2>> ret = new ArrayList<>();

    for (var lst : relativeMoves) {
      List<V2> directionList = new ArrayList<>();

      for (var move : lst) {
        directionList.add(move.plus(this.getPosition()));
      }
      ret.add(directionList);
    }

    return ret;
  }

  public List<List<V2>> getAbsoluteMoves() {
    return movesFromCurrentPosition(this.getRelativeMoves());
  }

  public List<List<V2>> getAbsoluteAttackMoves() {
    return movesFromCurrentPosition(this.getRelativeAttackMoves());
  }

  public boolean isKing() {
    return false;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof AbstractPiece)) return false;
    AbstractPiece that = (AbstractPiece) o;
    return position.equals(that.position) &&
      color == that.color &&
      whiteRepresentation.equals(that.whiteRepresentation) &&
      blackRepresentation.equals(that.blackRepresentation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(position, color, whiteRepresentation, blackRepresentation);
  }
}
