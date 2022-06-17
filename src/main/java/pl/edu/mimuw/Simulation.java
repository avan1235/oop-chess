package pl.edu.mimuw;

import pl.edu.mimuw.chess.Gracz;
import pl.edu.mimuw.chess.Kolor;
import pl.edu.mimuw.chess.Plansza;
import pl.edu.mimuw.chess.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public abstract class Simulation {

  public static void szachyDoPliku(File plikDocelowy) throws IOException {
    BufferedWriter writer = new BufferedWriter(new FileWriter(plikDocelowy));
    Plansza.initPlansza();
    Gracz kamil = new Gracz(Kolor.BIALY, "Kamil");
    Gracz adam = new Gracz(Kolor.CZARNY, "Adam");
    Kolor ostatniWykonanyRuch = Kolor.CZARNY;
    while (Plansza.gramyDalej(adam, kamil, writer)) {
      writer.write("" + (kamil.wykonaneRuchy+adam.wykonaneRuchy+1) + "\n");
      writer.write(Plansza.wypiszPlansze());
      if (ostatniWykonanyRuch == Kolor.CZARNY) {
        kamil.zrubRuch();
      } else {
        adam.zrubRuch();
      }
      ostatniWykonanyRuch = ostatniWykonanyRuch.zaprzeczenie();
      writer.write("\n\n");
    }
    writer.close();
  }

  public static void szachyNaKonsoli(double odstepPomiedyRuchamiWSekundach) {
    Plansza.initPlansza();
    Gracz kamil = new Gracz(Kolor.BIALY, "Kamil");
    Gracz adam = new Gracz(Kolor.CZARNY, "Adam");
    Kolor ostatniWykonanyRuch = Kolor.CZARNY;
    while (Plansza.gramyDalej(adam, kamil)) {
      System.out.println("" + (kamil.wykonaneRuchy+adam.wykonaneRuchy+1));
      System.out.println(Plansza.wypiszPlansze());
      if (ostatniWykonanyRuch == Kolor.CZARNY) {
        kamil.zrubRuch();
      } else {
        adam.zrubRuch();
      }
      ostatniWykonanyRuch = ostatniWykonanyRuch.zaprzeczenie();
      try {
        int sleep = (int) Math.floor(odstepPomiedyRuchamiWSekundach*1000);
        Thread.sleep(sleep);
      } catch (InterruptedException e){}
      Util.clearConsole();
      System.out.println("\n\n");
    }
  }
}
