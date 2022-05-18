package pl.edu.mimuw.chess.Board;

import pl.edu.mimuw.chess.pieces.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class BoardBuilder {

  private static void addRow(Piece[][] board, String team){
    int rowNr;
    if(team.equals("White")) rowNr=0;
    else rowNr = 7;

    board[0][rowNr] = new Rook(new Field(0,rowNr),team);
    board[7][rowNr] = new Rook(new Field(7,rowNr),team);

    board[1][rowNr] = new Knight(new Field(1,rowNr),team);
    board[6][rowNr] = new Knight(new Field(6,rowNr),team);

    board[2][rowNr] = new Bishop(new Field(2,rowNr),team);
    board[5][rowNr] = new Bishop(new Field(5,rowNr),team);

    board[3][rowNr] = new Queen(new Field(3,rowNr),team);
    board[4][rowNr] = new King(new Field(4,rowNr),team);
  }

  public static void build(Piece[][] board){
    assert board.length == 8;
    for (Piece[] row: board) {
      assert row.length == 8;
    }


   addRow(board,"White");
    Field crrField;
    for (int x = 0; x < 8; x++) {
      crrField = new Field(x,1);
      board[x][1] = new Pawn(crrField,"White");
    }

    addRow(board,"Black");
    for (int x = 0; x < 8; x++) {
      crrField = new Field(x,6);
      board[x][6] = new Pawn(crrField,"Black");
    }
  }
}
