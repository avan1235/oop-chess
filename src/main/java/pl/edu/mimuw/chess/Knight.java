package pl.edu.mimuw.chess;

public class Knight extends Piece {
    public Knight(Board board, Color color) {
        super(board, color);
    }
    public boolean isLegal(Position position) {
        if(!Position.validPosition(position)) return false;
        if(position == this.getPosition()) return false;
        if(board.getPiece(position).getColor() == this.color) return false;
        int rowDiff = getPosition().row() - position.row();
        int columnDiff = getPosition().column() - position.column();
        if(Math.abs(rowDiff * columnDiff) != 2) return false;
        return true;
    }
    public String toString() {
        if(this.color == Color.white)
            return "N1";
        else 
            return "N2";
    }
}