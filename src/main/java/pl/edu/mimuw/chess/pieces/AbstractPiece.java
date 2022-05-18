package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.ChessPiece;
import pl.edu.mimuw.chess.V2;

import java.util.Objects;

public abstract class AbstractPiece implements ChessPiece {

  private V2 position;
  private final ChessColor color;
  private final String whiteRepresentation;
  private final String blackRepresentation;

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
