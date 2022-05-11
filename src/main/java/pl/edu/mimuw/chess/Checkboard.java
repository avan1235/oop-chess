package pl.edu.mimuw.chess;

import java.util.*;

import static pl.edu.mimuw.chess.Player.isMoveCorrect;

public class Checkboard {
  public static final int BOARD_SIZE = 8;
  private Player playerWhite;
  private Player playerBlack;
  private ChessPiece[][] piecesArray;

  private int turn;

  private int totalMoves;

  public static boolean isOnBoard(V2 position) {
    return position.x > -1 && position.x < BOARD_SIZE &&
      position.y > -1 && position.y < BOARD_SIZE;
  }

  // public static boolean correctMove (V2 position1, V2 position2)
  public Checkboard() {
    this.playerWhite = new Player(ChessColor.WHITE);
    this.playerBlack = new Player(ChessColor.BLACK);
    this.piecesArray = new ChessPiece[8][8];
    this.totalMoves = 0;
    this.turn = 0;
  }

  private void fill(Player player) {
    var list = player.getPieces();
    //adding all pieces to checkerboard
    for (int i = 0; i < list.size(); i++) {
      var position = list.get(i).getPosition();
      this.piecesArray[position.x][position.y] = list.get(i);
    }
  }

  public boolean play() {

    if (this.totalMoves == 100) {
      System.out.println("Draw, too many moves!");
      return false;
    }

    System.out.println("\n");
    System.out.println(this.toString());
    System.out.println("\n");

    this.totalMoves++;

    List<ChessPiece> possiblePieces = new ArrayList<ChessPiece>();
    if (turn == 0)
      possiblePieces = this.playerWhite.getPiecesToMove(this.piecesArray);
    else
      possiblePieces = this.playerBlack.getPiecesToMove(this.piecesArray);

    if (possiblePieces.size() == 0) {
      if (turn == 0)
        System.out.println("Draw, first player cannot make another move");
      else
        System.out.println("Draw, second player cannot make another move");
      return false;
    }
    Random random = new Random();
    int pieceToMove = random.nextInt(possiblePieces.size());

    ChessPiece piece = possiblePieces.get(pieceToMove);
    var possibleMoves = new ArrayList<V2>();
    var allMoves = piece.getPossibleMoves();
    var currentPosition = piece.getPosition();
    for (int i = 0; i < allMoves.size(); i++) {
      var newPosition = piece.getPosition().plus(allMoves.get(i));
      if (isMoveCorrect(currentPosition, newPosition, this.piecesArray)) {
        possibleMoves.add(newPosition);
      }
    }

    if (possibleMoves.size() == 0) {
      System.out.println("Unknown mistake, firstly indicated possible moves with this piece, now they can't be found");
      return false;
    }
    random = new Random();
    int moveToMake = random.nextInt(possibleMoves.size());
    int x = possibleMoves.get(moveToMake).x;
    int y = possibleMoves.get(moveToMake).y;
    if (turn == 0) {
      if (this.piecesArray[x][y] == null) {
        piece.setPosition(new V2(x, y));
      } else if (this.piecesArray[x][y].getColor() == ChessColor.BLACK) {

        this.playerBlack.removePiece(new V2(x, y));
        if (!this.playerBlack.isKingAlive()) {

          piece.setPosition(new V2(x, y));
          System.out.println("First player won!");


          return false;
        }
        piece.setPosition(new V2(x, y));
      }
    } else {
      if (this.piecesArray[x][y] == null) {
        piece.setPosition(new V2(x, y));
      } else if (this.piecesArray[x][y].getColor() == ChessColor.WHITE) {
        this.playerWhite.removePiece(new V2(x, y));
        if (!this.playerWhite.isKingAlive()) {
          piece.setPosition(new V2(x, y));
          System.out.println("Second player won!");
          return false;
        }
      }
      piece.setPosition(new V2(x, y));
    }
    this.turn = 1 - this.turn;
    return true;
  }

  public String toString() {
    this.piecesArray = new ChessPiece[8][8];

    this.fill(this.playerWhite);
    this.fill(this.playerBlack);
    var sb = new StringBuilder();
    sb.append("╔═╤═╤═╤═╤═╤═╤═╤═╗\n");
    for (int i = 0; i < BOARD_SIZE; i++) {
      sb.append("║");
      for (int j = 0; j < BOARD_SIZE; j++) {
        if (piecesArray[i][j] != null) {
          sb.append(piecesArray[i][j].representation());
        } else if ((i + j) % 2 == 0) {
          sb.append(" ");
        } else {
          sb.append("█");
        }
        if (j != BOARD_SIZE - 1)
          sb.append("|");
      }
      sb.append("║\n");
      if (i != BOARD_SIZE - 1)
        sb.append("╟─┼─┼─┼─┼─┼─┼─┼─╢\n");
    }
    sb.append("╚═╧═╧═╧═╧═╧═╧═╧═╝");
    return sb.toString();
  }
}
