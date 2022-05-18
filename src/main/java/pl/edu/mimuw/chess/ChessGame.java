package pl.edu.mimuw.chess;

import pl.edu.mimuw.chess.pieces.*;

import java.util.HashSet;
import java.util.Set;

import pl.edu.mimuw.chess.Util;
import static pl.edu.mimuw.chess.V2.*;

public class ChessGame {
  private final ChessBoard board;
  private final Set<ChessPiece> whitePieces, blackPieces;

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
      if (piece.getPosition() == pos)
        return piece;
    }

    return null;
  }

  public void printToScreen() {
    final String upperEdge = "╔═╤═╤═╤═╤═╤═╤═╤═╗\n";
    final String middleEdge = "╟─┼─┼─┼─┼─┼─┼─┼─╢\n";
    final String lowerEdge = "╚═╧═╧═╧═╧═╧═╧═╧═╝\n";
    final String sideEdge = "║";
    final String whiteSquare = " ";
    final String blackSquare = "█";
    final String squareSeparator = "│";

    var sb = new StringBuilder();
    sb.append(upperEdge);

    for(int row = 7; row >= 0; row--) {
      sb.append(sideEdge);
      for(int column = 0; column <= 7; column++) {
        ChessPiece curPiece = getPieceFromPosition(whitePieces, v(row, column));
        curPiece = curPiece == null ?
          getPieceFromPosition(blackPieces, v(row, column)) :
          curPiece;

        if(curPiece == null) {
          sb.append(
            (row + column) % 2 == 0 ? whiteSquare : blackSquare
          );
        } else {
          sb.append(curPiece.representation());
        }

        if(column != 7) {
          sb.append(squareSeparator);
        }
      }
      sb.append(sideEdge).append("\n");
      sb.append(middleEdge);
    }

    sb.append(lowerEdge);

    Util.clearConsole();
    System.out.println(sb);
  }

  public ChessGame() {
    board = new ChessBoard();
    whitePieces = generatePieceSet(ChessColor.WHITE);
    blackPieces = generatePieceSet(ChessColor.BLACK);
  }

  public void runSimulation() {

  }
}
