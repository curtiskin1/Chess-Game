// Written by Curtis Kokuloku (kokul003) on 09/24/22
// for CSCI 1933 Project 2

import java.util.Arrays;

public class Board {

    // Instance variables
    private Piece[][] board;

    //TODO:
    // Construct an object of type Board using given arguments.
    public Board() {
        this.board = new Piece[8][8];
    }

    // Accessor Methods

    //TODO:
    // Return the Piece object stored at a given row and column
    public Piece getPiece(int row, int col) {
        return this.board[row][col];
    }

    //TODO:
    // Update a single cell of the board to the new piece.
    public void setPiece(int row, int col, Piece piece) {
        this.board[row][col] = piece;
    }

    // Game functionality methods

    //TODO:
    // Moves a Piece object from one cell in the board to another, provided that
    // this movement is legal. Returns a boolean to signify success or failure.
    public boolean movePiece(int startRow, int startCol, int endRow, int endCol) {
        boolean isInBound = 0 <= startRow && startRow < this.board.length && 0 <= startCol && startCol < this.board.length &&
                0 <= endRow && endRow < this.board.length && 0 <= endCol && endCol < this.board.length;

        if (isInBound && this.getPiece(startRow, startCol) != null && this.getPiece(startRow, startCol).isMoveLegal(this, endRow, endCol)) {
            this.board[endRow][endCol] = this.getPiece(startRow,startCol);
            this.board[endRow][endCol].setPosition(endRow, endCol);
            this.board[startRow][startCol] = null;
            return true;
        }
        return false;
    }   // movePiece method

    //TODO:
    // Determines whether the game has been ended, i.e., if one player's King
    // has been captured.
    public boolean isGameOver() {
        int counter = 0;

        for (Piece[] pieces : this.board) { // for (int i = 0; i < this.board.length; i++)
            for (Piece piece : pieces) {        // for (int j = 0; j < pieces.length; j++)
                if (piece != null && (piece.getCharacter() == '\u2654' || piece.getCharacter() == '\u265a')) {
                    counter++;
                }
            }
        }
        return counter != 2;    // returns true is the is only one king on the board, and game is over; false otherwise
    }   // isGameOver method

    public void checkWinner() {     // helper function to check which color piece is winner (very similar to isGameOver() above)
        for (Piece[] pieces : this.board) {
            for (Piece piece : pieces) {
                if (piece != null && (piece.getCharacter() == '\u2654')){
                    System.out.println("White has wont the game!");
                } else if (piece != null && (piece.getCharacter() == '\u265a')) {
                    System.out.println("Black has wont the game!");
                }
            }
        }
    }   // checkWinner method

    //TODO:
    // Construct a String that represents the Board object's 2D array. Return
    // the fully constructed String.
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("   0 1 2 3 4 5 6 7\n");   // adds the indexes of each column on top of the board
        int idx = 0;
        for (Piece[] pieces : this.board) { // for (int i = 0; i < this.board.length; i++)
            s.append(idx++).append(" ");
            for (Piece piece : pieces) {        // for (int j = 0; j < pieces.length; j++)
                if (piece != null) {
                    s.append("|").append(piece.getCharacter());
                } else {
                    s.append("| ");
                }
            }
            s.append("|\n");
        }
        return s.toString();
    }   // toString method

    //TODO:
    // Sets every cell of the array to null. For debugging and grading purposes.
    public void clear() {
        for (Piece[] pieces : this.board) { // for (int i = 0; i < this.board.length; i++)
            Arrays.fill(pieces, null);   // for (int j = 0; j < this.board[i].length; j++)
        }
    }   // clear method

    // Movement helper functions

    //TODO:
    // Ensure that the player's chosen move is even remotely legal.
    // Returns a boolean to signify whether:
    // - 'start' and 'end' fall within the array's bounds.
    // - 'start' contains a Piece object, i.e., not null.
    // - Player's color and color of 'start' Piece match.
    // - 'end' contains either no Piece or a Piece of the opposite color.
    public boolean verifySourceAndDestination(int startRow, int startCol, int endRow, int endCol, boolean isBlack) {

        boolean isInBound = 0 <= startRow && startRow < this.board.length && 0 <= startCol && startCol < this.board.length &&
                0 <= endRow && endRow < this.board.length && 0 <= endCol && endCol < this.board.length;

        boolean isStartValid = isInBound && this.board[startRow][startCol] != null && this.board[startRow][startCol].getIsBlack() == isBlack;
        boolean isEndValid = isInBound && this.board[endRow][endCol] == null || this.board[endRow][endCol].getIsBlack() != isBlack;

        return isStartValid && isEndValid;
    }   // verifySourceAndDestination method

    //TODO:
    // Check whether the 'start' position and 'end' position are adjacent to each other
    public boolean verifyAdjacent(int startRow, int startCol, int endRow, int endCol) {
        int rowDistance = Math.abs(endRow - startRow);
        int colDistance = Math.abs(endCol - startCol);
        boolean isRowAdjacent = rowDistance == 1 && colDistance <= 1;
        boolean isColAdjacent = colDistance == 1 && rowDistance <= 1;

        return isRowAdjacent || isColAdjacent;
    }   // verifyAdjacent method

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid horizontal move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one row.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyHorizontal(int startRow, int startCol, int endRow, int endCol) {
        if (startRow != endRow) {
            return false;
        }
        for (int i = 0; i < endCol; i++) {
            if (this.board[startRow][startCol + i] != null || this.board[startRow][startCol - i] != null) {
                return true;
            }
        }
        return false;
    }   // verifyHorizontal method

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid vertical move.
    // Returns a boolean to signify whether:
    // - The entire move takes place on one column.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyVertical(int startRow, int startCol, int endRow, int endCol) {
        if (startCol != endCol) {
            return false;
        }
        for (int i = 0; i < endRow; i++) {
            if (this.board[startRow + i][startCol] != null || this.board[startRow - i][startCol] != null) {
                return true;
            }
        }
        return false;
    }   // verifyVertical method

    //TODO:
    // Checks whether a given 'start' and 'end' position are a valid diagonal move.
    // Returns a boolean to signify whether:
    // - The path from 'start' to 'end' is diagonal... change in row and col.
    // - All spaces directly between 'start' and 'end' are empty, i.e., null.
    public boolean verifyDiagonal(int startRow, int startCol, int endRow, int endCol) {

        if (startRow == endRow && startCol == endCol) {     // checks if the player has moved a piece
            System.out.println("You did not make a move. Try again.");
            return false;
        }

        int absRowDistance = Math.abs(endRow - startRow);
        int absColDistance = Math.abs(endCol - startCol);

        if (absRowDistance != absColDistance) {
            return false;
        }

        int rowIncrement = 1;
        int colIncrement = 1;

        if (startRow > endRow || startCol > endCol) {
            rowIncrement = -1;
        }

        int rowCounter = startRow + rowIncrement;
        int colCounter = startCol + colIncrement;

        while (Math.abs(endRow - rowCounter) != 0 && Math.abs(endCol - colCounter) != 0) {
            if (this.board[rowCounter][colCounter] != null) {
                return false;
            }
            rowCounter += rowIncrement;
            colCounter += colIncrement;
        }
        return true;
    }   // verifyDiagonal method
}   // Board Class
