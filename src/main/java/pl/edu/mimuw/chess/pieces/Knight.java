package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Piece {

  public Knight(Field field, String team) {
    super(field,team);
    blackSign = "♘";
    whiteSign = "♞";
  }

  @Override
  public Field[] Possible_moves(Board board) {
    List<Field> moves = new ArrayList<>();

    MoveUtil.addWhenCorrect(this,board,new Field(this.field.x+1,this.field.y+2),moves);

    MoveUtil.addWhenCorrect(this,board,new Field(this.field.x+1,this.field.y-2),moves);

    MoveUtil.addWhenCorrect(this,board,new Field(this.field.x-1,this.field.y-2),moves);

    MoveUtil.addWhenCorrect(this,board,new Field(this.field.x-1,this.field.y+2),moves);

    return moves.toArray(new Field[0]);
  }
}
