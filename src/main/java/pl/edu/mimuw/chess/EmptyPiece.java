package pl.edu.mimuw.chess;


public class EmptyPiece extends Piece {
    public EmptyPiece(Board board) {
        super(board, Color.empty);
    }
    public boolean isLegal(Position position) {
        return false;
    }
    public String toString() {
        if((getPosition().row() + getPosition().column()) % 2 == 0)
            return " ";
        else 
            return "█";
    }
}