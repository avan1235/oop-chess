package pl.edu.mimuw.chess;

import java.util.List;

public interface IPiece {
    public static final Square N = new Square(1,0);
    public static final Square W = new Square(0,-1);
    public static final Square S = new Square(-1,0);
    public static final Square E = new Square(0,1);

    public static final Square NW = N.plus(W);
    public static final Square NE = N.plus(E);
    public static final Square SW = S.plus(W);
    public static final Square SE = S.plus(E);

    Square currentPosition();
    void setPosition(Square position);
    List<List<Square>> getPossibleMoves();

    String representation();
}
