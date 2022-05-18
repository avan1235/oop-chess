package pl.edu.mimuw.chess;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Random;

import pl.edu.mimuw.chess.Util;

public class ChessBoard {
	public static final int BOARD_SIZE = 8;
	
	public static final String EMPTY_BOARD = "╔═╤═╤═╤═╤═╤═╤═╤═╗\n" +
	                                         "║█│ │█│ │█│ │█│ ║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║ │█│ │█│ │█│ │█║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║█│ │█│ │█│ │█│ ║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║ │█│ │█│ │█│ │█║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║█│ │█│ │█│ │█│ ║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║ │█│ │█│ │█│ │█║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║█│ │█│ │█│ │█│ ║\n" +
	                                         "╟─┼─┼─┼─┼─┼─┼─┼─╢\n" +
	                                         "║ │█│ │█│ │█│ │█║\n" +
	                                         "╚═╧═╧═╧═╧═╧═╧═╧═╝\n";
	
	public static final int BOARD_ROW_LENGTH = BOARD_SIZE * 2 + 2;
	
	public static boolean isOnBoard(V2 position) {
		return 0 <= position.x && position.x < BOARD_SIZE &&
		       0 <= position.y && position.y < BOARD_SIZE;
	}
	
	private static final Random RANDOM = new Random();
	
	public static void play() {
		Player whitePlayer = new Player(ChessColor.WHITE);
		Player blackPlayer = new Player(ChessColor.BLACK);
		
		Util.clearConsole();
		System.out.println(whitePlayer.placePiecesOnTheBoard(
		                   blackPlayer.placePiecesOnTheBoard(EMPTY_BOARD)));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {}
		
		for (int move = 0; move < 100; move++) {
			List<AbstractPiece> moveablePieces;
			Set<V2> myOccupiedSquares;
			Set<V2> OccupiedSquares;
			if (move % 2 == 0) {
				moveablePieces = whitePlayer.getMoveablePieces();
				myOccupiedSquares = whitePlayer.getOccupiedSquares();
				OccupiedSquares = blackPlayer.getOccupiedSquares();
			}
			else {
				moveablePieces = blackPlayer.getMoveablePieces();
				myOccupiedSquares = blackPlayer.getOccupiedSquares();
				OccupiedSquares = whitePlayer.getOccupiedSquares();
			}
			
			if (moveablePieces.isEmpty()) {
				if (move % 2 == 0) {
					System.out.println("The game ended in a draw. White can't make a move.\n");
				}
				else {
					System.out.println("The game ended in a draw. Black can't make a move.\n");
				}
				
				return;
			}
			
			AbstractPiece pieceToMove = moveablePieces.get(RANDOM.nextInt(moveablePieces.size()));
			
			List<V2> validMoves = new ArrayList<V2>();
			for (List<V2> inDirection : pieceToMove.getPossibleMoves()) {
				for (V2 d : inDirection) {
					V2 position = pieceToMove.getPosition().plus(d);
					if (!isOnBoard(position) || myOccupiedSquares.contains(position)) {
						break;
					}
					validMoves.add(position);
					if (OccupiedSquares.contains(position)) {
						break;
					}
				}
			}
			assert !validMoves.isEmpty();
			
			V2 position = validMoves.get(RANDOM.nextInt(validMoves.size()));
			pieceToMove.setPosition(position);
			
			if (move % 2 == 0) {
				if (blackPlayer.capture(position)) {
					Util.clearConsole();
					System.out.println(whitePlayer.placePiecesOnTheBoard(
								       blackPlayer.placePiecesOnTheBoard(EMPTY_BOARD)));
					System.out.println("White won.\n");
					
					return;
				}
			}
			else {
				if (whitePlayer.capture(position)) {
					Util.clearConsole();
					System.out.println(whitePlayer.placePiecesOnTheBoard(
								       blackPlayer.placePiecesOnTheBoard(EMPTY_BOARD)));
					System.out.println("Black won.\n");
					
					return;
				}
			}
			
			Util.clearConsole();
			System.out.println(whitePlayer.placePiecesOnTheBoard(
				               blackPlayer.placePiecesOnTheBoard(EMPTY_BOARD)));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}
		
		System.out.println("The simulation is over. Nobody won.\n");
	}
}
