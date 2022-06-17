package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Plansza {
  public static final ArrayList<Figura> biale = new ArrayList<>();
  public static final ArrayList<Figura> czarne = new ArrayList<>();
  public static final HashMap<Integer, HashMap<Integer, Figura>> pola = new HashMap<>();

  public static void initPlansza() {
    for (int i = 1; i<=8; i++) {
      Pion pBialy = new Pion(Kolor.BIALY, new V2(i, 2));
      Pion pCzarny = new Pion(Kolor.CZARNY, new V2(i, 7));
      biale.add(pBialy);
      czarne.add(pCzarny);
      HashMap<Integer, Figura> accMap = new HashMap<>();
      accMap.put(2, pBialy);
      accMap.put(7, pCzarny);
      pola.put(i, accMap);
    }
    biale.add(new Wieza(Kolor.BIALY, new V2(1, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Wieza(Kolor.BIALY, new V2(8, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Kon(Kolor.BIALY, new V2(2, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Kon(Kolor.BIALY, new V2(7, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Goniec(Kolor.BIALY, new V2(3, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Goniec(Kolor.BIALY, new V2(6, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();
    biale.add(new Hetman(Kolor.BIALY, new V2(4, 1)));
    biale.get(biale.size()-1).dodajSieNaPlansze();

    czarne.add(new Wieza(Kolor.CZARNY, new V2(1, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Wieza(Kolor.CZARNY, new V2(8, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Kon(Kolor.CZARNY, new V2(2, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Kon(Kolor.CZARNY, new V2(7, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Goniec(Kolor.CZARNY, new V2(3, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Goniec(Kolor.CZARNY, new V2(6, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();
    czarne.add(new Hetman(Kolor.CZARNY, new V2(4, 8)));
    czarne.get(czarne.size()-1).dodajSieNaPlansze();

    biale.add(0, new Krul(Kolor.BIALY, new V2(5, 1)));
    biale.get(0).dodajSieNaPlansze();
    czarne.add(0, new Krul(Kolor.CZARNY, new V2(5, 8)));
    czarne.get(0).dodajSieNaPlansze();
  }

  public static ArrayList<Figura> figuryGracza(Kolor kolor) {
    if (kolor == Kolor.BIALY)
      return biale;
    else
      return czarne;
  }

  public static void zrealizujRuch(Ruch ruch) {
    Kolor kolorRuszanejFigury = ruch.figura.kolor();
    V2 nowaPozycja = new V2(ruch.pozycjaPoRuchu.x, ruch.pozycjaPoRuchu.y);
    V2 staraPozycja = ruch.figura.getPozycja();
    if (pola.get(nowaPozycja.x).containsKey(nowaPozycja.y)) {
      figuryGracza(kolorRuszanejFigury.zaprzeczenie()).remove(pola.get(nowaPozycja.x).get(nowaPozycja.y));
      pola.get(nowaPozycja.x).replace(nowaPozycja.y, ruch.figura);
    } else {
      pola.get(nowaPozycja.x).put(nowaPozycja.y, ruch.figura);
    }
    pola.get(staraPozycja.x).remove(staraPozycja.y);
    ruch.figura.setPozycja(nowaPozycja);
  }

  public static void wyczysc() {
    Plansza.czarne.clear();
    Plansza.biale.clear();
    Plansza.pola.clear();
  }

  public static String poleParzystosc(int x, int y) {
    if ((x+y)%2==0)
      return " ";
    return "X";
  }

  public static String naPolu(int x, int y) {
    if (pola.get(x).containsKey(y))
      return pola.get(x).get(y).reprezentacja();
    return poleParzystosc(x, y);
  }

  public static boolean gramyDalej(Gracz czarny, Gracz bialy, BufferedWriter writer) throws IOException {
    if (czarny.czyPat || bialy.czyPat) {
      writer.write("PAT - gra skończona remisem");
      return false;
    }
    if (czarny.czyPrzegralem) {
      writer.write("zbicie czarnego króla - gra skończona zwycięstwem gracza białego - " + bialy.imie);
      return false;
    }
    if (bialy.czyPrzegralem) {
      writer.write("zbicie bialego króla - gra skończona zwycięstwem gracza czarnego - " + czarny.imie);
      return false;
    }
    if (czarny.wykonaneRuchy == 50) {
      writer.write("obaj gracze zrobili po 50 ruchów - gra skończona remisem");
      return false;
    }
    return true;
  }

  public static boolean gramyDalej(Gracz czarny, Gracz bialy) {
    if (czarny.czyPat || bialy.czyPat) {
      System.out.println("PAT - gra skończona remisem");
      return false;
    }
    if (czarny.czyPrzegralem) {
      System.out.println("zbicie czarnego króla - gra skończona zwycięstwem gracza białego - " + bialy.imie);
      return false;
    }
    if (bialy.czyPrzegralem) {
      System.out.println("zbicie bialego króla - gra skończona zwycięstwem gracza czarnego - " + czarny.imie);
      return false;
    }
    if (czarny.wykonaneRuchy == 50) {
      System.out.println("obaj gracze zrobili po 50 ruchów - gra skończona remisem");
      return false;
    }
    return true;
  }

  public static String wypiszPlansze() {
    StringBuilder res = new StringBuilder();
    res.append("╔═╤═╤═╤═╤═╤═╤═╤═╗");
    for (int i = 8; i>1; i--) {
      res.append("\n║");
      for (int j = 1; j<8; j++) {
        res.append(naPolu(j, i)).append("│");
      }
      res.append(naPolu(8, i)).append("║\n╟─┼─┼─┼─┼─┼─┼─┼─╢");
    }
    res.append("\n║");
    for (int j = 1; j<8; j++) {
      res.append(naPolu(j, 1)).append("│");
    }
    res.append(naPolu(8, 1)).append("║\n╚═╧═╧═╧═╧═╧═╧═╧═╝");
    return res.toString();
  }


}
