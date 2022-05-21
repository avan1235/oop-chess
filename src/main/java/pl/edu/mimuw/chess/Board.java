package pl.edu.mimuw.chess;

import java.util.ArrayList;

public class Board {
    private Piece[][] pieces;
    private Color whoseTurn;
    private int movesSinceBeating;
    public static Color oppositeColor(Color color) {
        if(color == Color.white) return Color.black;
        if(color == Color.black) return Color.white;
        return Color.empty;
    }
    public Board() {
        pieces = new Piece[8][8];
        pieces[0][0] = new Rook(this, Color.white);
        pieces[0][7] = new Rook(this, Color.white);
        pieces[0][1] = new Knight(this, Color.white);
        pieces[0][6] = new Knight(this, Color.white);
        pieces[0][2] = new Bishop(this, Color.white);
        pieces[0][5] = new Bishop(this, Color.white);
        pieces[0][3] = new Queen(this, Color.white);
        pieces[0][4] = new King(this, Color.white);
        pieces[7][0] = new Rook(this, Color.black);
        pieces[7][7] = new Rook(this, Color.black);
        pieces[7][1] = new Knight(this, Color.black);
        pieces[7][6] = new Knight(this, Color.black);
        pieces[7][2] = new Bishop(this, Color.black);
        pieces[7][5] = new Bishop(this, Color.black);
        pieces[7][4] = new Queen(this, Color.black);
        pieces[7][3] = new King(this, Color.black);
        for(int i = 0; i < 8; i++) {
            pieces[1][i] = new Pawn(this, Color.white);
            pieces[6][i] = new Pawn(this, Color.black);
        }
        for(int i = 2; i < 6; i++) {
            for(int j = 0; j < 8; j++) {
                pieces[i][j] = new EmptyPiece(this);
            }
        }
        this.movesSinceBeating = 0;
        this.whoseTurn = Color.white;
    }
    public Piece getPiece(Position position) {
        return pieces[position.row()][position.column()];
    }
    public Piece getPiece(int row, int column) {
        return pieces[row][column];
    }
    public void makeMove(Position start, Position end) {
        if(getPiece(start).isLegal(end)) {
            movesSinceBeating++;
            if(whoseTurn == Color.white) whoseTurn = Color.black;
            else {
                if(whoseTurn == Color.black) whoseTurn = Color.white;
            }
            if(getPiece(end).getColor() != Color.empty) movesSinceBeating = 0;
            pieces[end.row()][end.column()] = pieces[start.row()][start.column()];
            pieces[start.row()][start.column()] = new EmptyPiece(this);
        }
    }
    public void makeMove(Move move) {
        makeMove(move.getStartPosition(), move.getEndPosition());
    }
    public ArrayList<Move> getPlayerMoves(Color color) {
        ArrayList<Move> result = new ArrayList<Move>();
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(getPiece(i,j).getColor() == color) {
                    result.addAll(getPiece(i,j).getLegalMoves());
                }
            }
        }
        return result;
    }
    public boolean isGameOver() {
        if(movesSinceBeating >= 50) return true;
        if(getPlayerMoves(this.whoseTurn).isEmpty()) return true;
        int howManyKings = 0;
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                if(getPiece(i,j).getClass() == King.class) howManyKings++;
            }
        }
        if(howManyKings != 2) return true;
        return false;
    }
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("╔═╤═╤═╤═╤═╤═╤═╤═╗\n");
        for(int i = 7; i >= 0; i--) {
            s.append("||");
            for(int j = 0; j < 8; j++) {
                s.append(pieces[i][j] + "|");
            }
            s.append("|\n");
        }
        s.append("╚═╧═╧═╧═╧═╧═╧═╧═╝");
        return s.toString();
    }
    public void simulateGame(int seed) {
        while(!isGameOver()) {
            makeMove(getPlayerMoves(whoseTurn).get((getPlayerMoves(whoseTurn).size()/2 + seed) % getPlayerMoves(whoseTurn).size()));
            System.out.println(this.toString());
        }
    }

}