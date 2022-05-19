package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.enums.PieceType;
import pl.edu.mimuw.chess.board.Position;

import java.util.ArrayList;

public abstract class AbstractPiece {
  protected final Position position;
  protected final PieceType type;
  protected final Colour colour;

  protected boolean takenOut = false;

  public AbstractPiece(int row, int column, PieceType type, Colour colour) {
    this.position = new Position(row, column);
    this.type = type;
    this.colour = colour;
  }

  public AbstractPiece(Position position, PieceType type, Colour colour) {
    this.position = new Position(position);
    this.type = type;
    this.colour = colour;
  }

  public boolean isTakenOut() {
    return takenOut;
  }

  public void takeOut() {
    takenOut = true;
  }

  public Position getPosition() {
    return new Position(position);
  }

  public PieceType getType() {
    return type;
  }

  public Colour getColour() {
    return colour;
  }

  public void move(Position position) {
    this.position.setPosition(position);
  }

  abstract public ArrayList<Position> genMoves(IBoard board);


}
