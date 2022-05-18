package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
  public static final String white = "white";
  public static final String black = "black";
  public static Random RANDOM = new Random();
  protected String color;
  private final ArrayList<Piece> pieces;
  private final ChessGame game;
  private int moveCount = 0;

  public Player(ChessGame game) {
    this.pieces = new ArrayList<>();
    this.game = game;
  }

  public void addToPieces(Piece piece) {
    assert piece.getColor().equals(this.color());
    pieces.add(piece);
  }

  public void losePiece(Piece piece) {
    pieces.remove(piece);
  }

  public void loseGame() {
    game.lose(this);
  }

  private ArrayList<Piece> getMovablePieces() {
    ArrayList<Piece> res = new ArrayList<>();
    for (Piece piece : this.pieces)
      if (piece != null && !piece.getPossibleMoves().isEmpty())
        res.add(piece);
    return res;
  }

  public void makeRandomMove() {
    ArrayList<Piece> movable = this.getMovablePieces();
    if (movable.isEmpty()) {
      game.draw();
      return;
    }

    Piece toMove = movable.get(RANDOM.nextInt(movable.size()));
    Position toPos = toMove.getPossibleMoves().get(RANDOM.nextInt(toMove.getPossibleMoves().size()));
    game.getBoard().movePiece(toMove, toPos);
    this.moveCount++;
  }

  public int getMoveCount() {
    return this.moveCount;
  }

  public String color() {
    return this.color;
  }
}
