package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

public class Ruch {
  public Figura figura;
  public V2 pozycjaPoRuchu;

  public Ruch(Figura figura, V2 poz) {
    this.figura = figura;
    this.pozycjaPoRuchu = poz;
  }
}
