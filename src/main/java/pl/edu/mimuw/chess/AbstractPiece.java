package pl.edu.mimuw.chess;

import java.util.List;
import java.util.Set;

public abstract class AbstractPiece implements ChessPiece {

  private V2 position;
  private final ChessColor color;
  private final String whiteRepresentation;
  private final String blackRepresentation;

  public AbstractPiece(V2 position, ChessColor color, String whiteRepresentation, String blackRepresentation){
    this.position = position;
    this.color = color;
    this.whiteRepresentation = whiteRepresentation;
    this.blackRepresentation = blackRepresentation;
  }
  @Override
  public List<V2> getPossibleMoves() {
    return null;
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
    switch (this.color) {
      case BLACK:
        return blackRepresentation;
      case WHITE:
        return whiteRepresentation;
    }
    throw new IllegalStateException("unknown color " + color);
  }
  public ChessColor getColor(){
    return this.color;
  }
}
