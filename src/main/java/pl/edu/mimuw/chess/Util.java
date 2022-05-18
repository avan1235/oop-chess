package pl.edu.mimuw.chess;

public class Util {

  private Util() {
  }

  /**
   * Clearing the console content that works only in terminal mode
   * for applications run manually from command line (not in IDE)
   */
  public static void clearConsole() {
    System.out.print("\033[H\033[2J");
    System.out.flush();
  }

  public static void waitBetweenFrames() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }
}
