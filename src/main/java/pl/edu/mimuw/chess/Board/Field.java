package pl.edu.mimuw.chess.Board;

public class Field {
  final public int x;
  final public int y;

  public Field(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public boolean isCorrect(){
    if(x<0 || x>7) return false;
    if(y<0 || y>7) return false;
    return true;
  }

  public Field s(int amount){
    return new Field(x,y+amount);
  }
  public Field e(int amount){
    return new Field(x+amount,y);
  }

  public Field se(int amount){
    return new Field(x+amount,y+amount);
  }

  public Field ws(int amount){
    return new Field(x-amount,y+amount);
  }

  @Override
  public String toString() {
    return "Field{" +
      "x=" + x +
      ", y=" + y +
      '}';
  }
}
