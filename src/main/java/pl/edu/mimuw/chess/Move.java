package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.v;

public class Move {

  private final int pieceIndex;
  private final V2 position;

  public Move(int pieceIndex, V2 position) {
    this.pieceIndex = pieceIndex;
    this.position = v(position.x, position.y);
  }

  public int getPieceIndex() {
    return this.pieceIndex;
  }

  public V2 getPosition() {
    return v(this.position.x, this.position.y);
  }
}
