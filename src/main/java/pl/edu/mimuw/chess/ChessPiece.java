package pl.edu.mimuw.chess;

import java.util.List;

public interface ChessPiece extends Comparable <ChessPiece>
{

	V2 getPosition();

	void setPosition(V2 v);

	List <V2> getPossibleMoves();

	String representation();
}