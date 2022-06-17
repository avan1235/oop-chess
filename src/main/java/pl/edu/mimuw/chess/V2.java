package pl.edu.mimuw.chess;

public class V2 {
  public int x;
  public int y;

  public V2(int x,int y){
    this.x=x;
    this.y=y;
  }
  public V2 add(V2 a) {
    return new V2(a.x+this.x, a.y+this.y);
  }

  public boolean naPlanszy() {
    return (this.x>=1 && this.x<=8) && (this.y>=1 && this.y<=8);
  }

  public V2 przesun(int x, int y) {
    return new V2(this.x+x, this.y+y);
  }
}
