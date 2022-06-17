package pl.edu.mimuw.chess;

import pl.edu.mimuw.Figura;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Gracz {
  public final Kolor kolor;
  public final String imie;
  public boolean czyPrzegralem;
  public int wykonaneRuchy;
  public boolean czyPat;

  public Gracz(Kolor kolor, String imie) {
    this.kolor = kolor;
    this.imie = imie;
    this.czyPrzegralem = false;
    this.wykonaneRuchy = 0;
    this.czyPat = false;
  }

  public boolean czyMamKrula() {
    return (Objects.equals(Plansza.figuryGracza(this.kolor).get(0).toString(), "Krul"));
  }

  public void zrubRuch() {
    if (czyMamKrula()) {
      Random random = new Random();
      ArrayList<Figura> mojeFigury = Plansza.figuryGracza(this.kolor);
      ArrayList<Figura> figuryKtorymiMogeSieRuszyc = new ArrayList<>();
      for (Figura figura : mojeFigury) {
        if (figura.wszystkieRuchy().size() > 0)
          figuryKtorymiMogeSieRuszyc.add(figura);
      }
      if (figuryKtorymiMogeSieRuszyc.size() > 0) {
        int losowaFigura = random.nextInt(figuryKtorymiMogeSieRuszyc.size());
        Figura figuraDoRuszenia = figuryKtorymiMogeSieRuszyc.get(losowaFigura);
        ArrayList<Ruch> dostepneRuchyTaFigura = figuraDoRuszenia.wszystkieRuchy();
        int losowyRuch = random.nextInt(dostepneRuchyTaFigura.size());
        Ruch ruchDoZrobienia = dostepneRuchyTaFigura.get(losowyRuch);
        Plansza.zrealizujRuch(ruchDoZrobienia);
        this.wykonaneRuchy++;
      } else {
        this.czyPat = true;
      }
    } else {
      this.czyPrzegralem = true;
    }
  }


}
