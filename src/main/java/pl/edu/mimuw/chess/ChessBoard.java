package pl.edu.mimuw.chess;

public class ChessBoard {
  public static final int BOARD_SIZE = 8;
  private final ChessCell[][] cells;

  public ChessBoard(PlayerPieceSet blackSet, PlayerPieceSet whiteSet) {
    this.cells = new ChessCell[BOARD_SIZE][BOARD_SIZE];
    for (int j=2;j<BOARD_SIZE-2;j++) {
      for (int i=0;i<BOARD_SIZE;i++) {
        this.cells[i][j]=new ChessCell();
      }
    }
    for(int i=0;i<BOARD_SIZE;i++){
      this.cells[i][1]=new ChessCell(whiteSet.pawns.get(i));
      this.cells[i][6]=new ChessCell(blackSet.pawns.get(i));
    }
    for(int j: new int[]{0, 7}){
      PlayerPieceSet set=blackSet;
      if(j==0)set=whiteSet;
      this.cells[0][j]=new ChessCell(set.rooks.get(0));
      this.cells[7][j]=new ChessCell(set.rooks.get(1));
      this.cells[1][j]=new ChessCell(set.knights.get(0));
      this.cells[6][j]=new ChessCell(set.knights.get(1));
      this.cells[2][j]=new ChessCell(set.bishops.get(0));
      this.cells[5][j]=new ChessCell(set.bishops.get(1));
      this.cells[3][j]=new ChessCell(set.queen.get(0));
      this.cells[4][j]=new ChessCell(set.king.get(0));
    }
  }

  public static boolean isOnBoard(V2 position) {
    return position.x > -1 && position.x < BOARD_SIZE &&
      position.y > -1 && position.y < BOARD_SIZE;
  }

  public boolean moveAvailable( V2 move, ChessPiece piece) {
    if (move == null || piece == null) return false;
    V2 position=piece.getPosition().plus(move);
    if (!isOnBoard(position)) return false;
    if (this.cells[position.x][position.y].isOccupied()) {
      return this.cells[position.x][position.y].getPiece().getColor() != piece.getColor();
    }
    return true;
  }

  public void executeMove(V2 move, AbstractPiece piece, Player otherPlayer) {
    V2 prevPosition=piece.getPosition();
    this.cells[prevPosition.x][prevPosition.y].setOccupied(false);
    V2 position=piece.getPosition().plus(move);
    ChessCell currCell = this.cells[position.x][position.y];
    if (!currCell.isOccupied()) {
      currCell.setOccupied(true);
    } else {
      otherPlayer.pieces.pawns.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.rooks.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.knights.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.bishops.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.queen.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.king.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      otherPlayer.pieces.abstractPieces.removeIf(abstractPiece -> abstractPiece.getPosition() == position);
      if(otherPlayer.pieces.king.size()==0)otherPlayer.setKingAlive(false);
      otherPlayer.pieces.setSize(otherPlayer.pieces.getSize()-1);
    }
    piece.setPosition(position);
    currCell.setPiece(piece);
    this.cells[prevPosition.x][prevPosition.y].setPiece(null);
  }

  public void printCurrentState(){
      for(int j=0;j<BOARD_SIZE;j++){
        for(int i=0;i<BOARD_SIZE;i++){
          if(!this.cells[i][j].isOccupied()){
            System.out.print(' ');
          }
          else System.out.print(this.cells[i][j].getPiece().representation());
        }
        System.out.print('\n');
      }
      System.out.println('\n');
  }
}
