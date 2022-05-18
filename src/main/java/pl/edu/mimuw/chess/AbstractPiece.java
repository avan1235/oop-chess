package pl.edu.mimuw.chess;

import java.util.List;

public abstract class AbstractPiece implements ChessPiece {
  public static final V2 N = new V2(0, 1);
  public static final V2 E = new V2(1, 0);
  public static final V2 S = new V2(0, -1);
  public static final V2 W = new V2(-1, 0);

  public static final V2 NE = N.plus(E);
  public static final V2 SE = S.plus(E);
  public static final V2 SW = S.plus(W);
  public static final V2 NW = N.plus(W);

  private V2 position;
  public final ChessColor color;
  protected String representation;

  protected final ChessBoard board;



  protected AbstractPiece(ChessBoard board, V2 position, ChessColor color) {
    this.board = board;
    this.position = position;
    this.color = color;
  }

  @Override
  public void take(){

  }
  @Override
  public ChessColor getColor(){
    return color;
  }

  @Override
  public void setPosition(V2 v) {
    this.position = v;
  }

  @Override
  public V2 getPosition() {
    return new V2(position);
  }

  @Override
  public List<Move> getPossibleMoves() {
    return null;
  }

  @Override
  public String representation() {
    return representation;
  }
}
