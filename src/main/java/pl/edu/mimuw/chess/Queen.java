package pl.edu.mimuw.chess;

public class Queen extends Piece {
    public Queen(Board board, Color color) {
        super(board, color);
    }
    public boolean isLegal(Position position) {
        if(!Position.validPosition(position)) return false;
        if(position == this.getPosition()) return false;
        if(board.getPiece(position).getColor() == this.color) return false;
        if(position.row() == getPosition().row()) {
            int startPos = Math.min(position.column(), getPosition().column());
            int endPos = Math.max(position.column(), getPosition().column());
            for(int i = startPos + 1; i < endPos; i++) {
                if(board.getPiece(getPosition().row(), i).getColor() != Color.empty) return false;
            }
            return true;
        }
        if(position.column() == getPosition().column()) {
            int startPos = Math.min(position.row(), getPosition().row());
            int endPos = Math.max(position.row(), getPosition().row());
            for(int i = startPos + 1; i < endPos; i++) {
                if(board.getPiece(i, getPosition().column()).getColor() != Color.empty) return false;
            }
            return true;
        }
        if(position.row() - position.column() == getPosition().row() - getPosition().column()) {
            int diff = position.row() - position.column();
            int startPos = Math.min(position.column(), getPosition().column());
            int endPos = Math.max(position.column(), getPosition().column());
            for(int i = startPos + 1; i < endPos; i++) {
                if(board.getPiece(i + diff, i).getColor() != Color.empty) return false;
            }
            return true;
        }
        if(position.row() + position.column() == getPosition().row() + getPosition().column()) {
            int sum = position.row() + position.column();
            int startPos = Math.min(position.column(), getPosition().column());
            int endPos = Math.max(position.column(), getPosition().column());
            for(int i = startPos + 1; i < endPos; i++) {
                if(board.getPiece(sum - i, i).getColor() != Color.empty) return false;
            }
            return true;
        }
        return false;
    }
    public String toString() {
        if(this.color == Color.white)
            return "Q1";
        else 
            return "Q2";
    }
}