package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.BoardSquare.*;
import static pl.edu.mimuw.chess.PieceColor.*;

import java.util.ArrayList;
import java.util.List;

public final class ChessBoard {

  public static final int boardSize = 8;
  /** Wyświetlana plansza */
  private String[][] boardState;
  /** Klasyfikacja pól - wolne, zajęte przez białe bierki, zajęte przez czarne bierki */
  private BoardSquare occupiedSpaces[][];
  /** Bierki każdego gracza */
  private List<AbstractPiece> AllPieces = generatePieces();
  /** Stałe znaki na białe i czarne pola */
  private static String whiteSquare = " ";
  private static String blackSquare = "█";

  private List<AbstractPiece> generatePieces() {
    List<AbstractPiece> newList = new ArrayList<AbstractPiece>();
    newList.add(new RookPiece(new Square(1, 1), WHITE));
    newList.add(new RookPiece(new Square(8, 1), WHITE));
    newList.add(new RookPiece(new Square(1, 8), BLACK));
    newList.add(new RookPiece(new Square(8, 8), BLACK));
    newList.add(new BishopPiece(new Square(3, 1), WHITE));
    newList.add(new BishopPiece(new Square(6, 1), WHITE));
    newList.add(new BishopPiece(new Square(3, 8), BLACK));
    newList.add(new BishopPiece(new Square(6, 8), BLACK));
    newList.add(new KnightPiece(new Square(2, 1), WHITE));
    newList.add(new KnightPiece(new Square(7, 1), WHITE));
    newList.add(new KnightPiece(new Square(2, 8), BLACK));
    newList.add(new KnightPiece(new Square(7, 8), BLACK));
    newList.add(new QueenPiece(new Square(4, 1), WHITE));
    newList.add(new KingPiece(new Square(5, 1), WHITE));
    newList.add(new QueenPiece(new Square(4, 8), BLACK));
    newList.add(new KingPiece(new Square(5, 8), BLACK));
    for (int i = 1; i < 9; i++) {
      newList.add(new PawnPiece(new Square(i, 2), WHITE));
      newList.add(new PawnPiece(new Square(i, 7), BLACK));
    }
    return newList;
  }

  public ChessBoard() {
    occupiedSpaces = new BoardSquare[9][9];
    /** Ustawiamy własności każdego pola planszy */
    for (int i = 1; i < 9; i++) {
      for (int j = 1; j < 3; j++) {
        occupiedSpaces[i][j] = OCCUPIEDBYWHITE;
      }
      for (int j = 3; j < 7; j++) {
        occupiedSpaces[i][j] = FREESQUARE;
      }
      for (int j = 7; j < 9; j++) {
        occupiedSpaces[i][j] = OCCUPIEDBYBLACK;
      }
    }
    boardState = new String[9][9];
    /** Stan podstawowy planszy - najpierw reprezentacja każdej bierki, potem pola modulo 2 */
    for (AbstractPiece piece : AllPieces) {
      boardState[piece.currentPosition().x][piece.currentPosition().y] =
        piece.representation();
    }
    for (int i = 3; i < 7; i++) {
      for (int j = 1; j < 9; j++) {
        if ((i + j) % 2 == 1) {
          boardState[j][i] = whiteSquare;
        } else {
          boardState[j][i] = blackSquare;
        }
      }
    }
  }

  private boolean isSquareOccupiedForPiece(
    PieceColor color,
    Square newPosition
  ) {
    if (!newPosition.isSquareInChessBoard()) {
      return true;
    }
    if (color == WHITE) {
      if (occupiedSpaces[newPosition.x][newPosition.y] == OCCUPIEDBYWHITE) {
        return true;
      }
    }
    if (color == BLACK) {
      if (occupiedSpaces[newPosition.x][newPosition.y] == OCCUPIEDBYBLACK) {
        return true;
      }
    }
    return false;
  }

  private boolean isSquareOccupied(Square newPosition) {
    return (!(occupiedSpaces[newPosition.x][newPosition.y] == FREESQUARE));
  }

  public boolean commitMoveAndCheckForWin(Move move) {
    AbstractPiece found = null;
    /** Przeszukujemy bierki i najpierw sprawdzamy, czy jakaś stoi na polu,
     *  na którym wykonujemy ruch, by ją zbić.
     */
    for (AbstractPiece piece : AllPieces) {
      if (piece.currentPosition() == move.moveTo) {
        found = piece;
      }
    }
    if (found != null) {
      /** Może i można dodać jakąś dodatkową funkcję mówiącą o tym, czy coś jest królem
       *  ale tu nawet nie castujemy, więc wydaje mi się to bardziej eleganckie.
       */
      if (found.getClass() == KingPiece.class) {
        return true;
      }
      AllPieces.remove(found);
    }
    /** Przeszukujemy bierki i najpierw sprawdzamy, jaka stoi na polu, z którego wykonujemy ruch.
     *  Następnie przesuwamy ją, zwalniamy jej pole i zajmujemy nowe.
     */
    for (AbstractPiece piece : AllPieces) {
      if (piece.currentPosition() == move.moveFrom) {
        occupiedSpaces[piece.currentPosition().x][piece.currentPosition().y] =
          FREESQUARE;
        if ((piece.currentPosition().x + piece.currentPosition().y) % 2 == 1) {
          boardState[piece.currentPosition().x][piece.currentPosition().y] =
            whiteSquare;
        } else {
          boardState[piece.currentPosition().x][piece.currentPosition().y] =
            blackSquare;
        }
        piece.setPosition(move.moveTo);
        boardState[piece.currentPosition().x][piece.currentPosition().y] =
          piece.representation();
        if (piece.getColor() == WHITE) {
          occupiedSpaces[piece.currentPosition().x][piece.currentPosition().y] =
            OCCUPIEDBYWHITE;
        } else {
          occupiedSpaces[piece.currentPosition().x][piece.currentPosition().y] =
            OCCUPIEDBYBLACK;
        }
      }
    }
    return false;
  }

  public List<Move> returnPossibleMovesForCurrentTurn(PieceColor currentTurn) {
    List<Move> currentPossibleMoves = new ArrayList<Move>();
    for (AbstractPiece piece : AllPieces) {
      if (piece.getColor() == currentTurn) {
        Square positionOfPiece = piece.currentPosition();
        List<List<Square>> possiblePieceMoves = piece.getPossibleMoves();
        for (List<Square> inDirection : possiblePieceMoves) {
          /** Dla każdego kierunku dodajemy ruchy tak długo, aż nie znajdziemy zajętego pola */
          for (int i = 0; i < inDirection.size(); i++) {
            Square possibleMove = inDirection.get(i);
            Square newPosition = positionOfPiece.plus(possibleMove);
            if (isSquareOccupiedForPiece(piece.getColor(), newPosition)) {
              i = inDirection.size();
            } else {
              if (isSquareOccupied(newPosition)) {
                i = inDirection.size();
              }
              Move newMove = new Move(positionOfPiece, newPosition, piece);
              currentPossibleMoves.add(newMove);
            }
          }
        }
      }
    }
    return currentPossibleMoves;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int i = 8; i > 0; i--) {
      for (int j = 1; j < 9; j++) {
        sb.append(boardState[j][i]);
      }
      sb.append("\n");
    }
    return sb.toString();
  }
}
