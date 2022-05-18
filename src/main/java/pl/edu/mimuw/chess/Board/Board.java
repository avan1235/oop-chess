package pl.edu.mimuw.chess.Board;

import pl.edu.mimuw.chess.pieces.Piece;

import java.util.*;

public class Board {
  private final Piece[][] board;
  public boolean isWhiteKingAlive;
  public boolean isBlackKingAlive;

  public Board() {
    board = new Piece[8][8];
    BoardBuilder.build(board);
    isWhiteKingAlive = true;
    isBlackKingAlive = true;
  }

  public Piece getPiece(Field field){
    return board[field.x][field.y];
  }

  public void movePiece(Move move){
    if(board[move.to.x][move.to.y]!= null && board[move.to.x][move.to.y].isKing()){
      if(board[move.to.x][move.to.y].getTeam().equals("White") ){
        isWhiteKingAlive = false;
      } else {
        isBlackKingAlive = false;
      }
    }

    board[move.to.x][move.to.y] = board[move.from.x][move.from.y];
    board[move.to.x][move.to.y].setField(move.to);
    board[move.from.x][move.from.y] = null;
  }

  private String pieceToString(Piece piece){
    if(piece== null) return " ";
    else return piece.toString();
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(" ");
    for (int i = 1; i <= 8; i++) {
      sb.append(String.format("%1$4s",i));
    }
    sb.append("\n");

    for (int y = 0; y < 8; y++) {
      sb.append(Util.rowSeparator(y))
        .append(y+1).append(" ║ ");
      for (int x = 0; x < 8; x++) {
        sb.append(pieceToString(board[x][y]));
        if(x < 7) sb.append(" │ ");

      }
      sb.append(" ║\n");
    }
    sb.append(Util.rowSeparator(8));

    return sb.toString();
  }
}
