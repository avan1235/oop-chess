package pl.edu.mimuw.chess;

import java.util.LinkedList;
import java.util.List;

public class ChessBoard {
  public static final int BOARD_SIZE = 8;
  private static final int MAX_NUMBER_OF_MOVES = 50;
  private static final int SLEEP_LENGTH = 1000;
  private static final char[][] CLEAR_BOARD = getClearBoardRepresentation();

  private final Player blackPlayer;
  private final Player whitePlayer;

  public ChessBoard() {
    this.blackPlayer = new Player(ChessColor.BLACK, getNewListOfBlackPieces());
    this.whitePlayer = new Player(ChessColor.WHITE, getNewListOfWhitePieces());
  }

  public void simulateGame() {
    System.out.println(this);
    Util.sleep(SLEEP_LENGTH);
    Util.clearConsole();

    for (int i = 0; i < MAX_NUMBER_OF_MOVES; i++) {
      if (simulateMove(blackPlayer)) return;
      if (simulateMove(whitePlayer)) return;
    }

    System.out.println("Draw!");
  }

  public boolean isNotOnBoard(Vector position) {
    return position.x < 0 || position.x >= BOARD_SIZE || position.y < 0 || position.y >= BOARD_SIZE;
  }

  public ChessPiece getPiece(Vector position) {
    for (var piece : blackPlayer.getPieces()) if (piece.getPosition().equals(position)) return piece;
    for (var piece : whitePlayer.getPieces()) if (piece.getPosition().equals(position)) return piece;
    return null;
  }

  public void beatPiece(ChessPiece piece) {
    if (piece.getColor() == ChessColor.BLACK) {
      blackPlayer.getPieces().remove(piece);
    } else {
      whitePlayer.getPieces().remove(piece);
    }
  }

  @Override
  public String toString() {
    final var sb = new StringBuilder();
    final var board = new char[BOARD_SIZE * 2 + 1][BOARD_SIZE * 2 + 1];

    for (var piece : blackPlayer.getPieces()) {
      board[piece.getPosition().y * 2 + 1][piece.getPosition().x * 2 + 1] = piece.getSymbol();
    }
    for (var piece : whitePlayer.getPieces()) {
      board[piece.getPosition().y * 2 + 1][piece.getPosition().x * 2 + 1] = piece.getSymbol();
    }

    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if (board[i][j] == '\u0000') {
          sb.append(CLEAR_BOARD[i][j]);
        } else {
          sb.append(board[i][j]);
        }
      }
      sb.append('\n');
    }

    return sb.toString();
  }

  /**
   * Simulates a move for the given player.
   * @param player the player to simulate a move for.
   * @return true if the match is over, false otherwise.
   */
  private boolean simulateMove(Player player) {
    final var sb = new StringBuilder();

    if (!player.makeRandomMove(this)) {
      if (player.getColor() == ChessColor.BLACK) sb.append("Black");
      else sb.append("White");
      sb.append(" can't make a move!");
      System.out.println(sb);
      return true;
    }

    System.out.println(this);
    Util.sleep(SLEEP_LENGTH);

    if (!player.hasAKing()) {
      if (player.getColor() == ChessColor.BLACK) sb.append("Black");
      else sb.append("White");
      sb.append(" lost his king!");
      System.out.println(sb);
      return true;
    }

    Util.clearConsole();
    return false;
  }

  /**
   * @return an array without any pieces on it.
   */
  private static char[][] getClearBoardRepresentation() {
    char[][] result = new char[BOARD_SIZE * 2 + 1][BOARD_SIZE * 2 + 1];

    for (int i = 0; i < result.length; i++) {
      for (int j = 0; j < result[i].length; j++) {
        if (i == 0) {
          if (j == 0) result[i][j] = '╔';
          else if (j == result[i].length - 1) result[i][j] = '╗';
          else if (j % 2 == 0) result[i][j] = '╤';
          else result[i][j] = '═';
        } else if (i == result.length - 1) {
          if (j == 0) result[i][j] = '╚';
          else if (j == result[i].length - 1) result[i][j] = '╝';
          else if (j % 2 == 0) result[i][j] = '╧';
          else result[i][j] = '═';
        } else if (i % 2 == 0) {
          if (j == 0 || j == result[i].length - 1) result[i][j] = '║';
          else if (j % 2 == 0) result[i][j] = '┼';
          else result[i][j] = '─';
        } else {
          if (j == 0 || j == result[i].length - 1) result[i][j] = '║';
          else if (j % 2 == 0) result[i][j] = '│';
          else if ((i + j) / 2 % 2 == 1) result[i][j] = ' ';
          else result[i][j] = '█';
        }
      }
    }

    return result;
  }

  /**
   * @return a new list of black pieces, each of them on their starting position.
   */
  private static List<ChessPiece> getNewListOfBlackPieces() {
    final var result = new LinkedList<ChessPiece>();

    for (int i = 0; i < BOARD_SIZE; i++) result.add(new Pawn(ChessColor.BLACK, i, BOARD_SIZE - 2));
    result.add(new Rook(ChessColor.BLACK, 0, BOARD_SIZE - 1));
    result.add(new Rook(ChessColor.BLACK, BOARD_SIZE - 1, BOARD_SIZE - 1));
    result.add(new Knight(ChessColor.BLACK, 1, BOARD_SIZE - 1));
    result.add(new Knight(ChessColor.BLACK, BOARD_SIZE - 2, BOARD_SIZE - 1));
    result.add(new Bishop(ChessColor.BLACK, 2, BOARD_SIZE - 1));
    result.add(new Bishop(ChessColor.BLACK, BOARD_SIZE - 3, BOARD_SIZE - 1));
    result.add(new Queen(ChessColor.BLACK, 3, BOARD_SIZE - 1));
    result.add(new King(ChessColor.BLACK, 4, BOARD_SIZE - 1));

    return result;
  }

  /**
   * @return a new list of white pieces, each of them on their starting position.
   */
  private static List<ChessPiece> getNewListOfWhitePieces() {
    final var result = new LinkedList<ChessPiece>();

    for (int i = 0; i < BOARD_SIZE; i++) result.add(new Pawn(ChessColor.WHITE, i, 1));
    result.add(new Rook(ChessColor.WHITE, 0, 0));
    result.add(new Rook(ChessColor.WHITE, BOARD_SIZE - 1, 0));
    result.add(new Knight(ChessColor.WHITE, 1, 0));
    result.add(new Knight(ChessColor.WHITE, BOARD_SIZE - 2, 0));
    result.add(new Bishop(ChessColor.WHITE, 2, 0));
    result.add(new Bishop(ChessColor.WHITE, BOARD_SIZE - 3, 0));
    result.add(new Queen(ChessColor.WHITE, 3, 0));
    result.add(new King(ChessColor.WHITE, 4, 0));

    return result;
  }
}
