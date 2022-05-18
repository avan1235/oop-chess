package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

import static pl.edu.mimuw.chess.V2.*;
import static pl.edu.mimuw.chess.ChessBoard.*;

public class Player {
	private final ChessColor color;
	private Set<AbstractPiece> pieces;
	
	public Player(ChessColor color) {
		this.color = color;
		
		this.pieces = new HashSet<AbstractPiece>();
		
		switch (color) {
			case WHITE:
				this.pieces.add(new RookPiece(v(7, 0), color));
				this.pieces.add(new RookPiece(v(7, 7), color));
				
				this.pieces.add(new KnightPiece(v(7, 1), color));
				this.pieces.add(new KnightPiece(v(7, 6), color));
				
				this.pieces.add(new BishopPiece(v(7, 2), color));
				this.pieces.add(new BishopPiece(v(7, 5), color));
				
				this.pieces.add(new QueenPiece(v(7, 3), color));
				
				this.pieces.add(new KingPiece(v(7, 4), color));
				
				for (int column = 0; column < BOARD_SIZE; column++) {
					this.pieces.add(new PawnPiece(v(6, column), color));
				}
				
				return;
			case BLACK:
				this.pieces.add(new RookPiece(v(0, 0), color));
				this.pieces.add(new RookPiece(v(0, 7), color));
				
				this.pieces.add(new KnightPiece(v(0, 1), color));
				this.pieces.add(new KnightPiece(v(0, 6), color));
				
				this.pieces.add(new BishopPiece(v(0, 2), color));
				this.pieces.add(new BishopPiece(v(0, 5), color));
				
				this.pieces.add(new QueenPiece(v(0, 3), color));
				
				this.pieces.add(new KingPiece(v(0, 4), color));
				
				for (int column = 0; column < BOARD_SIZE; column++) {
					this.pieces.add(new PawnPiece(v(1, column), color));
				}
				
				return;
		}
		
		throw new IllegalStateException("unknown color " + color);
	}
	
	public String placePiecesOnTheBoard(String board) {
		StringBuilder result = new StringBuilder(board);
		for (AbstractPiece piece : this.pieces) {
			final int row = piece.getPosition().x;
			final int column = piece.getPosition().y;
			result.setCharAt(BOARD_ROW_LENGTH * (2 * row + 1) + 2 * column + 1,
			                 piece.representation().charAt(0));
		}
		
		return result.toString();
	}
	
	public Set<V2> getOccupiedSquares() {
		Set<V2> result = new HashSet<V2>();
		for (AbstractPiece piece : this.pieces) {
			result.add(piece.getPosition());
		}
		
		return result;
	}
	
	public List<AbstractPiece> getMoveablePieces() {
		Set<V2> myOccupiedSquares = this.getOccupiedSquares();
		
		List<AbstractPiece> result = new ArrayList<AbstractPiece>();
		for (AbstractPiece piece : this.pieces) {
			List<V2> validMoves = new ArrayList<V2>();
			for (List<V2> inDirection : piece.getPossibleMoves()) {
				V2 position = piece.getPosition().plus(inDirection.get(0));
				if (isOnBoard(position) && !myOccupiedSquares.contains(position)) {
					result.add(piece);
					break;
				}
			}
		}
		
		return result;
	}
	
	/**
	 * Captures the piece, that currently occupies the @p position.
	 * Does nothing, if the @p position is not occupied.
	 * @return true if the king was captured, false otherwise.
	 */
	public boolean capture(V2 position) {
		for (AbstractPiece piece : this.pieces) {
			if (piece.getPosition().equals(position)) {
				if (piece.getClass() == KingPiece.class) {
					this.pieces.remove(piece);
					return true;
				}
				else {
					this.pieces.remove(piece);
					return false;
				}
			}
		}
		
		return false;
	}
}
