package pl.edu.mimuw.chess;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.v;

public abstract class AbstractPlayer implements Player
{
	private final ChessColor color;
	private final List <ChessPiece> pieces;
	private final int firstRank;
	private final int secondRank;

	protected AbstractPlayer(ChessColor color, int firstRank, int secondRank)
	{
		this.color = color;
		this.firstRank = firstRank;
		this.secondRank = secondRank;
		pieces = new ArrayList <>();
		generateStartingPieces();
	}

	private void generateStartingPieces()
	{
		pieces.add(new RookPiece(v(firstRank, 0), color));
		pieces.add(new KnightPiece(v(firstRank, 1), color));
		pieces.add(new BishopPiece(v(firstRank, 2), color));
		pieces.add(new QueenPiece(v(firstRank, 3), color));
		pieces.add(new KingPiece(v(firstRank, 4), color));
		pieces.add(new BishopPiece(v(firstRank, 5), color));
		pieces.add(new KnightPiece(v(firstRank, 6), color));
		pieces.add(new RookPiece(v(firstRank, 7), color));

		for (int i = 0; i < 8; i++)
			pieces.add(new PawnPiece(v(secondRank, i), color));
	}

	@Override
	public List <ChessPiece> getPieces()
	{
		return pieces;
	}

	private boolean isLegal(V2 move)
	{
		if (!ChessBoard.isOnBoard(move))
			return false;
		for (var piece : pieces)
			if (move.equals(piece.getPosition()))
				return false;
		return true;
	}

	private V2 makeASpecificMove(ChessPiece piece, V2 move)
	{
		piece.setPosition(move);
		return move;
	}

	public V2 makeAMove()
	{
		ArrayList <ArrayList <V2>> legalMoves = new ArrayList <>();
		int legalPieces = 0;
		for (var piece : pieces)
			if (!piece.getPossibleMoves().stream().filter(this::isLegal).toList().isEmpty())
				legalPieces++;
		int pieceToMove = Util.random.nextInt(legalPieces) + 1;
		for (var piece : pieces)
			if (!piece.getPossibleMoves().stream().filter(this::isLegal).toList().isEmpty())
			{
				legalMoves.add(new ArrayList <>());
				legalMoves.get(legalMoves.size() - 1).addAll(piece.getPossibleMoves().stream().filter(this::isLegal).toList());
				pieceToMove--;
				if (pieceToMove == 0)
				{
					if (legalMoves.isEmpty())
						return null;
					return makeASpecificMove(piece, legalMoves.get(legalMoves.size() - 1).get(Util.random.nextInt(legalMoves.get(legalMoves.size() - 1).size())));
				}
			}
		//to się nigdy nie wydarzy
		return null;
	}

	@Override
	public boolean removeTakenFigure(V2 move)
	{
		for (var piece : pieces)
			if (piece.getPosition().equals(move))
			{
				if (piece.getClass() == KingPiece.class)
				{
					pieces.remove(piece);
					return false;
				}
				else
				{
					pieces.remove(piece);
					return true;
				}
			}
		return true;
	}
}