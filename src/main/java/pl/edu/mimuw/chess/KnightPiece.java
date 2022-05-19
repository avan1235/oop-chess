package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

public class KnightPiece extends AbstractPiece
{

	private static final V2[] directions = new V2[]{NNE, NEE, SEE, SSE, SSW, SWW, NWW, NNW};
	private static final int moveLimit = 1;

	public KnightPiece(V2 position, ChessColor color)
	{
		super(position, color, "♘", "♞", directions, moveLimit);
	}
}