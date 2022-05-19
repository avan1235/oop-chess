package pl.edu.mimuw.chess;

public class V2 {
  public static final V2 N = new V2(0,1);
  public static final V2 S = new V2(0,-1);
  public static final V2 E = new V2(1,0);
  public static final V2 W = new V2(-1,0);

  public static final V2 NE = N.plus(E);
  public static final V2 NW = N.plus(W);
  public static final V2 SE = S.plus(E);
  public static final V2 SW = S.plus(W);

  public final int x;
  public final int y;

  public V2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public V2 plus(V2 v){
    return new V2(this.x+v.x, this.y+v.y);
  }
}
