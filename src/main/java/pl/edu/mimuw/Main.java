package pl.edu.mimuw;

import pl.edu.mimuw.chess.Gracz;
import pl.edu.mimuw.chess.Kolor;
import pl.edu.mimuw.chess.Plansza;
import pl.edu.mimuw.chess.Util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Main {

  public static void main(String[] args) throws IOException {
    for (String a: args) {
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e){}
      Util.clearConsole();
      System.out.println(a);
    }
    Simulation.szachyNaKonsoli(0.5);
    //Simulation.szachyDoPliku(new File("szachy_test2"));
  }
}
