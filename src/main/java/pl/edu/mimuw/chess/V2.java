package pl.edu.mimuw.chess;

public final class V2 {
  public final int x;
  public final int y;

  public V2(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public V2(V2 position) {
    this.x = position.x;
    this.y = position.y;
  }

  public V2 plus(V2 v){
    return new V2(this.x + v.x, this.y + v.y);
  }

  public V2 times(int i){
    return new V2(this.x*i, this.y*i);
  }

  @Override
  public boolean equals(Object o){
    if (this == o) return true;
    else{
      if(o == null || this.getClass()!=o.getClass()) return false;
      return this.x == ((V2) o).x && this.y == ((V2) o).y;
    }
  }
}
