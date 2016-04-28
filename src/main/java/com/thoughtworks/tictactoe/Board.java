package com.thoughtworks.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Objects;

/**
 * Created by andreang on 4/28/16.
 */
public class Board {

    private PrintStream printStream;
    private String[] board;

    public Board(PrintStream printStream) {
        board = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        this.printStream = printStream;
    }

    public void printBoard() {
        printStream.println(String.format("%s|%s|%s\n-----\n%s|%s|%s\n-----\n%s|%s|%s",
                board[0], board[1], board[2], board[3], board[4], board[5], board[6], board[7], board[8]));
    }

    public boolean playerMove(int playerNumber, String spaceSelected) {
        int boardSpaceSelected = Integer.parseInt(spaceSelected) - 1;
        if (isSpaceFilled(boardSpaceSelected)) {
            printStream.println("Location already taken");
            return false;
        }
        if (playerNumber == 1) {
            board[boardSpaceSelected] = "X";
        } else if (playerNumber == 2) {
            board[boardSpaceSelected] = "O";
        }
        return true;
    }

    public boolean isFilled() {
        for (int space = 0; space < 9; space++) {
            if (!isSpaceFilled(space)) {
                return false;
            }
        }
        return true;
    }

    public boolean isSpaceFilled(int spaceSelected) {
        return (board[spaceSelected].equals("X") || board[spaceSelected].equals("O"));
    }

    public boolean hasWinner() {
        for (int i = 0; i <= 2; i++) {
            if (board[i] == board[i + 3] && board[i + 3] == board[i + 6]) {
                return true;
            }
        }
        for (int i = 0; i <= 6; i += 3) {
            if (board[i] == board[i + 1] && board[i + 1] == board[i + 2]) {
                return true;
            }
        }
        if (board[0] == board[4] && board[4] == board[8]) {
            return true;
        }
        if (board[2] == board[4] && board[4] == board[6]) {
            return true;
        }
        return false;
    }

    public String getFirstAvailableSpace() {
        for (int spaceSelected = 0; spaceSelected < 9; spaceSelected++) {
            if (!isSpaceFilled(spaceSelected)) {
                return Integer.toString(spaceSelected + 1);
            }
        }
        return "";
    }

    public String getWinningMove(int playerNumber) {
        String playerMark = "";
        if(playerNumber == 1) {
            playerMark = "X";
        }
        else if(playerNumber == 2) {
            playerMark = "O";
        }
        int[] playerMarkBoard = new int[9];
        for(int i = 0; i < 9; i++) {
            if(board[i].equals(playerMark)) {
                playerMarkBoard[i] = 1;
            }
            else {
                playerMarkBoard[i] = 0;
            }
        }

        for (int i = 0; i <= 2; i++) {
            if (playerMarkBoard[i] + playerMarkBoard[i+3] + playerMarkBoard[i+6] == 2) {
                return findWinningSpace(i, i+3, i+6);
            }
        }
        for (int i = 0; i <= 6; i += 3) {
            if (playerMarkBoard[i] + playerMarkBoard[i + 1] + playerMarkBoard[i + 2] == 2) {
                return findWinningSpace(i, i+1, i+2);
            }
        }
        if (playerMarkBoard[0] + playerMarkBoard[4] + playerMarkBoard[8] == 2) {
            return findWinningSpace(0, 4, 8);
        }
        if (playerMarkBoard[2] + playerMarkBoard[4] + playerMarkBoard[6] == 2) {
            return findWinningSpace(2, 4, 6);
        }
        return "";

    }

    private String findWinningSpace(int i, int i1, int i2) {
        if(board[i] == board[i1]) {
            return Integer.toString(i2 + 1);
        }
        else if(board[i1] == board[i2]) {
            return Integer.toString(i + 1);
        }
        else if(board[i] == board[i2]) {
            return Integer.toString(i1 + 1);
        }
        else {
            return "";
        }
    }
}
