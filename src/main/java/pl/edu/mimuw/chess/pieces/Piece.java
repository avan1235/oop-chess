package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;

import java.util.Objects;

public abstract class Piece {
  protected String blackSign;
  protected String whiteSign;
  protected Field field;
  final protected String team;

  protected Piece(Field field,String team) {
    assert team.equals("Black") || team.equals("White");
    this.field = field;
    this.team = team;
  }

  public void  setField(Field field){
    this.field = field;
  }
  public Field getField(){
    return field;
  }
  public String getTeam(){
    return team;
  }

  public boolean isKing(){
    return false;
  }

  public Field[] Possible_moves(Board board) {

    return new Field[0];
  }

  public String toString() {
    if (Objects.equals(team, "Black")) return blackSign;
    return whiteSign;
  }
}
