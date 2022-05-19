package pl.edu.mimuw.chess.enums;

public enum Colour {
  WHITE,
  BLACK,
  NONE;

  public Colour oppose() {
    switch (this) {
      case BLACK -> {
        return WHITE;
      }
      case WHITE -> {
        return BLACK;
      }
      case NONE -> {
        return NONE;
      }
    }
    throw new RuntimeException("Case not implemented");
  }

}
