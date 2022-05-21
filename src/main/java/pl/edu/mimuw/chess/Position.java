package pl.edu.mimuw.chess;

public class Position {
    private final int row;
    private final int column;
    public static boolean validPosition(Position position) {
        if((position.row < 8) && (position.row >= 0) && (position.column < 8) && (position.column >= 0)) return true;
        return false;
    }
    public static int columnToNumber(char column) {
        return (int) (column - 'A');
    }
    public Position(int row, int column) {
        this.row = row;
        this.column = column;
    }
    public Position(int row, char column) {
        this.row = row;
        this.column = columnToNumber(column);
    }
    public int row() {
        return this.row;
    }
    public int column() {
        return this.column;
    }
    public String toString() {
        return "(" + this.row + " , " + this.column + ")";
    }
}