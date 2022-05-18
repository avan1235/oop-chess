package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.ArrayList;
import java.util.List;

public class King extends Piece{
  public King(Field field, String team) {
    super(field,team);
    blackSign = "♔";
    whiteSign = "♚";
  }

  @Override
  public boolean isKing() {
    return true;
  }

  @Override
  public Field[] Possible_moves(Board board) {
    List<Field> moves = new ArrayList<>();

    MoveUtil.moveVertical(1,this,board,moves);
    MoveUtil.moveDiagonal(1,this,board,moves);

    return moves.toArray(new Field[0]);
  }
}
