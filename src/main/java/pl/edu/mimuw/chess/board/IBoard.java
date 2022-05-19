package pl.edu.mimuw.chess.board;

import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.pieces.AbstractPiece;

public interface IBoard {
  AbstractPiece getPiece(Position position);

  void movePiece(Position positionFrom, Position positionTo);

  default boolean isOccupied(Position position) {
    return getPiece(position) != null;
  }

  default Colour isOccupiedBy(Position position) {
    if (getPiece(position) == null) {
      return Colour.NONE;
    }
    else {
      return getPiece(position).getColour();
    }
  }

  boolean insideBoard(Position position);
}
