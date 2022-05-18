package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.Random;

public abstract class Player {
  public static final Random RANDOM = new Random();
  private final ArrayList<Piece> pieces;
  private final ChessGame game;
  private int moveCount = 0;

  Player(ChessGame game) {
    this.pieces = new ArrayList<>();
    this.game = game;
  }

  public void addToPieces(Piece piece) {
    assert piece.owner == this;
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
}
