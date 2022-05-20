package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.edu.mimuw.chess.ChessBoard.*;

public class ChessGame {
  private final Player whitePlayer;
  private final Player blackPlayer;

  public ChessGame() {
    this.whitePlayer = new Player(ChessColor.WHITE);
    this.blackPlayer = new Player(ChessColor.BLACK);
  }

  public List<List<V2>> removeOutBoardMoves(List<List<V2>> possibleMoves) {
    List<List<V2>> result = new ArrayList<>();

    for (var i : possibleMoves) {

      List<V2> clear = new ArrayList<>();

      for (var move : i) {
        if (ChessBoard.isOnBoard(move)) {
          clear.add(move);
        } else {
          break;
        }
      }

      result.add(clear);
    }

    return result;
  }

  public ChessPiece getPieceFromPosition(List<ChessPiece> pieces, V2 v2) {
    for (var piece : pieces) {
      if (piece.getPosition().x == v2.x && piece.getPosition().y == v2.y) {
        return piece;
      }
    }
    return null;
  }

  public List<V2> getFinalMoves(List<ChessPiece> myPieces, List<List<V2>> possibleMoves) {
    List<V2> finalMoves = new ArrayList<>();

    for (var i : possibleMoves) {
      for (var move : i) {
        ChessPiece isItEmpty = getPieceFromPosition(myPieces, move);

        if (isItEmpty == null) {
          finalMoves.add(move);
        } else {
          break;
        }
      }
    }

    return finalMoves;
  }

  public List<List<V2>> movesFromActualPosition(ChessPiece piece, List<List<V2>> possibleMoves) {

    List<List<V2>> result = new ArrayList<>();

    for (var i : possibleMoves) {
      List<V2> inDirection = new ArrayList<>();

      for (var move : i) {
        inDirection.add(move.plus(piece.getPosition()));
      }
      result.add(inDirection);
    }

    return result;
  }

  private void changePosition(ChessPiece piece, V2 v2, List<ChessPiece> myPieces, List<ChessPiece> opponentPieces) {
      ChessPiece myPiece = getPieceFromPosition(myPieces, piece.getPosition());
      ChessPiece opponentPiece = getPieceFromPosition(opponentPieces, v2);

      if(opponentPiece != null){
        opponentPieces.remove(opponentPiece);
      }

      myPiece.setPosition(v2);
  }

  public boolean makeMove(ChessColor color) {
    List<ChessPiece> myPieces;
    List<ChessPiece> opponentPieces;

    if (color == ChessColor.WHITE) {
      myPieces = whitePlayer.getPieces();
      opponentPieces = blackPlayer.getPieces();
    } else {
      myPieces = blackPlayer.getPieces();
      opponentPieces = whitePlayer.getPieces();
    }

    List<ChessPiece> moves;

    for (int i = 0; i < myPieces.size(); i++) {
      Random randomNum = new Random();
      ChessPiece randomPiece = myPieces.get(randomNum.nextInt(myPieces.size()));
      List<List<V2>> possibleMoves = movesFromActualPosition(randomPiece, randomPiece.getPossibleMoves());
      possibleMoves = removeOutBoardMoves(possibleMoves);
      List<V2> finalMoves = getFinalMoves(myPieces, possibleMoves);

      if (finalMoves.size() != 0) {
        Random randomMove = new Random();
        changePosition(randomPiece, finalMoves.get(randomMove.nextInt(finalMoves.size())), myPieces, opponentPieces);
        return true;
      } else {
        myPieces.remove(randomNum);
      }
    }

    return false;
  }

  public String draw() {
    return "It's a draw!";
  }

  public boolean kingIsAlive (ChessColor color){
    List<ChessPiece> pieces;

    if(color == ChessColor.WHITE){
      pieces = whitePlayer.getPieces();
    }else{
      pieces = blackPlayer.getPieces();
    }

    for(var piece : pieces){
      if(piece.getClass() == KingPiece.class){
        return true;
      }
    }

    return false;
  }

  public void play() throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      ChessColor color;
      ChessColor kingColor;

      if (i % 2 == 0) {
        color = ChessColor.WHITE;
        kingColor = ChessColor.BLACK;
      } else {
        color = ChessColor.BLACK;
        kingColor = ChessColor.WHITE;
      }

      if (!makeMove(color)) {
        System.out.println(draw());
        return;
      }

      this.printGame();
      Thread.sleep(1000);

      if(!kingIsAlive(kingColor)){
        System.out.println("The game has been ended. " +  color + " won.");
        return;
      }

    }

    System.out.println(draw());
  }

  public void printGame() {
    Util.clearConsole();
    System.out.println(this);
  }

  public String toString() {
    final String top = "╔═╤═╤═╤═╤═╤═╤═╤═╗\n";
    final String middle = "╟─┼─┼─┼─┼─┼─┼─┼─╢\n";
    final String bottom = "╚═╧═╧═╧═╧═╧═╧═╧═╝\n";
    final String edge = "║";
    final String line = "│";
    final String whiteField = " ";
    final String blackField = "█";

    StringBuilder result = new StringBuilder();
    result.append(top);

    for (int y = 0; y < BOARD_SIZE; y++) { //wiersz
      result.append(edge);
      for (int x = 0; x < BOARD_SIZE; x++) { //kolumna

        ChessPiece findPiece = getPieceFromPosition(whitePlayer.getPieces(), new V2(x, y));

        if (findPiece == null) {
          findPiece = getPieceFromPosition(blackPlayer.getPieces(), new V2(x, y));
        }

        if (findPiece != null) {
          result.append(findPiece.representation());
        } else {
          if ((x + y) % 2 == 0) {
            result.append(blackField);
          } else {
            result.append(whiteField);
          }
        }

        if (x != 7) {
          result.append(line);
        }
      }
      result.append(edge)
        .append("\n");
      if (y != 7) {
        result.append(middle);
      }
    }
    result.append(bottom);


    return result.toString();
  }
}
