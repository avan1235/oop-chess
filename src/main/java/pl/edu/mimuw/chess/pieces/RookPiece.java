package pl.edu.mimuw.chess.pieces;

import pl.edu.mimuw.chess.ChessBoard;
import pl.edu.mimuw.chess.ChessColor;
import pl.edu.mimuw.chess.V2;

import java.util.ArrayList;
import java.util.List;

import static pl.edu.mimuw.chess.V2.*;

public class RookPiece extends AbstractPiece {

  private static final List<List<V2>> POSSIBLE_MOVES = generatePossibleMoves();

  public RookPiece(V2 position, ChessColor color) {
    super(position, color, "♖", "♜");
  }

  @Override
  public List<List<V2>> getPossibleMoves() {
    return POSSIBLE_MOVES;
  }

  @Override
  public List<List<V2>> getPossibleAttackMoves() {
    return POSSIBLE_MOVES;
  }

  private static List<List<V2>> generatePossibleMoves() {
    List<List<V2>> result = new ArrayList<>();
    for (final var d : new V2[]{N, S, E, W}) {
      List<V2> inDirection = new ArrayList<>();
      for (int i = 1; i < ChessBoard.BOARD_SIZE; i++) inDirection.add(d.times(i));
      result.add(inDirection);
    }
    return result;
  }
}
