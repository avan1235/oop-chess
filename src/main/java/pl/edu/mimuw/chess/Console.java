package pl.edu.mimuw.chess;

public final class Console {
  public static final String CLEAR = "\033[H\033[2J";

  public static void clear() {
    System.out.print(CLEAR);
    System.out.flush();
  }
}
