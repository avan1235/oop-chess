package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece{

  public Pawn(Field field, String team) {
    super(field,team);
    blackSign = "♙";
    whiteSign = "♟";
  }

  @Override
  public Field[] Possible_moves(Board board) {
    List<Field> moves = new ArrayList<>();

    int delta=-1;
    if(this.getTeam().equals("White")) delta = 1;

    Piece moveDestination;
    Field field = this.getField().s(delta);
    if(field.isCorrect()) {
      moveDestination = board.getPiece(field);
      if (moveDestination == null) {
        moves.add(field);
      }
    }

    for (int i = -1; i <2 ; i+=2) {
      field = new Field(this.getField().x+i, this.getField().y+delta);
      if(field.isCorrect()) {
        moveDestination = board.getPiece(field);
        if (moveDestination != null && !moveDestination.getTeam().equals(this.getTeam()) ) {
          moves.add(field);
        }
      }
    }

    return moves.toArray(new Field[0]);
  }

}
