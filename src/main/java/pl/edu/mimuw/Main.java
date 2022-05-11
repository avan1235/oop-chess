package pl.edu.mimuw;

import pl.edu.mimuw.chess.Util;

public class Main {

  public static void main(String[] args) {
    for (String arg : args){
      try{
        Thread.sleep(1000);
      } catch (InterruptedException e){};
      Util.clearConsole();
      System.out.println(arg);
    }
  }
}
