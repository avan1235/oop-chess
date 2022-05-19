package pl.edu.mimuw.chess.enums;

public enum Direction {
  N,
  NE,
  E,
  SE,
  S,
  SW,
  W,
  NW;

  public Direction flip() {
    switch (this) {
      case N -> {return S;}
      case S -> {return N;}
      case W -> {return W;}
      case E -> {return E;}
      case SW -> {return NW;}
      case NW -> {return SW;}
      case SE -> {return NE;}
      case NE -> {return SE;}
    }

    throw new RuntimeException("Case not implemented");
  }
}
