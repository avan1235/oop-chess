package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.pieces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChessBoard {
  private final List<Piece> pieces;
  private final List<Piece> capturedPieces;

  public ChessBoard() {
    this.pieces = new ArrayList<>();
    this.capturedPieces = new ArrayList<>();
    for (int row = 0; row < 2; row++) {
      pieces.add(new RookPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(0, 7 * row)));
      pieces.add(new RookPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(7, 7 * row)));

      pieces.add(new KnightPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(1, 7 * row)));
      pieces.add(new KnightPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(6, 7 * row)));

      pieces.add(new BishopPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(2, 7 * row)));
      pieces.add(new BishopPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(5, 7 * row)));

      pieces.add(new KingPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(4, 7 * row)));
      pieces.add(new QueenPiece(row == 0 ? Piece.Color.WHITE : Piece.Color.BLACK, new V2(3, 7 * row)));
    }
    for (int column = 0; column < 8; column++) {
      pieces.add(new PawnPiece(Piece.Color.WHITE, new V2(column, 1)));
      pieces.add(new PawnPiece(Piece.Color.BLACK, new V2(column, 6)));
    }
  }

  public Map<V2, Piece> getBoard() {
    Map<V2, Piece> result = new HashMap<>();
    for (var piece : pieces) result.put(piece.getPosition(), piece);
    return result;
  }

  public List<Piece> getPieces() {
    return List.copyOf(pieces);
  }

  public Piece findAndHandleCaptured(Piece capturer) {
    for (var piece : pieces)
      if (piece != capturer && piece.getPosition().equals(capturer.getPosition())) {
        pieces.remove(piece);
        capturedPieces.add(piece);
        return piece;
      }
    return null;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder("╔═╤═╤═╤═╤═╤═╤═╤═╗");
    capturedPieces.stream().filter(piece -> piece.color.equals(Piece.Color.WHITE)).forEach(builder::append);
    builder.append('\n');
    Map<V2, Piece> boardState = getBoard();
    for (int row = 7; row >= 0; row--) {
      builder.append('║');
      for (int column = 0; column < 8; column++) {
        V2 position = new V2(column, row);
        if (!boardState.containsKey(position))
          builder.append((row + column) % 2 == 0 ? ' ' : '█');
        else builder.append(boardState.get(position));
        builder.append('│');
      }
      builder.setCharAt(builder.length() - 1, '║');
      if (row > 0) builder.append("\n╟─┼─┼─┼─┼─┼─┼─┼─╢\n");
    }
    builder.append("\n╚═╧═╧═╧═╧═╧═╧═╧═╝");
    capturedPieces.stream().filter(piece -> piece.color.equals(Piece.Color.BLACK)).forEach(builder::append);
    return builder.toString();
  }

  public enum FieldState {EMPTY, ALLY_PIECE, ENEMY_PIECE}
}
