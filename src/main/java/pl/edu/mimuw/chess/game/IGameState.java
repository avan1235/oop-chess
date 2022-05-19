package pl.edu.mimuw.chess.game;

import pl.edu.mimuw.chess.board.Board;
import pl.edu.mimuw.chess.enums.Colour;

public interface IGameState {

  Board getBoard();

  void nextMove();

  int getMoveNumber();

  boolean isSimulationStopped();

  Colour checkWinner();

  void simulate();
}
