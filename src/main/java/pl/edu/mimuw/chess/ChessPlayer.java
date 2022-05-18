package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class ChessPlayer {
  public final Piece.Color color;
  private final List<Piece> pieces;

  public ChessPlayer(Piece.Color color, List<Piece> pieces) {
    this.color = color;
    this.pieces = new ArrayList<>(pieces);
  }

  public void removePiece(Piece piece) {
    pieces.remove(piece);
  }

  private void shufflePieces(Random rng) {
    for (int i = 0; i < pieces.size(); i++) {
      int randomIndexToSwap = rng.nextInt(pieces.size());
      Piece temp = pieces.get(randomIndexToSwap);
      pieces.set(randomIndexToSwap, pieces.get(i));
      pieces.set(i, temp);
    }
  }

  public Piece makeMove(Function<V2, ChessBoard.FieldState> getFieldState, Random rng) throws ChessGame.NoAvailableMoveException {
    shufflePieces(rng);
    for (var piece : pieces) {
      List<V2> availableMoves = piece.getAvailableMoves(getFieldState);
      if (availableMoves.size() == 0) continue;
      piece.moveTo(availableMoves.get(rng.nextInt(availableMoves.size())));
      return piece;
    }
    throw new ChessGame.NoAvailableMoveException();
  }
}
