package pl.edu.mimuw.chess;

public final class Move {

  public final Square moveFrom;
  public final Square moveTo;
  public final AbstractPiece whichPiece;

  public Move(Square moveFrom, Square moveTo, AbstractPiece whichPiece) {
    this.moveFrom = moveFrom;
    this.moveTo = moveTo;
    this.whichPiece = whichPiece;
  }

  @Override
  public String toString() {
    return (
      whichPiece.representation() +
      moveFrom.toString() +
      ":" +
      moveTo.toString() +
      "\n"
    );
  }
}
