package pl.edu.mimuw.chess;

import java.util.Random;

public class Player {
  public PlayerPieceSet pieces;
  private final ChessColor color;
  private boolean kingAlive;

  public Player(PlayerPieceSet set){
    this.color=set.getColor();
    this.pieces=set;
    this.kingAlive=true;
  }


  public ChessColor getColor() {
    return color;
  }

  public void setKingAlive(boolean kingAlive) {
    this.kingAlive = kingAlive;
  }

  public boolean isKingAlive() {
    return kingAlive;
  }

  public boolean makeMove(ChessBoard board, Player otherPlayer) {
    if(this.pieces.getSize()==0)return false;
    Random RAND=new Random();
    boolean moveSuccessful=false;
    while(true){
      var piece=this.pieces.abstractPieces.get(RAND.nextInt(this.pieces.getSize()));
      for(var innerList:piece.getPossibleMoves()){
        for(var move:innerList){
          if(board.moveAvailable(move,piece)){
            board.executeMove(move,piece,otherPlayer);
            return true;
          }
        }
      }
    }
  }
}
