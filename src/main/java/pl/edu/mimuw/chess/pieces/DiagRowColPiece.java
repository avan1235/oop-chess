package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.board.IBoard;
import pl.edu.mimuw.chess.board.Position;
import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.enums.Direction;
import pl.edu.mimuw.chess.enums.PieceType;

import java.util.ArrayList;
import java.util.EnumSet;

public abstract class DiagRowColPiece extends AbstractPiece {
  public DiagRowColPiece(int row, int column, PieceType type, Colour colour) {
    super(row, column, type, colour);
  }

  public DiagRowColPiece(Position position, PieceType type, Colour colour) {
    super(position, type, colour);
  }

  protected ArrayList<Position> movesInLine(EnumSet<Direction> directions, IBoard board) {
    ArrayList<Position> newPositions = new ArrayList<>();

    for (var dir: directions) {
      Position newPosition = this.position.stepIntoDirection(dir);

      while (board.insideBoard(newPosition)) {
        Colour occupiedColour = board.isOccupiedBy(newPosition);

        if (this.colour == occupiedColour) {
          break;
        }
        else if (this.colour.oppose() == occupiedColour) {
          newPositions.add(newPosition);
          break;
        }
        else if (occupiedColour == Colour.NONE) {
          newPositions.add(newPosition);
          newPosition = newPosition.stepIntoDirection(dir);
        }
        else {
          throw new RuntimeException("Case not implemented");
        }
      }
    }

    return newPositions;
  }

  protected ArrayList<Position> diagonalMoves(IBoard board) {
    EnumSet<Direction> directions = EnumSet.of(Direction.NE, Direction.SE, Direction.SW, Direction.NW);

    return this.movesInLine(directions, board);
  }

  protected ArrayList<Position> rowColumnMoves(IBoard board) {
    EnumSet<Direction> directions = EnumSet.of(Direction.N, Direction.S, Direction.E, Direction.W);

    return this.movesInLine(directions, board);
  }

  protected ArrayList<Position> diagRowColMoves(IBoard board) {
    var moves = this.diagonalMoves(board);
    moves.addAll(this.rowColumnMoves(board));

    return moves;
  }
}
