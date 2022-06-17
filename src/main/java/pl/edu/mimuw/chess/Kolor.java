package pl.edu.mimuw.chess;

public enum Kolor {
  BIALY, CZARNY;

  public Kolor zaprzeczenie() {
    if (this==Kolor.BIALY)
      return Kolor.CZARNY;
    return Kolor.BIALY;
  }
}
