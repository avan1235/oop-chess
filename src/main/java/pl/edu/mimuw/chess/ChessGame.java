package pl.edu.mimuw.chess;

public class ChessGame {
  private final Board board;
  private final White white;
  private final Black black;
  private Player winner;
  private boolean finished = false;
  private boolean isDraw = false;
  private boolean whiteWon = false;

  public ChessGame() {
    this.white = new White(this);
    this.black = new Black(this);
    this.board = new Board(white, black);
  }

  public void lose(Player player) {
    this.whiteWon = white.getClass() != player.getClass();
    this.winner = (whiteWon ? white : black);
    this.finished = true;
  }

  public void draw() {
    this.isDraw = true;
  }

  public boolean isFinished() {
    return this.finished || this.isDraw;
  }

  public int getMoveCount() {
    return this.board.getTotalMoveCount();
  }

  public Black getBlack() {
    return this.black;
  }

  public White getWhite() {
    return this.white;
  }

  Board getBoard() {
    return board;
  }

  public void printBoard() {
    System.out.println(this.board);
  }

  public void printResult() {
    if (this.finished)
      System.out.println((whiteWon ? "White" : "Black") + " won in " + winner.getMoveCount() + " moves.");
    else
      System.out.println("Draw! In " + this.board.getTotalMoveCount() + " moves.");
  }
}
