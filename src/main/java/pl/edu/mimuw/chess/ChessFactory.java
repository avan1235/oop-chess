package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class ChessFactory {

  public static ArrayList<Pawn> newPawns(ChessColor color){
    ArrayList<Pawn>pawns=new ArrayList<>(8);
    int row=6;
    if(color==ChessColor.WHITE)row=1;
    for(int i=0;i<8;i++){
      pawns.add(new Pawn(new V2(i,row),color));
    }
    return pawns;
  }

  public static ArrayList<Rook>newRooks(ChessColor color){
    ArrayList<Rook>rooks=new ArrayList<>(2);
    int row=7;
    if(color==ChessColor.WHITE)row=0;
    rooks.add(new Rook(new V2(0,row),color));
    rooks.add(new Rook(new V2(7,row),color));
    return rooks;
  }

  public static ArrayList<Knight>newKnights(ChessColor color){
    ArrayList<Knight>knights=new ArrayList<>(2);
    int row=7;
    if(color==ChessColor.WHITE)row=0;
    knights.add(new Knight(new V2(1,row),color));
    knights.add(new Knight(new V2(6,row),color));
    return knights;
  }

  public static ArrayList<Bishop>newBishops(ChessColor color){
    ArrayList<Bishop>bishops=new ArrayList<>(2);
    int row=7;
    if(color==ChessColor.WHITE)row=0;
    bishops.add(new Bishop(new V2(2,row),color));
    bishops.add(new Bishop(new V2(5,row),color));
    return bishops;
  }

  public static ArrayList<Queen>newQueen(ChessColor color){
    ArrayList<Queen>queen=new ArrayList<>(1);
    int row=7;
    if(color==ChessColor.WHITE)row=0;
    queen.add(new Queen(new V2(3,row),color));
    return queen;
  }

  public static ArrayList<King>newKing(ChessColor color){
    ArrayList<King>king=new ArrayList<>(1);
    int row=7;
    if(color==ChessColor.WHITE)row=0;
    king.add(new King(new V2(3,row),color));
    return king;
  }

}
