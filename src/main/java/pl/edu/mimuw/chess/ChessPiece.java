package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public abstract class ChessPiece {
  protected final List<List<Vector>> allMoves;
  private final ChessColor color;
  private final char blackSymbol;
  private final char whiteSymbol;
  private Vector position;

  protected ChessPiece(ChessColor color, Vector position, char blackSymbol, char whiteSymbol) {
    this.color = color;
    this.position = position;
    this.blackSymbol = blackSymbol;
    this.whiteSymbol = whiteSymbol;
    this.allMoves = generateAllMoves();
  }

  public Vector getPosition() {
    return position;
  }

  public void setPosition(Vector position) {
    this.position = position;
  }

  public char getSymbol() {
    return color == ChessColor.BLACK ? blackSymbol : whiteSymbol;
  }

  public ChessColor getColor() {
    return color;
  }

  public List<List<Vector>> getPossibleMoves(ChessBoard board) {
    final var moves = generateAllMoves();
    final var result = new ArrayList<List<Vector>>();

    for (var direction : moves) {
      final var thisDirection = new ArrayList<Vector>();

      for (var move : direction) {
        final var newPosition = getPosition().plus(move);
        if (board.isNotOnBoard(newPosition)) break;

        final var pieceAtNewPosition = board.getPiece(newPosition);
        if (pieceAtNewPosition == null) {
          thisDirection.add(move);
        } else if (pieceAtNewPosition.getColor() != getColor()) {
          thisDirection.add(move);
          break;
        } else {
          break;
        }
      }

      if (thisDirection.size() > 0) result.add(thisDirection);
    }

    return result;
  }

  public boolean isKing() {
    return false;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) return false;
    if (getClass() != obj.getClass()) return false;
    final ChessPiece other = (ChessPiece) obj;
    if (this.color != other.color) return false;
    return this.position.equals(other.position);
  }

  protected abstract List<List<Vector>> generateAllMoves();
}
