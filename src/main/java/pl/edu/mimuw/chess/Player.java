package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.ChessColor.BLACK;
import static pl.edu.mimuw.chess.ChessColor.WHITE;

public class Player {
  private List<ChessPiece> pieces;
  private final ChessColor color;

  public Player(ChessColor color) {
    this.color = color;
    this.pieces = initPieces();
  }

  public ChessColor getColor() {
    return this.color;
  }

  public static boolean isMoveCorrect(V2 position1, V2 position2, ChessPiece[][] chessPieces) {
    if (!Checkboard.isOnBoard(position1) || !Checkboard.isOnBoard(position2))
      return false;
    if(chessPieces[position2.x][position2.y] != null){
      if(chessPieces[position2.x][position2.y].getColor() == chessPieces[position1.x][position1.y].getColor())
        return false;
    }
    int abs1 = Math.abs(position1.x - position2.x);
    int abs2 = Math.abs(position1.y - position2.y);
    //move of knights
    if (abs1 == 1 && abs2 == 2)
      return true;
    if (abs1 == 2 && abs1 == 1)
      return true;
    //move horizontal or vertical
    if (abs1 == 0) {
      for (int i = Math.min(position1.y, position2.y) + 1; i <= Math.max(position1.y, position2.y) - 1; i++) {
        if (chessPieces[position1.x][i] != null)
          return false;
      }
      return true;
    }
    if (abs2 == 0) {
      for (int i = Math.min(position1.x, position2.x) + 1; i <= Math.max(position1.x, position2.x) - 1; i++) {
        if (chessPieces[i][position1.y] != null)
          return false;
      }
      return true;
    }
    //incorrect move
    if (abs1 != abs2)
      return false;
    //diagonal
    int sign1 = 1, sign2 = 1;
    if (position2.x - position1.x < 0)
      sign1 = -1;
    if (position2.y - position1.y < 0)
      sign2 = -1;
      var currentPosition = new V2(position1.x+sign1, position1.y+sign2);
      int counter = 0;
      //counter in case I did any of the conditions wrong
      while(currentPosition.x != position2.x && currentPosition.y != position2.y && counter < 10){
        counter++;
        if(chessPieces[currentPosition.x][currentPosition.y] != null)
          return false;
        currentPosition = new V2(currentPosition.x + sign1, currentPosition.y + sign2);
      }
      return true;
  }

  public List<ChessPiece> initPieces() {
    var newPieces = new ArrayList<ChessPiece>();

    if (this.color == WHITE) {
      newPieces.add(new King(new V2(7, 3), WHITE));
      newPieces.add(new Queen(new V2(7, 4), WHITE));
      newPieces.add(new Rook(new V2(7, 0), WHITE));
      newPieces.add(new Rook(new V2(7, 7), WHITE));
      newPieces.add(new Bishop(new V2(7, 2), WHITE));
      newPieces.add(new Bishop(new V2(7, 5), WHITE));
      newPieces.add(new Knight(new V2(7, 1), WHITE));
      newPieces.add(new Knight(new V2(7, 6), WHITE));

      for (int i = 0; i < 8; i++)
        newPieces.add(new Pawn(new V2(6, i), WHITE));

      return newPieces;

    } else if (this.color == BLACK) {
      newPieces.add(new King(new V2(0, 3), BLACK));
      newPieces.add(new Queen(new V2(0, 4), BLACK));
      newPieces.add(new Rook(new V2(0, 0), BLACK));
      newPieces.add(new Rook(new V2(0, 7), BLACK));
      newPieces.add(new Bishop(new V2(0, 2), BLACK));
      newPieces.add(new Bishop(new V2(0, 5), BLACK));
      newPieces.add(new Knight(new V2(0, 1), BLACK));
      newPieces.add(new Knight(new V2(0, 6), BLACK));
      for (int i = 0; i < 8; i++)
        newPieces.add(new Pawn(new V2(1, i), BLACK));

      return newPieces;
    }

    throw new IllegalStateException("unknown color " + color);
  }

  public void nextMove(List<ChessPiece> otherPlayerPieces) {
    for (ChessPiece piece : pieces) {
      V2 position = piece.getPosition();
    }
  }

  public List<ChessPiece> getPieces() {
    return new ArrayList<ChessPiece>(this.pieces);
  }

  public List<ChessPiece> getPiecesToMove(ChessPiece[][] chesspieces) {
    var possiblePieces = new ArrayList<ChessPiece>();
    for (int i = 0; i < this.pieces.size(); i++) {

      var possiblePositions = this.pieces.get(i).getPossibleMoves();
      for (int j = 0; j < possiblePositions.size(); j++) {
        var positionAfterMove = this.pieces.get(i).getPosition().plus(possiblePositions.get(j));
        if (isMoveCorrect(this.pieces.get(i).getPosition(), positionAfterMove, chesspieces)) {
          possiblePieces.add(this.pieces.get(i));
          break;
        }
      }
    }
    return possiblePieces;
  }


  public void removePiece(V2 position) {
    var newPieces = new ArrayList <ChessPiece> ();
    for (int i = 0; i < this.pieces.size(); i++) {
      if (this.pieces.get(i).getPosition().x == position.x
           && this.pieces.get(i).getPosition().y == position.y) {
        for(int j=0; j<i; j++)
          newPieces.add(this.pieces.get(j));
        for(int j=i+1; j<this.pieces.size(); j++)
          newPieces.add(this.pieces.get(j));
          this.setPieces(newPieces);
        return;
      }
    }
  }

  public boolean isKingAlive() {
    for (int i = 0; i < this.pieces.size(); i++) {
      if (this.pieces.get(i).getClass() == King.class)
        return true;
    }
    return false;
  }
  public void setPieces(List <ChessPiece> pieces){
    this.pieces = pieces;
  }
}
