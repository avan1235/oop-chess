package pl.edu.mimuw.chess;

import java.util.List;

public abstract class AbstractPiece implements IPiece {

  private Square position;
  private final PieceColor color;
  private final String whiteRepresentation;
  private final String blackRepresentation;

  public AbstractPiece(
    Square position,
    PieceColor color,
    String whiteRepresentation,
    String blackRepresentation
  ) {
    this.position = position;
    this.color = color;
    this.whiteRepresentation = whiteRepresentation;
    this.blackRepresentation = blackRepresentation;
  }

  public PieceColor getColor() {
    return color;
  }

  @Override
  public Square currentPosition() {
    return position;
  }

  public void setPosition(Square position) {
    this.position = position;
  }

  @Override
  public abstract List<List<Square>> getPossibleMoves();

  @Override
  public String representation() {
    switch (color) {
      case BLACK:
        {
          return blackRepresentation;
        }
      case WHITE:
        {
          return whiteRepresentation;
        }
    }
    throw new IllegalStateException("Unknown color: " + color);
  }
}
