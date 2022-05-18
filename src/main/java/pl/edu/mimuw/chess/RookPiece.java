package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;

import static pl.edu.mimuw.chess.V2.*;
import static pl.edu.mimuw.chess.ChessBoard.*;

public class RookPiece extends AbstractPiece {
	public RookPiece(V2 position, ChessColor color) {
		super(position, color, "♜", "♖");
	}
	
	@Override
	protected List<List<V2>> generatePossibleMoves() {
		List<List<V2>> result = new ArrayList<List<V2>>();
		for (final V2 d : new V2[] {N, S, E, W}) {
			List<V2> inDirection = new ArrayList<V2>();
			for (int i = 1; i < BOARD_SIZE; i++) {
				inDirection.add(d.times(i));
			}
			result.add(inDirection);
		}
		
		return result;
	}
}
