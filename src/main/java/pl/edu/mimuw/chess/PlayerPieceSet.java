package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.ChessFactory.*;

import java.util.ArrayList;

public class PlayerPieceSet {
  private int size;
  private final ChessColor color;
  public ArrayList<Pawn>pawns;///Puliczna widoczość pozostawiona celowo: w tym przypadku ważne jest sprawne podgladanie i edytowanie pól z zewnątrz
  public ArrayList<Rook>rooks;
  public ArrayList <Knight>knights;
  public ArrayList <Bishop>bishops;
  public ArrayList<Queen> queen;
  public ArrayList<King> king;
  public ArrayList<AbstractPiece>abstractPieces;
  public int getSize() {
    return size;
  }

  public void setSize(int size) {
    this.size = size;
  }

  public ChessColor getColor() {
    return color;
  }

  public PlayerPieceSet(ChessColor color){
    this.size=16;
    this.color=color;
    this.king=newKing(color);
    this.queen=newQueen(color);
    this.pawns=newPawns(color);
    this.rooks=newRooks(color);
    this.knights=newKnights(color);
    this.bishops=newBishops(color);
    this.abstractPieces=new ArrayList<>();
    abstractPieces.addAll(pawns);
    abstractPieces.addAll(rooks);
    abstractPieces.addAll(knights);
    abstractPieces.addAll(bishops);
    abstractPieces.addAll(queen);
    abstractPieces.addAll(king);
  }

}
