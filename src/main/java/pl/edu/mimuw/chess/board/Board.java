package pl.edu.mimuw.chess.board;

import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.pieces.*;

import java.util.ArrayList;

public class Board implements IBoard {
  private final AbstractPiece[][] board;

  public Board() {
    this.board = new AbstractPiece[8][8];

    for (int i = 0; i < 8; i++) {
      this.board[1][i] = new Pawn(1, i, Colour.WHITE);
      this.board[6][i] = new Pawn(6, i, Colour.BLACK);
    }

    this.board[0][0] = new Rook(0, 0, Colour.WHITE);
    this.board[0][7] = new Rook(0, 7, Colour.WHITE);
    this.board[0][1] = new Knight(0, 1, Colour.WHITE);
    this.board[0][6] = new Knight(0, 6, Colour.WHITE);
    this.board[0][2] = new Bishop(0, 2, Colour.WHITE);
    this.board[0][5] = new Bishop(0, 5, Colour.WHITE);
    this.board[0][3] = new Queen(0, 3, Colour.WHITE);
    this.board[0][4] = new King(0, 4, Colour.WHITE);

    this.board[7][0] = new Rook(7, 0, Colour.BLACK);
    this.board[7][7] = new Rook(7, 7, Colour.BLACK);
    this.board[7][1] = new Knight(7, 1, Colour.BLACK);
    this.board[7][6] = new Knight(7, 6, Colour.BLACK);
    this.board[7][2] = new Bishop(7, 2, Colour.BLACK);
    this.board[7][5] = new Bishop(7, 5, Colour.BLACK);
    this.board[7][3] = new Queen(7, 3, Colour.BLACK);
    this.board[7][4] = new King(7, 4, Colour.BLACK);
  }

  public AbstractPiece getPiece(Position position) {
    assert this.insideBoard(position);
    return board[position.getRow()][position.getColumn()];
  }

  @Override
  public void movePiece(Position positionFrom, Position positionTo) {
    assert (this.insideBoard(positionFrom) && this.insideBoard(positionTo));

    AbstractPiece toMove = board[positionFrom.getRow()][positionFrom.getColumn()];
    if (board[positionTo.getRow()][positionTo.getColumn()] != null) {
      board[positionTo.getRow()][positionTo.getColumn()].takeOut();
    }
    board[positionTo.getRow()][positionTo.getColumn()] = toMove;
    board[positionFrom.getRow()][positionFrom.getColumn()] = null;

    toMove.move(positionTo);
  }

  public boolean insideBoard(Position position) {
    if (position.getColumn() >=0 && position.getColumn() < 8) {
      return position.getRow() >= 0 && position.getRow() < 8;
    }

    return false;
  }

  public ArrayList<AbstractPiece> piecesList(Colour colour) {
    ArrayList<AbstractPiece> pieces = new ArrayList<>();

    for (var elem: board) {
      for (var piece: elem) {
        if (piece != null) {
          if (piece.getColour() == colour) {
            pieces.add(piece);
          }
        }
      }
    }

    return pieces;
  }

  private String dividingLine() {
    return "╟─┼─┼─┼─┼─┼─┼─┼─╢\n";
  }

  private String topLine() {
    return "╔═╤═╤═╤═╤═╤═╤═╤═╗\n";
  }

  private String bottomLine() {
    return "╚═╧═╧═╧═╧═╧═╧═╧═╝\n";
  }

  private String cell(int row, int column) {
    if (row % 2 == column % 2) {
      return " ";
    }
    else {
      return "█";
    }
  }

  @Override
  public String toString() {
    var sb = new StringBuilder();

    sb.append(topLine());
    for (int row = 7; row >= 0; row--) {
      for (int column = 0; column < 8; column++) {
        if (column == 0) {
          sb.append("║");
        }
        else {
          sb.append("│");
        }
        if (board[row][column] != null) {
          sb.append(board[row][column].toString());
        }
        else {
          sb.append(cell(row, column));
        }
      }
      sb.append("║\n");
      if (row != 0) {
        sb.append(dividingLine());
      }
      else {
        sb.append(bottomLine());
      }
    }
    return sb.toString();
  }
}
