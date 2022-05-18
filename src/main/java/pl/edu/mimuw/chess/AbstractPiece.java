package pl.edu.mimuw.chess;

import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public abstract class AbstractPiece implements ChessPiece {
	private V2 position;
	private final ChessColor color;
	private final String whiteRepresentation;
	private final String blackRepresentation;
	private final List<List<V2>> POSSIBLE_MOVES;
	
	protected AbstractPiece(V2 position, ChessColor color, String whiteRepresentation,
	                        String blackRepresentation) {
		this.position = position;
		this.color = color;
		this.whiteRepresentation = whiteRepresentation;
		this.blackRepresentation = blackRepresentation;
		this.POSSIBLE_MOVES = this.generatePossibleMoves();
	}
	
	protected abstract List<List<V2>> generatePossibleMoves();
	
	public List<List<V2>> getPossibleMoves() {
		return this.POSSIBLE_MOVES;
	}
	
	@Override
	public V2 getPosition() {
		return v(this.position.x, this.position.y);
	}
	
	@Override
	public void setPosition(V2 position) {
		this.position = v(position.x, position.y);
	}
	
	@Override
	public String representation() {
		switch (color) {
			case WHITE:
				return whiteRepresentation;
			case BLACK:
				return blackRepresentation;
		}
		throw new IllegalStateException("unknown color " + color);
	}
}
