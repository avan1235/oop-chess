package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.util.ArrayList;

public class Wieza implements Figura {
  public Kolor kolor;
  public V2 pozycja;

  public Wieza(Kolor kolor, V2 pozycja) {
    this.pozycja = pozycja;
    this.kolor = kolor;
  }

  @Override
  public String reprezentacja() {
    if (this.kolor == Kolor.BIALY)
      return "♖";
    return "♜";
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
  public String toString() {
    return this.reprezentacja();
  }

  @Override
  public ArrayList<Ruch> wszystkieRuchy() {
    ArrayList<Ruch> res = new ArrayList<>();
    V2 poz = new V2(this.pozycja.x+1, this.pozycja.y);
    while (poz.naPlanszy()) {
      if (!Plansza.pola.get(poz.x).containsKey(poz.y)) {
        res.add(new Ruch(this, new V2(poz.x, poz.y)));
        poz.x++;
      } else {
        if (Plansza.pola.get(poz.x).get(poz.y).kolor()!=this.kolor)
          res.add(new Ruch(this, new V2(poz.x, poz.y)));
        break;
      }
    }
    poz = new V2(this.pozycja.x-1, this.pozycja.y);
    while (poz.naPlanszy()) {
      if (!Plansza.pola.get(poz.x).containsKey(poz.y)) {
        res.add(new Ruch(this, new V2(poz.x, poz.y)));
        poz.x--;
      } else {
        if (Plansza.pola.get(poz.x).get(poz.y).kolor()!=this.kolor)
          res.add(new Ruch(this, new V2(poz.x, poz.y)));
        break;
      }
    }
    poz = new V2(this.pozycja.x, this.pozycja.y-1);
    while (poz.naPlanszy()) {
      if (!Plansza.pola.get(poz.x).containsKey(poz.y)) {
        res.add(new Ruch(this, new V2(poz.x, poz.y)));
        poz.y--;
      } else {
        if (Plansza.pola.get(poz.x).get(poz.y).kolor()!=this.kolor)
          res.add(new Ruch(this, new V2(poz.x, poz.y)));
        break;
      }
    }
    poz = new V2(this.pozycja.x, this.pozycja.y+1);
    while (poz.naPlanszy()) {
      if (!Plansza.pola.get(poz.x).containsKey(poz.y)) {
        res.add(new Ruch(this, new V2(poz.x, poz.y)));
        poz.y++;
      } else {
        if (Plansza.pola.get(poz.x).get(poz.y).kolor()!=this.kolor)
          res.add(new Ruch(this, new V2(poz.x, poz.y)));
        break;
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
