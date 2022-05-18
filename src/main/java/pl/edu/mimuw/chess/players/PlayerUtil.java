package pl.edu.mimuw.chess.players;

import pl.edu.mimuw.chess.Board.Board;
import pl.edu.mimuw.chess.Board.Field;
import pl.edu.mimuw.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

public class PlayerUtil {

  public static List<Piece> getAvailableMoves(Board board, Player player){
    Piece piece;
    List<Piece> pieces = new ArrayList<>();
    for (int x = 0; x < 8; x++) {
      for (int y = 0; y < 8; y++) {
        piece = board.getPiece(new Field(x,y));
        if(piece != null && piece.getTeam().equals(player.getTeam())) {
          //System.out.println(piece.getField().toString() +" - "+ piece.Possible_moves(board).length);
          if (piece.Possible_moves(board).length > 0) {
            pieces.add(piece);
          }
        }
      }
    }
    return pieces;
  }
}
