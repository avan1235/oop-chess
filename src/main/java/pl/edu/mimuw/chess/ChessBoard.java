package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static pl.edu.mimuw.chess.ChessColor.*;
import static pl.edu.mimuw.chess.V2.v;
import static pl.edu.mimuw.chess.Util.*;

public class ChessBoard {

  public static final int BOARD_SIZE = 8;

  private final Player whitePlayer;
  private final Player blackPlayer;

  public ChessBoard() {
    this.whitePlayer = new Player(WHITE);
    this.blackPlayer = new Player(BLACK);
  }

  public static boolean isOnBoard(V2 position) {
    return position.x > -1 && position.x < BOARD_SIZE &&
      position.y > -1 && position.y < BOARD_SIZE;
  }

  private String[][] fields() {
    String[][] result = new String[BOARD_SIZE][BOARD_SIZE];
    for (int i = 0; i < BOARD_SIZE; i++) {
      for (int j = 0; j < BOARD_SIZE; j++) {
        result[i][j] = square(v(i, j));
      }
    }
    for (var p : whitePlayer.getPieces()) {
      result[p.getPosition().x][p.getPosition().y] = p.representation();
    }
    for (var p : blackPlayer.getPieces()) {
      result[p.getPosition().x][p.getPosition().y] = p.representation();
    }
    return result;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    String[][] fields = this.fields();
    sb.append("╔═╤═╤═╤═╤═╤═╤═╤═╗\n");
    for (int i = BOARD_SIZE - 1; i >= 0; i--) {
      sb.append("║");
      for (int j = 0; j < BOARD_SIZE - 1; j++) {
        sb.append(fields[i][j]).append("│");
      }
      sb.append(fields[i][BOARD_SIZE - 1]).append("╢\n");
      if (i < BOARD_SIZE - 1) sb.append("╟─┼─┼─┼─┼─┼─┼─┼─╢\n");
      else sb.append("\n╚═╧═╧═╧═╧═╧═╧═╧═╝\n");
    }
    return sb.toString();
  }

  //returns color of the piece occupying given field or null if the field is free
  private ChessColor isOccupied(V2 position) {
    assert (isOnBoard(position));
    for (var p : this.whitePlayer.getPieces()) {
      if (position.equals(p.getPosition())) return WHITE;
    }
    for (var p : this.blackPlayer.getPieces()) {
      if (position.equals(p.getPosition())) return BLACK;
    }
    return null;
  }

  private List<Move> possiblePieceMoves(AbstractPiece piece, int pieceIndex) {
    List<Move> moves = new ArrayList<>();

    for (var d : piece.getPossibleMoves()) {
      int i;
      for (i = 0; i < d.size() && isOnBoard(d.get(i).plus(piece.getPosition()))
        && isOccupied(d.get(i).plus(piece.getPosition())) == null; i++) {

        moves.add(new Move(pieceIndex, d.get(i)));
      }
      if (i < d.size() && isOnBoard(d.get(i).plus(piece.getPosition()))
        && isOccupied(d.get(i).plus(piece.getPosition())) != piece.getColor()) {
        moves.add(new Move(pieceIndex, d.get(i)));
      }
    }
    return moves;
  }

  private Player opponent(Player player) {
    return player.getColor() == WHITE ? this.blackPlayer : this.whitePlayer;
  }

  private void move(Player player) {
    List<Move> possibleMoves = new ArrayList<>();
    for (int i = 0; i < player.getPieces().size(); i++) {
      possibleMoves.addAll(possiblePieceMoves(player.getPieces().get(i), i));
    }

    Random r = new Random();
    int moveId = r.nextInt(possibleMoves.size());
    opponent(player).deletePiece(possibleMoves.get(moveId).getPosition());
    player.makeMove(possibleMoves.get(moveId));
  }

  private boolean canContinue(Player player) {
    for (int i = 0; i < player.getPieces().size(); i++) {
      if (possiblePieceMoves(player.getPieces().get(i), i).size() != 0) return player.hasKing();
    }
    return false;
  }

  public void play() {
    Util.clearConsole();
    int i;
    for (i = 0; i < 50; i++) {
      System.out.println(this);
      System.out.println("\n");
      if (!this.canContinue(whitePlayer)) {
        if (whitePlayer.hasKing()) System.out.println("white cannot move");
        else System.out.println("white lost his king");
        break;
      }
      move(this.whitePlayer);
      System.out.println(this);
      System.out.println("\n");
      if (!this.canContinue(blackPlayer)) {
        if (blackPlayer.hasKing()) System.out.println("black cannot move");
        else System.out.println("black lost his king");
        break;
      }
      move(this.blackPlayer);
    }
    if(i==50) {
      System.out.println(this);
      System.out.println("\n");
    }
  }
}
