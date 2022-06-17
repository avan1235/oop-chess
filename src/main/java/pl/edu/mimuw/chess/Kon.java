package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.util.ArrayList;

public class Kon implements Figura {
  public V2 pozycja;
  public Kolor kolor;

  public Kon(Kolor kolor, V2 pozycja) {
    this.pozycja = pozycja;
    this.kolor = kolor;
  }

  @Override
  public String reprezentacja() {
    if (this.kolor == Kolor.BIALY)
      return "k";
    return "K";
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
    this.checkAndAddRuch(this.pozycja.przesun(1, 2), res);
    this.checkAndAddRuch(this.pozycja.przesun(1, -2), res);
    this.checkAndAddRuch(this.pozycja.przesun(-1, 2), res);
    this.checkAndAddRuch(this.pozycja.przesun(-1, -2), res);
    this.checkAndAddRuch(this.pozycja.przesun(2, 1), res);
    this.checkAndAddRuch(this.pozycja.przesun(2, -1), res);
    this.checkAndAddRuch(this.pozycja.przesun(-2, 1), res);
    this.checkAndAddRuch(this.pozycja.przesun(-2, -1), res);
    return res;
  }

  @Override
  public void setPozycja(V2 poz) {
    this.pozycja = poz;
  }

  @Override
  public String toString() {
    return this.reprezentacja();
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
