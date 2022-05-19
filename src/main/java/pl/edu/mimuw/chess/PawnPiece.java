package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

public class PawnPiece extends AbstractPiece
{

	private static final V2[] directionsWhite = new V2[]{N};
	private static final V2[] directionsBlack = new V2[]{S};
	private static final int moveLimit = 1;

	public PawnPiece(V2 position, ChessColor color)
	{
		super(position, color, "♙", "♟", (color == ChessColor.WHITE ? directionsWhite : directionsBlack), moveLimit);
	}
}