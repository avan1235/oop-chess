package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;

import static pl.edu.mimuw.chess.V2.*;

public class KingPiece extends AbstractPiece {
	public KingPiece(V2 position, ChessColor color) {
		super(position, color, "♚", "♔");
	}
	
	@Override
	protected List<List<V2>> generatePossibleMoves() {
		List<List<V2>> result = new ArrayList<List<V2>>();
		for (final V2 d : new V2[] {N, S, E, W, NW, SW, NE, SE}) {
			List<V2> inDirection = new ArrayList<V2>();
			inDirection.add(d);
			result.add(inDirection);
		}
		
		return result;
	}
}
