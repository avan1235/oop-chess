package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPiece implements ChessPiece
{

	private V2 position;
	protected final ChessColor color;
	private final String whiteRepresentation;
	private final String blackRepresentation;
	protected final V2[] directions;
	protected final int moveLimit;
	private List <V2> POSSIBLE_MOVES;

	protected AbstractPiece(V2 position, ChessColor color, String whiteRepresentation, String blackRepresentation, V2[] directions, int moveLimit)
	{
		this.position = position;
		this.color = color;
		this.whiteRepresentation = whiteRepresentation;
		this.blackRepresentation = blackRepresentation;
		this.directions = directions;
		this.moveLimit = moveLimit;
		this.POSSIBLE_MOVES = generatePossibleMoves();
	}

	@Override
	public V2 getPosition()
	{
		return position;
	}

	@Override
	public void setPosition(V2 v)
	{
		this.position = v;
		POSSIBLE_MOVES = generatePossibleMoves();
	}

	@Override
	public String representation()
	{
		switch (color)
		{
			case BLACK:
				return blackRepresentation;
			case WHITE:
				return whiteRepresentation;
		}
		throw new IllegalStateException("unknown color " + color);
	}

	@Override
	public int compareTo(ChessPiece o)
	{
		return this.position.compareTo(o.getPosition());
	}

	@Override
	public List <V2> getPossibleMoves()
	{
		return POSSIBLE_MOVES;
	}

	protected List <V2> generatePossibleMoves()
	{
		List <V2> result = new ArrayList <>();
		for (final var d : directions)
		{
			List <V2> inDirection = new ArrayList <>();
			for (int i = 1; i < moveLimit + 1; i++)
				inDirection.add(position.plus(d.times(i)));
			result.addAll(inDirection);
		}
		return result;
	}
}