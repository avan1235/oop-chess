package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.players.Player;
import pl.edu.mimuw.chess.players.PlayerUtil;
import pl.edu.mimuw.chess.players.RandomPlayer;

public class Game {
  private final Board board;
  private final Player blackPlayer;
  private int blackPlayerMoves;
  private final Player whitePlayer;
  private int whitePlayerMoves;
  private boolean gameOver;



  public Game() {
    this.board = new Board();
    this.blackPlayer = new RandomPlayer("Black");
    this.blackPlayerMoves = 0;
    this.whitePlayer = new RandomPlayer("White");
    this.whitePlayerMoves = 0;
    this.gameOver = false;
  }

  private void makeMove(Player player){
    if(PlayerUtil.getAvailableMoves(board,player).size() == 0){
      gameOver = true;
      return;
    }

    board.movePiece(player.makeMove(board));
    if (!board.isBlackKingAlive || !board.isWhiteKingAlive){
      gameOver = true;
    }
  }
  public void playTurn(){
    if(gameOver) return;

    if(whitePlayerMoves > blackPlayerMoves){
      makeMove(blackPlayer);
      blackPlayerMoves++;
    } else {
      makeMove(whitePlayer);
      whitePlayerMoves++;
    }

  }

  public boolean isGameOver(){
    return gameOver;
  }

  @Override
  public String toString(){
    return "combined moves: "+(blackPlayerMoves+whitePlayerMoves)+"\n" + board;
  }
}
