package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.BoardSquare.*;
import static pl.edu.mimuw.chess.PieceColor.*;

public class Game {

  private ChessBoard board;
  private ChessPlayer playerWhite;
  private ChessPlayer playerBlack;
  private PieceColor currentTurn = WHITE;

  public Game(
    ChessBoard board,
    ChessPlayer playerWhite,
    ChessPlayer playerBlack
  ) {
    this.board = board;
    this.playerWhite = playerWhite;
    this.playerBlack = playerBlack;
  }

  public boolean simulateNextMove() {
    ChessPlayer currentPlayer;
    if (currentTurn == WHITE) {
      currentPlayer = playerWhite;
    } else {
      currentPlayer = playerBlack;
    }
    Move playerMove = currentPlayer.makeAMove(
      board.returnPossibleMovesForCurrentTurn(currentTurn)
    );
    if(playerMove == null){
        if (currentTurn == WHITE) {
            currentTurn = BLACK;
          } else {
            currentTurn = WHITE;
          }
        return true;
    }
    Boolean isTheGameOver = board.commitMoveAndCheckForWin(playerMove);
    System.out.println(board);
    if (isTheGameOver) {
      return true;
    }
    if (currentTurn == WHITE) {
      currentTurn = BLACK;
    } else {
      currentTurn = WHITE;
    }
    return false;
  }

  public PieceColor getCurrentTurn() {
    return currentTurn;
  }
}
