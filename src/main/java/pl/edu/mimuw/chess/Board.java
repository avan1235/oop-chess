package pl.edu.mimuw.chess;

public class Board {
  public static final int size = 8;
  private Piece[][] board;

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

  public Board(White white, Black black) {
    this.board = new Piece[size][];
    this.board[0] = genPieceRow(0, white, this);
    this.board[1] = genPawnRow(1, white, this);
    for (int i = 2; i < 6; i++)
      this.board[i] = new Piece[size];
    this.board[6] = genPawnRow(6, black, this);
    this.board[7] = genPieceRow(7, black, this);
  }

  public void movePiece(Piece piece, Position moveTo) {
    board[piece.pos().row][piece.pos().column] = null;
    piece.move(moveTo);
    board[piece.pos().row][piece.pos().column] = piece;
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
    for (int i = 0; i < size - 1; i++) {
      res.append(drawRow(this.board[i]));
      res.append(drawMid());
    }
    res.append(drawRow(this.board[size - 1]));
    res.append(drawBottom());
    return res.toString();
  }

  private static String drawTop() {
    return "\u2554" +
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
    return "\u255F" +
      "\u2500\u253C".repeat(size - 1) +
      "\u2500\u2562\n";
  }

  private static String drawBottom() {
    return "\u255A" +
      "\u2550\u2567".repeat(size - 1) +
      "\u2550\u255D\n";
  }
}
