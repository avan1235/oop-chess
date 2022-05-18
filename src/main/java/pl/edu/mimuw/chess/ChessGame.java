package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.pieces.*;

import java.util.*;

import pl.edu.mimuw.chess.Util;

import static pl.edu.mimuw.chess.V2.*;

public class ChessGame {
  private final Set<ChessPiece> whitePieces, blackPieces;

  public ChessGame() {
    whitePieces = generatePieceSet(ChessColor.WHITE);
    blackPieces = generatePieceSet(ChessColor.BLACK);
  }

  private static Set<ChessPiece> generatePieceSet(ChessColor color) {
    int pawnY = color == ChessColor.WHITE ? 1 : 6;
    int otherY = color == ChessColor.WHITE ? 0 : 7;
    int kingX = color == ChessColor.WHITE ? 4 : 3;
    int queenX = color == ChessColor.WHITE ? 3 : 4;

    var s = new HashSet<ChessPiece>();

    for (int i = 0; i < 8; i++) {
      s.add(new PawnPiece(v(i, pawnY), color));
    }

    s.add(new RookPiece(v(0, otherY), color));
    s.add(new RookPiece(v(7, otherY), color));

    s.add(new KnightPiece(v(1, otherY), color));
    s.add(new KnightPiece(v(6, otherY), color));

    s.add(new BishopPiece(v(2, otherY), color));
    s.add(new BishopPiece(v(5, otherY), color));

    s.add(new QueenPiece(v(queenX, otherY), color));
    s.add(new KingPiece(v(kingX, otherY), color));

    return s;
  }

  private static ChessPiece getPieceFromPosition(Set<ChessPiece> s, V2 pos) {
    for (var piece : s) {
      if (piece.getPosition().equals(pos))
        return piece;
    }

    return null;
  }

  private List<List<V2>> filterOutOfBoardMoves(List<List<V2>> l) {
    List<List<V2>> ret = new ArrayList<>();

    for (var lst : l) {
      List<V2> partialList = new ArrayList<>();

      for (var move : lst) {
        if (!ChessBoard.isOnBoard(move)) {
          break;
        } else {
          partialList.add(move);
        }
      }

      ret.add(partialList);
    }

    return ret;
  }

  private List<V2> getLegalMoves(ChessPiece piece, Set<ChessPiece> myPieces, Set<ChessPiece> opponentsPieces) {
    List<List<V2>> allMoves = piece.getAbsoluteMoves();
    List<List<V2>> movesOnBoard = filterOutOfBoardMoves(allMoves);
    List<V2> legalMoves = new ArrayList<>();

    for (var lst : movesOnBoard) {
      for (var move : lst) {
        ChessPiece myPiece = getPieceFromPosition(myPieces, move);
        ChessPiece otherPiece = getPieceFromPosition(opponentsPieces, move);

        if (myPiece != null || otherPiece != null) {
          break;
        } else {
          legalMoves.add(move);
        }
      }
    }

    return legalMoves;
  }

  private List<V2> getLegalAttacks(ChessPiece piece, Set<ChessPiece> myPieces, Set<ChessPiece> opponentsPieces) {
    List<List<V2>> allMoves = piece.getAbsoluteAttackMoves();
    List<List<V2>> movesOnBoard = filterOutOfBoardMoves(allMoves);
    List<V2> legalAttacks = new ArrayList<>();

    for (var lst : movesOnBoard) {
      for (var move : lst) {
        ChessPiece myPiece = getPieceFromPosition(myPieces, move);
        ChessPiece otherPiece = getPieceFromPosition(opponentsPieces, move);

        if (myPiece != null) {
          break;
        }

        if (otherPiece != null) {
          legalAttacks.add(move);
        }
      }
    }


    return legalAttacks;
  }

  private boolean makeMove(ChessPiece piece, V2 move, Set<ChessPiece> myPieces, Set<ChessPiece> opponentsPieces) {
    ChessPiece myPiece = getPieceFromPosition(myPieces, piece.getPosition());
    ChessPiece otherPiece = getPieceFromPosition(opponentsPieces, move);

    if (otherPiece != null) {
      opponentsPieces.removeIf(e -> e.getPosition().equals(move));
    }

    myPiece.setPosition(move);

    return otherPiece != null && otherPiece.isKing();
  }

  private boolean makeRandomMove(ChessColor color) {
    Set<ChessPiece> myPieces = color == ChessColor.WHITE ? whitePieces : blackPieces;
    Set<ChessPiece> opponentsPieces = color == ChessColor.WHITE ? blackPieces : whitePieces;

    List<ChessPiece> myPiecesList = new ArrayList<>();

    myPiecesList.addAll(myPieces);
    Collections.shuffle(myPiecesList);

    for (ChessPiece piece : myPiecesList) {
      List<V2> moves = getLegalMoves(piece, myPieces, opponentsPieces);
      moves.addAll(getLegalAttacks(piece, myPieces, opponentsPieces));

      if (moves.size() == 0) {
        continue;
      }

      Collections.shuffle(moves);
      return makeMove(piece, moves.get(0), myPieces, opponentsPieces);
    }

    return true;
  }

  public String toString() {
    final String upperEdge = "╔═╤═╤═╤═╤═╤═╤═╤═╗\n";
    final String middleEdge = "╟─┼─┼─┼─┼─┼─┼─┼─╢\n";
    final String lowerEdge = "╚═╧═╧═╧═╧═╧═╧═╧═╝\n";
    final String sideEdge = "║";
    final String whiteSquare = " ";
    final String blackSquare = "█";
    final String squareSeparator = "│";

    var sb = new StringBuilder();
    sb.append(upperEdge);

    for (int row = 7; row >= 0; row--) {
      sb.append(sideEdge);
      for (int column = 0; column <= 7; column++) {
        ChessPiece curPiece = getPieceFromPosition(whitePieces, v(column, row));

        curPiece = curPiece == null ?
          getPieceFromPosition(blackPieces, v(column, row)) :
          curPiece;

        if (curPiece == null) {
          sb.append(
            (row + column) % 2 == 0 ? blackSquare : whiteSquare
          );
        } else {
          sb.append(curPiece.representation());
        }

        if (column != 7) {
          sb.append(squareSeparator);
        }
      }
      sb.append(sideEdge).append("\n");

      if (row != 0) {
        sb.append(middleEdge);
      }
    }

    sb.append(lowerEdge);
    return sb.toString();
  }

  private void printToScreen() {
    Util.clearConsole();
    System.out.println(this);
  }

  public void runSimulation() throws InterruptedException {
    for (int i = 0; i < 100; i++) {
      if (!this.makeRandomMove(i % 2 == 0 ? ChessColor.WHITE : ChessColor.BLACK)) {
        this.printToScreen();
        Thread.sleep(1000);
      } else {
        return;
      }
    }
  }
}
