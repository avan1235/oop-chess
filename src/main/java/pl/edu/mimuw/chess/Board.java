package pl.edu.mimuw.chess;

import java.util.LinkedList;
import java.util.List;

import static java.lang.Character.digit;

public class Board {
  public static final int size = 8;
  private final Piece[][] board;
  private final List<Piece> captured;
  private int totalMoveCount = 0;
  private String bottomPart = null;

  public Board(White white, Black black) {
    this.board = new Piece[size][];
    this.captured = new LinkedList<>();

    this.board[0] = genPieceRow(0, white, this);
    this.board[1] = genPawnRow(1, white, this);
    for (int i = 2; i < 6; i++)
      this.board[i] = new Piece[size];
    this.board[6] = genPawnRow(6, black, this);
    this.board[7] = genPieceRow(7, black, this);
  }

  private static Position pos(int row, int column) {
    return new Position(row, column);
  }

  private static Piece[] genPieceRow(int row, Player player, Board board) {
    return new Piece[]{
      new Rook(pos(row, 0), player, board),
      new Knight(pos(row, 1), player, board),
      new Bishop(pos(row, 2), player, board),
      new Queen(pos(row, 3), player, board),
      new King(pos(row, 4), player, board),
      new Bishop(pos(row, 5), player, board),
      new Knight(pos(row, 6), player, board),
      new Rook(pos(row, 7), player, board),
    };
  }

  private static Piece[] genPawnRow(int row, Player player, Board board) {
    Piece[] res = new Piece[size];
    for (int i = 0; i < size; i++)
      res[i] = new Pawn(pos(row, i), player, board);
    return res;
  }

  private static String drawTop() {
    return "  \u2554" +
      "\u2550\u2564".repeat(size - 1) +
      "\u2550\u2557\n";
  }

  private static String drawRow(Piece[] row) {
    StringBuilder res = new StringBuilder();
    res.append("\u2551");
    for (int i = 0; i < size - 1; i++) {
      res.append(drawIcon(row[i])).append("\u2502");
    }
    res.append(drawIcon(row[size - 1])).append("\u2551\n");
    return res.toString();
  }

  private static String drawIcon(Piece piece) {
    if (piece == null) return " ";
    else return piece.icon();
  }

  private static String drawMid() {
    return "  \u255F" +
      "\u2500\u253C".repeat(size - 1) +
      "\u2500\u2562\n";
  }

  private String drawBottom() {
    if (this.bottomPart == null) {
      int radix = 32;
      int a_NUM_VAL = digit('a', radix);
      StringBuilder res = new StringBuilder();
      res.append("  \u255A")
        .append("\u2550\u2567".repeat(size - 1))
        .append("\u2550\u255D\n")
        .append(" ".repeat(3));
      for (int i = 0; i < 8; i++)
        res.append(Character.forDigit(a_NUM_VAL + i, radix)).append(" ");
      res.append("\n");
      this.bottomPart = res.toString();
    }
    return this.bottomPart;
  }

  public int getTotalMoveCount() {
    return this.totalMoveCount;
  }

  /**
   * Assumes moveTo is a legal position for that piece. If there is a Piece on moveTo
   * it assumes that piece is attacked and captured.
   *
   * @param piece  moved piece.
   * @param moveTo position to which piece is being moved.
   */
  public void movePiece(Piece piece, Position moveTo) {
    Piece attacked = board[moveTo.row][moveTo.column];
    if (attacked != null) {
      attacked.becomeCaptured();
      this.captured.add(attacked);
    }

    board[piece.pos().row][piece.pos().column] = null;
    piece.move(moveTo);
    board[piece.pos().row][piece.pos().column] = piece;

    this.totalMoveCount++;
  }

  public boolean isFree(Position pos) {
    return get(pos) == null;
  }

  public Piece get(Position pos) {
    return this.board[pos.row][pos.column];
  }

  @Override
  public String toString() {
    StringBuilder res = new StringBuilder();
    res.append(drawTop());
    for (int i = size - 1; i >= 0; i--) {
      res.append(i + 1).append(" ");
      res.append(drawRow(this.board[i]));
      if (i != 0)
        res.append(drawMid());
      else
        res.append(drawBottom());
    }
    res.append(this.capturedToString());
    return res.toString();
  }

  private String capturedToString() {
    StringBuilder res = new StringBuilder();
    for (Piece piece : this.captured)
      res.append(piece).append(" ");
    return res.toString();
  }
}
