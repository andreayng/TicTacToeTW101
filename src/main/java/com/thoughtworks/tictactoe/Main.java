package com.thoughtworks.tictactoe;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        Board board = new Board(System.out);

        HumanInputCollector humanInputCollector1 = new HumanInputCollector(new BufferedReader(new InputStreamReader(System.in)));
        TicTacToeAI ticTacToeAI1 = new TicTacToeAI(board);

        Game game = new Game(board, humanInputCollector1, ticTacToeAI1, System.out);

        game.start();
        game.playGame();
    }
}
