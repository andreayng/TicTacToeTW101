package com.thoughtworks.tictactoe;

/**
 * Created by andreang on 4/28/16.
 */
public class TicTacToeAI implements InputCollector {
    private Board board;

    public TicTacToeAI(Board board) {
        this.board = board;
    }

    public String getPlayerMove() {
        String nextMove = board.getWinningMove(2);
        if(!nextMove.equals("")) {
            return nextMove;
        }
        return board.getFirstAvailableSpace();
    }
}
