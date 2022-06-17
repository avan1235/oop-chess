package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.util.ArrayList;
import java.util.List;

public class Pion implements Figura {
  public Kolor kolor;
  public V2 pozycja;

  public Pion(Kolor kolor, V2 poz) {
    this.kolor = kolor;
    this.pozycja = poz;
  }


  @Override
  public String reprezentacja() {
    if (this.kolor == Kolor.BIALY)
      return "♙";
    return "♟";
  }

  @Override
  public V2 getPozycja() {
    return new V2(this.pozycja.x, this.pozycja.y);
  }

  @Override
  public Kolor kolor() {
    return this.kolor;
  }

  @Override
  public String toString() {
    return this.reprezentacja();
  }

  @Override
  public ArrayList<Ruch> wszystkieRuchy() {
    ArrayList<Ruch> res = new ArrayList<>();
    V2 candidate = this.getPozycja();
    if (this.kolor == Kolor.BIALY)
      candidate.y+=1;
    else
      candidate.y-=1;
    if (candidate.naPlanszy()) {
      if (!Plansza.pola.get(candidate.x).containsKey(candidate.y))
        res.add(new Ruch(this, candidate));
      V2 bicie = new V2(candidate.x - 1, candidate.y);
      if (bicie.naPlanszy())
      if (Plansza.pola.get(bicie.x).containsKey(bicie.y) && Plansza.pola.get(bicie.x).get(bicie.y).kolor() != this.kolor) {
        res.add(new Ruch(this, new V2(bicie.x, bicie.y)));
      }
      bicie.x+=2;
      if (bicie.naPlanszy())
        if (Plansza.pola.get(bicie.x).containsKey(bicie.y) && Plansza.pola.get(bicie.x).get(bicie.y).kolor() != this.kolor) {
          res.add(new Ruch(this, bicie));
        }
    }
    return res;
  }

  @Override
  public void setPozycja(V2 poz) {
    this.pozycja = poz;
  }

  @Override
  public void dodajSieNaPlansze() {
    Plansza.pola.get(this.pozycja.x).put(this.pozycja.y, this);
  }










}
