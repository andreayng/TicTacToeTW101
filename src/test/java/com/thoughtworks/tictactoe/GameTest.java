package com.thoughtworks.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.*;

/**
 * Created by andreang on 4/28/16.
 */
public class GameTest {

    private Board board;
    private Game game;
    private PrintStream printStream;
    private HumanInputCollector playerOneInput;
    private HumanInputCollector playerTwoInput;

    @Before
    public void setUp() {
        board = mock(Board.class);
        playerOneInput = mock(HumanInputCollector.class);
        playerTwoInput = mock(HumanInputCollector.class);
        printStream = mock(PrintStream.class);
        game = new Game(board, playerOneInput, playerTwoInput, printStream);
    }

    @Test
    public void shouldPrintBoardWhenGameStarts() {
        game.start();

        verify(board).printBoard();
    }

    @Test
    public void shouldEndGameWhenBoardIsFilled() {
        boardFillsAfterFourTurns();
        playersTakeTurnsUntilBoardIsFilled();
        playerMovesAreAlwaysSuccessful();
        game.playGame();

        verify(board, times(4)).playerMove(any(Integer.class), any(String.class));
    }

    private void boardFillsAfterFourTurns() {
        when(board.isFilled()).thenReturn(false).thenReturn(false).thenReturn(false).thenReturn(true);
    }

    @Test
    public void shouldPrintDrawMessageWhenBoardIsFilled() {
        boardFillsAfterFourTurns();
        playersTakeTurnsUntilBoardIsFilled();
        playerMovesAreAlwaysSuccessful();
        game.playGame();

        verify(printStream).println(contains("Game is a draw"));
    }

    @Test
    public void shouldPrintPlayerOneWinningMessageWhenPlayerOneWins() {
        playerOneWinsOnFirstTurn();
        playerMovesAreAlwaysSuccessful();
        game.playGame();

        verify(printStream).println(contains("Player 1 Wins!"));
    }

    private void playerOneWinsOnFirstTurn() {
        when(board.hasWinner()).thenReturn(true);
    }

    @Test
    public void shouldPrintPlayerTwoWinningMessageWhenPlayerTwoWins() {
        playerTwoWinsOnSecondTurn();
        playerMovesAreAlwaysSuccessful();
        game.playGame();

        verify(printStream).println(contains("Player 2 Wins!"));
    }

    private void playerTwoWinsOnSecondTurn() {
        when(board.hasWinner()).thenReturn(false).thenReturn(true);
    }

    private void playersTakeTurnsUntilBoardIsFilled() {
        when(playerOneInput.getPlayerMove()).thenReturn("1").thenReturn("3")
                .thenReturn("5").thenReturn("7").thenReturn("9");
        when(playerTwoInput.getPlayerMove()).thenReturn("2").thenReturn("4")
                .thenReturn("6").thenReturn("8");
    }

    private void playerMovesAreAlwaysSuccessful() {
        when(board.playerMove(any(Integer.class), any(String.class))).thenReturn(true);
    }
}