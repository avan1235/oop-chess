package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.ChessBoard.BOARD_SIZE;
import static pl.edu.mimuw.chess.ChessColor.*;
import static pl.edu.mimuw.chess.V2.v;

public class Player {

  private final List<AbstractPiece> pieces;
  private final ChessColor color;

  public Player(ChessColor color) {
    assert (color == WHITE || color == BLACK);
    this.color = color;
    int[] rows;
    if (color == WHITE) {
      rows = new int[]{0, 1};
    } else {
      rows = new int[]{7, 6};
    }

    this.pieces = new ArrayList<>();

    this.pieces.add(new RookPiece(v(rows[0], 0), this.color));
    this.pieces.add(new RookPiece(v(rows[0], 7), this.color));

    this.pieces.add(new KnightPiece(v(rows[0], 1), this.color));
    this.pieces.add(new KnightPiece(v(rows[0], 6), this.color));

    this.pieces.add(new BishopPiece(v(rows[0], 2), this.color));
    this.pieces.add(new BishopPiece(v(rows[0], 5), this.color));

    this.pieces.add(new QueenPiece(v(rows[0], 3), this.color));
    this.pieces.add(new KingPiece(v(rows[0], 4), this.color));

    for (int i = 0; i < BOARD_SIZE; i++) {
      this.pieces.add(new PawnPiece(v(rows[1], i), this.color));
    }
  }

  public ChessColor getColor() {
    return this.color;
  }

  public List<AbstractPiece> getPieces() {
    return new ArrayList<>(this.pieces);
  }

  public void deletePiece(V2 position) {
    for (var p : this.pieces) {
      if (position.equals(p.getPosition())) {
        this.pieces.remove(p);
        break;
      }
    }
  }

  public boolean hasKing() {
    for (var p : this.pieces) {
      if (p.representation().equals("♔") || p.representation().equals("♚")) return true;
    }
    return false;
  }

  public void makeMove(Move move) {
    this.pieces.get(move.getPieceIndex()).setPosition(this.pieces.get(move.getPieceIndex()).getPosition().plus(move.getPosition()));
  }

}
