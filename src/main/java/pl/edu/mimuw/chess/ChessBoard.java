package pl.edu.mimuw.chess;

public class ChessBoard {
  public static final int BOARD_SIZE = 8;

  private ChessGamePlayer currPlayer;
  private ChessGamePlayer otherPlayer;

  public ChessBoard() {
    otherPlayer = new ChessGamePlayer(ChessColor.BLACK, this);
    currPlayer = new ChessGamePlayer(ChessColor.WHITE, this);
  }

  public ChessPiece getPieceOnTile(V2 dest) {
    ChessPiece output = null;
    output = currPlayer.getPieceOnTile(dest);
    if (output != null)
      return output;
    output = otherPlayer.getPieceOnTile(dest);
    return output;
  }

  public boolean isOnBoard(V2 dest) {
    return 0 <= dest.x && dest.x < BOARD_SIZE
      && 0 <= dest.y && dest.y < BOARD_SIZE;
  }


  public boolean playTurn() {
    Move move;
    try {
      move = currPlayer.getMove();
      otherPlayer.removePieceFromTile(move.dest);
      move.piece.setPosition(move.dest);
    } catch (EndGameException ex) {
      return false;
    }
    ChessGamePlayer temp = currPlayer;
    currPlayer = otherPlayer;
    otherPlayer = temp;
    return true;
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("╔═╤═╤═╤═╤═╤═╤═╤═╗\n");
    for (int i = 0; i < BOARD_SIZE; i++) {
      sb.append("║");
      for(int j=0; j<BOARD_SIZE; j++){
        ChessPiece p = currPlayer.getPieceOnTile(new V2(j, BOARD_SIZE - 1 - i));
        if(p == null)
          p = otherPlayer.getPieceOnTile(new V2(j, BOARD_SIZE - 1 - i));
        if(p == null)
          sb.append(" ");
        else
          sb.append(p.representation());
        if(j<BOARD_SIZE -1)
          sb.append("│");
        else
          sb.append("║\n");
      }
      if (i < BOARD_SIZE - 1)
        sb.append("╟─┼─┼─┼─┼─┼─┼─┼─╢\n");
      else
        sb.append("╚═╧═╧═╧═╧═╧═╧═╧═╝\n");

    }
    return sb.toString();
  }
}
