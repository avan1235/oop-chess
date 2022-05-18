package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.pieces.KingPiece;
import pl.edu.mimuw.chess.pieces.Piece;

import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class ChessGame {
  public final ChessPlayer whitePlayer;
  public final ChessPlayer blackPlayer;
  public final ChessBoard board;
  public final Random rng;
  public Piece.Color nextMove;

  public ChessGame(ChessPlayer whitePlayer, ChessPlayer blackPlayer, ChessBoard board, Random rng) {
    this.whitePlayer = whitePlayer;
    this.blackPlayer = blackPlayer;
    this.board = board;
    this.nextMove = Piece.Color.WHITE;
    this.rng = rng;
  }

  public ChessGame(Random rng) {
    this.board = new ChessBoard();
    this.whitePlayer = new ChessPlayer(
      Piece.Color.WHITE,
      board.getPieces().stream().filter((el) -> el.color == Piece.Color.WHITE).collect(Collectors.toList())
    );
    this.blackPlayer = new ChessPlayer(
      Piece.Color.BLACK,
      board.getPieces().stream().filter((el) -> el.color == Piece.Color.BLACK).collect(Collectors.toList())
    );
    this.nextMove = Piece.Color.WHITE;
    this.rng = rng;
  }

  public ChessGame() {
    this(new Random());
  }

  public void printBoard() {
    Util.clearConsole();
    System.out.println(board.toString());
  }

  public void nextMove() throws NoAvailableMoveException, CapturedKingException {
    ChessPlayer actPlayer = nextMove == Piece.Color.WHITE ? whitePlayer : blackPlayer;
    ChessPlayer opponent = nextMove == Piece.Color.BLACK ? whitePlayer : blackPlayer;
    final Map<V2, Piece> boardState = board.getBoard();
    Piece movedPiece = actPlayer.makeMove((pos) -> {
      if (!boardState.containsKey(pos)) return ChessBoard.FieldState.EMPTY;
      if (boardState.get(pos).color.equals(actPlayer.color)) return ChessBoard.FieldState.ALLY_PIECE;
      else return ChessBoard.FieldState.ENEMY_PIECE;
    }, rng);
    Piece capturedPiece = board.findAndHandleCaptured(movedPiece);
    if (capturedPiece != null) {
      opponent.removePiece(capturedPiece);
      if (capturedPiece.getClass().equals(KingPiece.class))
        throw new CapturedKingException(capturedPiece.color);
    }
    nextMove = nextMove == Piece.Color.WHITE ? Piece.Color.BLACK : Piece.Color.WHITE;
  }

  public static class NoAvailableMoveException extends Throwable {
  }

  public static class CapturedKingException extends Throwable {
    public final Piece.Color color;

    CapturedKingException(Piece.Color color) {
      this.color = color;
    }
  }
}
