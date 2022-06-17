package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.util.ArrayList;

public class Krul implements Figura {
  public V2 pozycja;
  public Kolor kolor;

  public Krul(Kolor kolor, V2 pozycja) {
    this.pozycja = pozycja;
    this.kolor = kolor;
  }

  @Override
  public String reprezentacja() {
    if (this.kolor == Kolor.BIALY)
      return "♔";
    return "♚";
  }

  @Override
  public String toString() {
    return "Krul";
  }

  @Override
  public V2 getPozycja() {
    return this.pozycja;
  }

  @Override
  public Kolor kolor() {
    return this.kolor;
  }

  @Override
  public ArrayList<Ruch> wszystkieRuchy() {
    ArrayList<Ruch> res = new ArrayList<>();
    checkAndAddRuch(this.pozycja.przesun(1,1), res);
    checkAndAddRuch(this.pozycja.przesun(-1,1), res);
    checkAndAddRuch(this.pozycja.przesun(1,-1), res);
    checkAndAddRuch(this.pozycja.przesun(-1,-1), res);
    checkAndAddRuch(this.pozycja.przesun(0,1), res);
    checkAndAddRuch(this.pozycja.przesun(1,0), res);
    checkAndAddRuch(this.pozycja.przesun(-1,0), res);
    checkAndAddRuch(this.pozycja.przesun(0,-1), res);
    return res;
  }

  @Override
  public void setPozycja(V2 poz) {
    this.pozycja = poz;
  }

  public void checkAndAddRuch(V2 candidate, ArrayList<Ruch> acc) {
    if (candidate.naPlanszy()) {
      if (!Plansza.pola.get(candidate.x).containsKey(candidate.y) || (Plansza.pola.get(candidate.x).get(candidate.y).kolor() != this.kolor))
        acc.add(new Ruch(this, candidate));
    }
  }

  @Override
  public void dodajSieNaPlansze() {
    Plansza.pola.get(this.pozycja.x).put(this.pozycja.y, this);
  }

}
