package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ChessGamePlayer {

  private final Random random;
  private static int BOARD_SIZE = 8;
  private final List<ChessPiece> pieces;
  public final ChessColor color;

  private final ChessBoard board;

  public ChessGamePlayer(ChessColor color, ChessBoard board) {
    this.random = new Random();
    this.board = board;
    this.color = color;
    this.pieces = new ArrayList<>();
    initPieces();
  }

  private void initPieces() {
    int pawnRow;
    int pieceRow;
    if (color == ChessColor.WHITE) {
      pawnRow = 1;
      pieceRow = 0;
    } else {
      pawnRow = BOARD_SIZE - 2;
      pieceRow = BOARD_SIZE - 1;
    }
    for (int i = 0; i < BOARD_SIZE; i++)
      pieces.add(new PawnPiece(board, new V2(i, pawnRow), color));
    pieces.add(new RookPiece(board, new V2(0, pieceRow), color));
    pieces.add(new RookPiece(board, new V2(7, pieceRow), color));
    pieces.add(new KnightPiece(board, new V2(1, pieceRow), color));
    pieces.add(new KnightPiece(board, new V2(6, pieceRow), color));
    pieces.add(new BishopPiece(board, new V2(2, pieceRow), color));
    pieces.add(new BishopPiece(board, new V2(5, pieceRow), color));
    pieces.add(new QueenPiece(board, new V2(3, pieceRow), color));
    pieces.add(new KingPiece(board, new V2(4, pieceRow), color));
  }

  public Move getMove() throws EndGameException {
    ArrayList<Move> moves = new ArrayList<>();
    for (ChessPiece p : pieces)
      moves.addAll(p.getPossibleMoves());
    if(moves.size() == 0)
      throw new EndGameException();
    return moves.get(random.nextInt(moves.size()));
  }

  public ChessPiece getPieceOnTile(V2 v) {
    for (ChessPiece p : pieces)
      if (p.getPosition().equals(v))
        return p;
    return null;
  }

  public void removePieceFromTile(V2 v) throws EndGameException {
    int index = -1;
    for (int i = 0; i < pieces.size(); i++) {
      if (pieces.get(i).getPosition().equals(v)) {
        if(pieces.get(i) instanceof KingPiece)
          throw new EndGameException();
        pieces.remove(i);
        i--;
      }
    }
  }
}
