package pl.edu.mimuw.chess.players;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;
import pl.edu.mimuw.chess.Board.Move;
import pl.edu.mimuw.chess.pieces.Piece;

import java.util.Random;

public class RandomPlayer extends Player{
  Random random = new Random();

  public RandomPlayer(String myTeam) {
    super(myTeam);
  }

  private int radom(){
    return random.nextInt() & Integer.MAX_VALUE;
  }

  public Move makeMove(Board board){
    var pieces = PlayerUtil.getAvailableMoves(board,this);

    Piece piece = pieces.get(radom()%pieces.size());
    Field[]  moves = piece.Possible_moves(board);
    Field destination = moves[radom()% moves.length];
    //System.out.println(piece.getField() +" -> "+ destination);
    return new Move(piece.getField(),destination);
  }
}
