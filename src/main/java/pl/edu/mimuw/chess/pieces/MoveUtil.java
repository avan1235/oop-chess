package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.List;

public class MoveUtil {

  public static boolean addWhenCorrect(Piece piece, Board board, Field moveTo, List<Field> moves){
    if(moveTo.isCorrect()) {
      var moveDestination = board.getPiece(moveTo);
      if (moveDestination == null || !moveDestination.getTeam().equals(piece.getTeam())) {
        moves.add(moveTo);
        return true;
      }
    }
    return false;
  }

  public static void moveDiagonal(int range, Piece piece, Board board, List<Field> moves){
    int i;

    i=1;
    while (addWhenCorrect(piece, board, piece.getField().se(i), moves)) {
      if(Math.abs(i) == range) break;
      i++;
    }

    i=-1;
    while (addWhenCorrect(piece, board, piece.getField().se(i), moves)) {
      if(Math.abs(i) == range) break;
      i--;
    }

    i=1;
    while (addWhenCorrect(piece, board, piece.getField().ws(i), moves)) {
      if(Math.abs(i) == range) break;
      i++;
    }

    i=-1;
    while (addWhenCorrect(piece, board, piece.getField().ws(i), moves)) {
      if(Math.abs(i) == range) break;
      i--;
    }
  }

  public static void moveVertical(int range, Piece piece, Board board, List<Field> moves){
    int i;

    i=1;
    while (addWhenCorrect(piece, board, piece.getField().s(i), moves)) {
      if(Math.abs(i) == range) break;
      i++;
    }

    i=-1;
    while (addWhenCorrect(piece, board, piece.getField().s(i), moves)) {
      if(Math.abs(i) == range) break;
      i--;
    }

    i=1;
    while (addWhenCorrect(piece, board, piece.getField().e(i), moves)) {
      if(Math.abs(i) == range) break;
      i++;
    }

    i=-1;
    while (addWhenCorrect(piece, board, piece.getField().e(i), moves)) {
      if(Math.abs(i) == range) break;
      i--;
    }
  }
}
