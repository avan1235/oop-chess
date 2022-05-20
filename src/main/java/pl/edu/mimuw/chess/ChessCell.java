package pl.edu.mimuw.chess;

public class ChessCell {
  private boolean isOccupied;
  private ChessPiece piece;

  public boolean isOccupied() {
    return isOccupied;
  }

  public ChessPiece getPiece() {
    return piece;
  }

  public void setOccupied(boolean occupied) {
    isOccupied = occupied;
  }

  public void setPiece(ChessPiece piece) {
    this.piece = piece;
  }

  public ChessCell(){
    this.isOccupied=false;
    this.piece=null;
  }

  public ChessCell(ChessPiece piece){
    this.isOccupied=true;
    this.piece=piece;
  }
}
