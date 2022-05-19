package pl.edu.mimuw.chess.game;

import pl.edu.mimuw.chess.Util;
import pl.edu.mimuw.chess.board.Board;
import pl.edu.mimuw.chess.board.Position;
import pl.edu.mimuw.chess.enums.Colour;
import pl.edu.mimuw.chess.pieces.AbstractPiece;

import java.util.Random;

public class GameState implements IGameState {
  private final Board board;
  private int moveNumber;
  private Colour turn;

  private final AbstractPiece whiteKing;
  private final AbstractPiece blackKing;

  private final Random random = new Random();

  private boolean isStopped;

  public GameState() {
    this.board = new Board();
    this.moveNumber = 0;
    this.turn = Colour.WHITE;
    this.whiteKing = this.board.getPiece(new Position(0, 4));
    this.blackKing = this.board.getPiece(new Position(7, 4));
  }

  private int numOfPossibleMoves() {
    var pieces = board.piecesList(turn);
    int sum = 0;

    for (var piece: pieces) {
      sum += piece.genMoves(board).size();
    }

    return sum;
  }

  private AbstractPiece selectRandomPiece() {
    var pieces = board.piecesList(turn);
    var rand = random.nextInt(pieces.size());

    return pieces.get(rand);
  }

  private Position selectRandomMove(AbstractPiece piece) {
    var moveList = piece.genMoves(board);

    if (moveList.size() == 0) {
      return null;
    }

    return moveList.get(random.nextInt(moveList.size()));
  }

  public Colour checkWinner() {
    if (whiteKing.isTakenOut()) {
      return Colour.BLACK;
    }
    else if (blackKing.isTakenOut()) {
      return Colour.WHITE;
    }
    else {
      return Colour.NONE;
    }
  }

  @Override
  public Board getBoard() {
    return board;
  }

  @Override
  public void nextMove() {
    if (this.numOfPossibleMoves() == 0) {
      isStopped = true;
      return;
    }

    var piece = this.selectRandomPiece();
    var move = this.selectRandomMove(piece);

    while (move == null) {
      piece = this.selectRandomPiece();
      move = this.selectRandomMove(piece);
    }

    board.movePiece(piece.getPosition(), move);
    turn = turn.oppose();

    if (checkWinner() != Colour.NONE) {
      isStopped = true;
    }
    moveNumber++;
    if (moveNumber == 100) {
      isStopped = true;
    }
  }

  @Override
  public int getMoveNumber() {
    return moveNumber;
  }

  @Override
  public boolean isSimulationStopped() {
    return isStopped;
  }


  @Override
  public void simulate() {
    while (!this.isStopped) {
      Util.clearConsole();
      System.out.println(board.toString());
      this.nextMove();
      try {
        //noinspection BusyWait
        Thread.sleep(1000);
      }
      catch (InterruptedException e) {
        System.out.println("Interrupt");
      }

    }

    Util.clearConsole();
    System.out.println(board.toString());

    System.out.println("The winner is: " + this.checkWinner());
  }
}
