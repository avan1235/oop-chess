package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;

import static pl.edu.mimuw.chess.V2.*;

public class KnightPiece extends AbstractPiece {
	public KnightPiece(V2 position, ChessColor color) {
		super(position, color, "♞", "♘");
	}
	
	@Override
	protected List<List<V2>> generatePossibleMoves() {
		List<List<V2>> result = new ArrayList<List<V2>>();
		for (final V2 d : new V2[] {N.plus(NW), N.plus(NE), E.plus(NE), E.plus(SE),
		                            S.plus(SE), S.plus(SW), W.plus(SW), W.plus(NW)}) {
			List<V2> inDirection = new ArrayList<V2>();
			inDirection.add(d);
			result.add(inDirection);
		}
		
		return result;
	}
}
