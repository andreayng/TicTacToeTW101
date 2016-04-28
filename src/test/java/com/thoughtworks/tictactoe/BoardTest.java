package com.thoughtworks.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by andreang on 4/28/16.
 */
public class BoardTest {

    private Board board;
    private PrintStream printStream;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        board = new Board(printStream);
    }

    @Test
    public void shouldPrintInitialBoardWithSpaceNumbersWhenInitialized() {
        board.printBoard();

        verify(printStream).println(contains("1|2|3\n-----\n4|5|6\n-----\n7|8|9"));
    }
    
    @Test
    public void shouldDrawXMarkInSpaceOneWhenFirstPlayerSelectsSpaceOne() {
        board.playerMove(1, "1");
        board.printBoard();

        verify(printStream).println(contains("X|2|3\n-----\n4|5|6\n-----\n7|8|9"));
    }

    @Test
    public void shouldDrawOMarkInSpaceThreeWhenSecondPlayerSelectsSpaceThree() {
        board.playerMove(2, "3");
        board.printBoard();

        verify(printStream).println(contains("1|2|O\n-----\n4|5|6\n-----\n7|8|9"));
    }

    @Test
    public void shouldPrintMessageWhenLocationIsAlreadyTaken() {
        board.playerMove(1, "1");
        board.playerMove(2, "1");

        verify(printStream).println(contains("Location already taken"));
    }

    @Test
    public void shouldReturnTrueForIsFilledWhenBoardIsFilled() {
        board.playerMove(1, "1");
        board.playerMove(2, "2");
        board.playerMove(1, "3");
        board.playerMove(2, "4");
        board.playerMove(1, "5");
        board.playerMove(2, "6");
        board.playerMove(1, "7");
        board.playerMove(2, "8");
        board.playerMove(1, "9");

        assertThat(board.isFilled(), is(true));
    }

    @Test
    public void shouldReturnTrueWhenWinningRowIsFound() {
        board.playerMove(1, "1");
        board.playerMove(1, "2");
        board.playerMove(1, "3");

        assertThat(board.hasWinner(), is(true));
    }
    
    @Test
    public void shouldReturnTrueWhenWinningColumnIsFound() {
        board.playerMove(1, "2");
        board.playerMove(1, "5");
        board.playerMove(1, "8");

        assertThat(board.hasWinner(), is(true));
    }

    @Test
    public void shouldReturnTrueWhenWinningDiagonalIsFound() {
        board.playerMove(1, "1");
        board.playerMove(1, "5");
        board.playerMove(1, "9");

        assertThat(board.hasWinner(), is(true));
    }

    @Test
    public void shouldReturnThreeWhenFirstAvailableSpaceIsSpaceThree() {
        board.playerMove(1, "1");
        board.playerMove(2, "2");

        assertThat(board.getFirstAvailableSpace(), is("3"));
    }
    
    @Test
    public void shouldReturnThreeAsWinningMoveWhenFirstTwoSpacesAreTakenBySamePlayer() {
        board.playerMove(1, "1");
        board.playerMove(1, "2");

        assertThat(board.getWinningMove(1), is("3"));
    }

    @Test
    public void shouldReturnFourAsWinningMoveWhenSpaceOneAndSpaceSevenAreTakenBySamePlayer() {
        board.playerMove(1, "1");
        board.playerMove(1, "7");

        assertThat(board.getWinningMove(1), is("4"));
    }

    @Test
    public void shouldReturnFiveAsWinningMoveWhenSpaceThreeAndSpaceSevenAreTakenBySamePlayer() {
        board.playerMove(1, "3");
        board.playerMove(1, "7");

        assertThat(board.getWinningMove(1), is("5"));
    }
}