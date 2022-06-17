package pl.edu.mimuw;

import pl.edu.mimuw.chess.Kolor;
import pl.edu.mimuw.chess.Ruch;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;

public interface Figura {
  String reprezentacja();
  V2 getPozycja();
  Kolor kolor();
  ArrayList<Ruch> wszystkieRuchy();
  String toString();
  void setPozycja(V2 poz);
  void dodajSieNaPlansze();

}
