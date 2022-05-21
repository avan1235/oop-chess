package pl.edu.mimuw.chess;

public class Pawn extends Piece {
    public Pawn(Board board, Color color) {
        super(board, color);
    }
    public boolean isLegal(Position position) {
        if(!Position.validPosition(position)) return false;
        if(position == this.getPosition()) return false;
        if(board.getPiece(position).getColor() == this.color) return false;
        if(getPosition().column() == position.column()) {
            if(board.getPiece(position).getColor() != Color.empty) return false;
            if(this.color == Color.white) {
                if(position.row() - getPosition().row() == 1) return true;
                if((position.row() == 3) && (getPosition().row() == 1)) return true;
                else return false;
            }
            else {
                if(position.row() - getPosition().row() == -1) return true;
                if((position.row() == 4) && (getPosition().row() == 6)) return true;
                else return false;
            }
        }
        if(Math.abs((getPosition().column() - position.column())) == 1) {
            if(this.color == Color.white) {
                if((position.row() - getPosition().row() == 1) && (board.getPiece(position).getColor() == Color.black)) return true;
                else return false;
            }
            else {
                if(position.row() - getPosition().row() == -1) return true;
                else return false;
            }
        }
        return false;
    }
    public String toString() {
        if(this.color == Color.white)
            return "P1";
        else 
            return "P2";
    }
    
}