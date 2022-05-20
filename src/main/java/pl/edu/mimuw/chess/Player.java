package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.edu.mimuw.chess.ChessBoard.*;

public class Player {
  private final List<ChessPiece> pieces;
  private final ChessColor color;

  public Player(ChessColor color) {
    this.color = color;
    this.pieces = createPieces();
  }

  public List<ChessPiece> createPieces() {
    List<ChessPiece> pieces = new ArrayList<ChessPiece>();

    int pawnY;
    int figureY;

    if (this.color == ChessColor.WHITE) {
      pawnY = 1;
      figureY = 0;
    } else {
      pawnY = 6;
      figureY = 7;
    }

    pieces.add(new KingPiece(new V2(4, figureY), this.color));
    pieces.add(new QueenPiece(new V2(3, figureY), this.color));
    pieces.add(new RookPiece(new V2(0, figureY), this.color));
    pieces.add(new RookPiece(new V2(7, figureY), this.color));
    pieces.add(new KnightPiece(new V2(1, figureY), this.color));
    pieces.add(new KnightPiece(new V2(6, figureY), this.color));
    pieces.add(new BishopPiece(new V2(2, figureY), this.color));
    pieces.add(new BishopPiece(new V2(5, figureY), this.color));

    for (int i = 0; i < BOARD_SIZE; i++) {
      pieces.add(new PawnPiece(new V2(i, pawnY), this.color));
    }

    return pieces;
  }

  public List<ChessPiece> getPieces() {
    return this.pieces;
  }
}
