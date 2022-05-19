package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

public class KingPiece extends AbstractPiece
{

	private static final V2[] directions = new V2[]{N, NE, E, SE, S, SW, W, NW};
	private static final int moveLimit = 1;

	public KingPiece(V2 position, ChessColor color)
	{
		super(position, color, "♔", "♚", directions, moveLimit);
	}
}
