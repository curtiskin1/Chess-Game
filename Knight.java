// Written by Curtis Kokuloku (kokul003) on 09/24/22
// for CSCI 1933 Project 2

public class Knight {

    private int row, col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */

    public Knight(int row, int col, boolean isBlack) {
        this.row = row;
        this.col = col;
        this.isBlack = isBlack;
    }

    /**
     * Checks if a move to a destination square is legal.
     * @param board     The game board.
     * @param endRow    The row of the destination square.
     * @param endCol    The column of the destination square.
     * @return True if the move to the destination square is legal, false otherwise.
     */

    public boolean isMoveLegal(Board board, int endRow, int endCol) {

        boolean upperLeft1 = (this.row == endRow - 2) && (this.col == endCol - 1);
        boolean upperLeft2 = (this.row == endRow - 1) && (this.col == endCol - 2);

        boolean lowerLeft1 = (this.row == endRow + 1) && (this.col == endCol - 2);
        boolean lowerLeft2 = (this.row == endRow + 2) && (this.col == endCol - 1);

        boolean upperRight1 = (this.row == endRow - 2) && (this.col == endCol + 1);
        boolean upperRight2 = (this.row == endRow - 1) && (this.col == endCol + 2);

        boolean lowerRight1 = (this.row == endRow + 1) && (this.col == endCol + 2);
        boolean lowerRight2 = (this.row == endRow + 2) && (this.col == endCol + 1);

        boolean isValidMove = upperLeft1 || upperLeft2 || upperRight1 || upperRight2 || lowerLeft1 || lowerLeft2 || lowerRight1 || lowerRight2;


        if (board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)) {
            return isValidMove;
        }
        return false;
    }

}
