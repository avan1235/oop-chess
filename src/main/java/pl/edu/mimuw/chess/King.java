package pl.edu.mimuw.chess;

public class King extends Piece {
    public King(Board board, Color color) {
        super(board, color);
    }
    public boolean isLegal(Position position) {
        if(!Position.validPosition(position)) return false;
        if(position == this.getPosition()) return false;
        if(board.getPiece(position).getColor() == this.color) return false;
        if((
            Math.abs(getPosition().row() - position.row()) > 1) || 
            ((Math.abs(getPosition().column() - position.column()) > 1))
        ) return false;
        return true;
    }

    public String toString() {
        if(this.color == Color.white)
            return "K1";
        else 
            return "K2";
    }
}