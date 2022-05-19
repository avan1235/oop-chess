package pl.edu.mimuw;

import pl.edu.mimuw.chess.*;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class Main
{

	//zdecydowałem się na implementację tej z wersji przedstawionych na zajęciach, w której wszystkie figury mogą skakać
	//i pionki biją do przodu
	public static void main(String[] args)
	{
		var whitePlayer = new WhitePlayer();
		var blackPlayer = new BlackPlayer();
		Player currentPlayer = whitePlayer;

		sleep(3_000);

		for (int i = 0;i<100;i++)
		{
			sleep(1_000);
			Util.clearConsole();
			var piecesOnBoard = new ArrayList <>(whitePlayer.getPieces());
			piecesOnBoard.addAll(blackPlayer.getPieces());
			System.out.println(getBoardRepresentation(piecesOnBoard));
			V2 move = currentPlayer.makeAMove();
			if (move == null)
				break;
			currentPlayer = (currentPlayer == whitePlayer ? blackPlayer : whitePlayer);
			if (!currentPlayer.removeTakenFigure(move))
			{
				sleep(1_000);
				Util.clearConsole();
				piecesOnBoard = new ArrayList <>(whitePlayer.getPieces());
				piecesOnBoard.addAll(blackPlayer.getPieces());
				System.out.println(getBoardRepresentation(piecesOnBoard));
				break;
			}
		}
	}

	private static void sleep(long millis)
	{
		try
		{
			Thread.sleep(millis);
		}
		catch (InterruptedException ignored)
		{

		}
	}

	private static String getBoardRepresentation(List <ChessPiece> pieces)
	{
		pieces.sort(null);
		var sb = new StringBuilder();
		int it = 0;
		sb.append("ඞ ");
		for (int j = 0;j<8;j++)
			sb.append(' ').append(j);
		sb.append('\n');
		for (int i = 0;i<8;i++)
		{
			sb.append(i).append(' ');
			for (int j = 0;j<8;j++)
			{
				sb.append('|');
				if (it != pieces.size() && pieces.get(it).getPosition().equals(v(i, j)))
				{
					sb.append(pieces.get(it).representation());
					it++;
				}
				else sb.append(' ');
			}
			sb.append("|\n");
		}
		return sb.toString();
	}
}