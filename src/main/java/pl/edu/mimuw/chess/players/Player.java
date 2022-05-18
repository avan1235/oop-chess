package pl.edu.mimuw.chess.players;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Move;

public abstract class Player {
  protected final String myTeam;

  protected Player(String myTeam) {
    this.myTeam = myTeam;
  }

  public String getTeam(){
    return myTeam;
  }

  public abstract Move makeMove(Board board);
}
