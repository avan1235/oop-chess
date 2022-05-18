package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.ArrayList;
import java.util.List;

public class Queen extends Piece{
  public Queen(Field field, String team) {
    super(field,team);
    blackSign = "♕";
    whiteSign = "♛";
  }

  @Override
  public Field[] Possible_moves(Board board) {
    List<Field> moves = new ArrayList<>();

    MoveUtil.moveVertical(9,this,board,moves);
    MoveUtil.moveDiagonal(9,this,board,moves);

    return moves.toArray(new Field[0]);
  }

}
