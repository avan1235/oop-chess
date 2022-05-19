package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

public class RookPiece extends AbstractPiece
{

	private static final V2[] directions = new V2[]{N, S, E, W};
	private static final int moveLimit = ChessBoard.BOARD_SIZE;

	public RookPiece(V2 position, ChessColor color)
	{
		super(position, color, "♖", "♜", directions, moveLimit);
	}
}