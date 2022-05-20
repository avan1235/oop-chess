package pl.edu.mimuw.chess;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Player {
  private static final Random RANDOM = new Random();

  private final ChessColor color;
  private final List<ChessPiece> pieces;

  public Player(ChessColor color, List<ChessPiece> pieces) {
    this.color = color;
    this.pieces = pieces;
  }

  public List<ChessPiece> getPieces() {
    return this.pieces;
  }

  public boolean hasAKing() {
    for (var piece : this.pieces) {
      if (piece.isKing()) return true;
    }
    return false;
  }

  public boolean makeRandomMove(ChessBoard board) {
    final var usefulPieces = this.pieces.stream()
      .filter(piece -> piece.getPossibleMoves(board).size() > 0).toArray(ChessPiece[]::new);

    if (usefulPieces.length == 0) {
      return false;
    } else {
      final var piece = usefulPieces[RANDOM.nextInt(usefulPieces.length)];
      final var usefulDirections = piece.getPossibleMoves(board).stream().filter(direction -> direction.size() > 0).collect(Collectors.toList());
      final var direction = usefulDirections.get(RANDOM.nextInt(usefulDirections.size()));

      final var newPosition = piece.getPosition().plus(direction.get(RANDOM.nextInt(direction.size())));
      final var beatenPiece = board.getPiece(newPosition);
      if (beatenPiece == null) piece.setPosition(newPosition);
      else {
        board.beatPiece(beatenPiece);
        piece.setPosition(newPosition);
      }
      return true;
    }
  }
}
