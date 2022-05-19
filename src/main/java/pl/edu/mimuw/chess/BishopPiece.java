package pl.edu.mimuw.chess;

import static pl.edu.mimuw.chess.V2.*;

public class BishopPiece extends AbstractPiece
{

	private static final V2[] directions = new V2[]{NE, SE, SW, NW};
	private static final int moveLimit = ChessBoard.BOARD_SIZE;

	public BishopPiece(V2 position, ChessColor color)
	{
		super(position, color, "♗", "♝", directions, moveLimit);
	}
}