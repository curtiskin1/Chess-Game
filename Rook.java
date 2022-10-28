// Written by Curtis Kokuloku (kokul003) on 09/24/22
// for CSCI 1933 Project 2

public class Rook {
    private int row, col;
    private boolean isBlack;

    /**
     * Constructor.
     * @param row   The current row of the pawn.
     * @param col   The current column of the pawn.
     * @param isBlack   The color of the pawn.
     */

    public Rook(int row, int col, boolean isBlack) {
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
        return board.verifySourceAndDestination(this.row, this.col, endRow, endCol, this.isBlack)
                && (board.verifyHorizontal(this.row, this.col, endRow, endCol)
                    || board.verifyVertical(this.row, this.col, endRow, endCol));
    }
}
