package pl.edu.mimuw.chess;
import java.util.ArrayList;

public abstract class Piece {
    protected Board board;
    protected Color color;
    public Piece(Board board, Color color) {
        this.board = board;
        this.color = color;
    }
    public ArrayList<Move> getLegalMoves() {
        ArrayList<Move> legalMoves = new ArrayList<Move>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Position checkedPosition = new Position(i, j);
                if(this.isLegal(checkedPosition)) {
                    legalMoves.add(new Move(getPosition(), checkedPosition));
                }
            }
        }
        return legalMoves;
    }
    public Color getColor() {
        return this.color;
    }
    public Position getPosition() {
        Position position = new Position(-1, -1);
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(this.board.getPiece(new Position(i, j)) == this) {
                    position = new Position(i, j);
                }
            }
        }
        return position;
    }
    public abstract boolean isLegal(Position end);
}