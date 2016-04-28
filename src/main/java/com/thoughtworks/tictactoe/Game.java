package com.thoughtworks.tictactoe;

import java.io.PrintStream;

/**
 * Created by andreang on 4/28/16.
 */
public class Game {
    private final PrintStream printStream;
    private InputCollector inputCollector1, inputCollector2;
    private Board board;
    private int currentPlayer;

    public Game(Board board, InputCollector inputCollector1, InputCollector inputCollector2, PrintStream printStream) {
        this.board = board;
        this.inputCollector1 = inputCollector1;
        this.inputCollector2 = inputCollector2;
        this.printStream = printStream;
        currentPlayer = 1;
    }

    public void start() {
        board.printBoard();
    }

    public void playGame() {
        boolean boardIsFilled = false;
        boolean boardHasWinner = false;
        while(!boardIsFilled && !boardHasWinner) {
            takeTurn();
            switchCurrentPlayer();
            boardIsFilled = board.isFilled();
            boardHasWinner = board.hasWinner();
        }
        if(boardHasWinner) {
            switchCurrentPlayer();
            printStream.println("Player " + currentPlayer + " Wins!");
        }
        else if(boardIsFilled) {
            printStream.println("Game is a draw");
        }
    }

    private void takeTurn() {
        boolean successfulMove = false;
        String playerMove = "";

        while (!successfulMove) {
            printStream.println("Player " + currentPlayer + "'s turn:");
            if(currentPlayer == 1) {
                playerMove = inputCollector1.getPlayerMove();
            }
            else if(currentPlayer == 2) {
                playerMove = inputCollector2.getPlayerMove();
            }
            successfulMove = board.playerMove(currentPlayer, playerMove);
        }

        board.printBoard();
    }


    private void switchCurrentPlayer() {
        if (currentPlayer == 1) {
            currentPlayer = 2;
        } else if (currentPlayer == 2) {
            currentPlayer = 1;
        }
    }
}
