package pl.edu.mimuw.chess;

import java.util.List;

public interface Player
{
	List <ChessPiece> getPieces();

	V2 makeAMove();

	boolean removeTakenFigure(V2 move);
}