package pl.edu.mimuw.chess;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public final class Board {
  private final XY dimensions;
  private final Player whitePlayer;
  private final Player blackPlayer;
  private final Map<XY, Piece> pieces;
  private Player whoPlaysNextTurn;

  public Board(XY dimensions) {
    this.dimensions = dimensions;
    this.pieces = new HashMap<>();

    whoPlaysNextTurn = whitePlayer = new Player(Player.Color.WHITE, -1);
    blackPlayer = new Player(Player.Color.BLACK, +1);

    for (var i = 0; i < dimensions.x; ++i) {
      new PawnPiece(this, whitePlayer, new XY(i, this.dimensions.y - 2));
      new PawnPiece(this, blackPlayer, new XY(i, 1));
    }

    var whiteY = this.dimensions.y - 1;
    var blackY = 0;
    for (var x = 0; x < dimensions.x; ++x) {
      switch (x % 8) {
        case 0, 7 -> {
          new RookPiece(this, whitePlayer, new XY(x, whiteY));
          new RookPiece(this, blackPlayer, new XY(x, blackY));
        }
        case 1, 6 -> {
          new KnightPiece(this, whitePlayer, new XY(x, whiteY));
          new KnightPiece(this, blackPlayer, new XY(x, blackY));
        }
        case 2, 5 -> {
          new BishopPiece(this, whitePlayer, new XY(x, whiteY));
          new BishopPiece(this, blackPlayer, new XY(x, blackY));
        }
        case 3 -> {
          new QueenPiece(this, whitePlayer, new XY(x, whiteY));
          new KingPiece(this, blackPlayer, new XY(x, blackY));
        }
        case 4 -> {
          new KingPiece(this, whitePlayer, new XY(x, whiteY));
          new QueenPiece(this, blackPlayer, new XY(x, blackY));
        }
      }
    }
  }

  public void doMove() throws NoMovesLeft, GameEnded {
    var moves = new HashSet<Move>();
    for (var piece : pieces.values()) {
      if (piece.getOwner() == whoPlaysNextTurn) moves.addAll(piece.getMoves());
    }
    if (moves.isEmpty()) throw new NoMovesLeft();

    var move = moves.stream().skip(0).findFirst().get();
    move.getWhat().doMove(move.getWhereTo());

    whoPlaysNextTurn = (whoPlaysNextTurn == whitePlayer ? blackPlayer : whitePlayer);
  }

  public XY getDimensions() {
    return dimensions;
  }

  private void appendHorizontalLine(
    StringBuilder s, String left, String cell, String join, String right) {
    s //
      .append(left)
      .append(String.join(join, Collections.nCopies(dimensions.x, cell)))
      .append(right)
      .append("\n");
  }

  public String getRepresentation() {
    var s = new StringBuilder();
    appendHorizontalLine(s, " ╔", "═══", "╤", "╗ ");
    for (var y = 0; y < dimensions.y; ++y) {
      if (y > 0) appendHorizontalLine(s, " ╟", "───", "┼", "╢ ");

      s.append(" ║");
      for (var x = 0; x < dimensions.x; ++x) {
        if (x > 0) s.append("│");
        var piece = pieces.get(new XY(x, y));
        var text = " " + (piece != null ? piece.getRepresentation() : " ") + " ";
        s.append(text);
      }
      s.append("║ \n");
    }
    appendHorizontalLine(s, " ╚", "═══", "╧", "╝ ");
    return s.toString();
  }

  public void removePiece(Piece piece) {
    assert pieces.get(piece.getPosition()) == piece;
    pieces.remove(piece.getPosition());
  }

  public void putPiece(Piece piece) {
    pieces.put(piece.getPosition(), piece);
  }

  public Piece getPieceAt(XY position) {
    return pieces.get(position);
  }
}
