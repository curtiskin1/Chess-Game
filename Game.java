// Written by Curtis Kokuloku (kokul003) on 09/24/22
// for CSCI 1933 Project 2

import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Board myBoard = new Board();
        Fen.load("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR", myBoard);   // sets the pieces on the board
        System.out.println(myBoard.toString());
        int turnCounter = 0;        // keeps track of number of turn and which player's turn to play.
        Scanner s = new Scanner(System.in);
        while (!myBoard.isGameOver()) {
            if (turnCounter %  2 == 1) {
                System.out.println("It is currently black's turn to play.");
            } else if (turnCounter % 2 == 0) {
                System.out.println("It is currently white's turn to play.");
            }
            System.out.println("What is your move? (format: [startRow] [startCol] [endRow] [endCol])");
            int startRow = s.nextInt();
            int startCol = s.nextInt();
            int endRow = s.nextInt();
            int endCol = s.nextInt();
            if (myBoard.movePiece(startRow, startCol, endRow, endCol)) {
                turnCounter++;
                System.out.println(myBoard.toString());
            } else {
                System.out.println("\nInvalid move. Player try again.\n");
                System.out.println(myBoard.toString());
            }
        }
        myBoard.checkWinner();      // helper method to check the winner adn returns who won
        System.out.println("Game over.");
    }
}
