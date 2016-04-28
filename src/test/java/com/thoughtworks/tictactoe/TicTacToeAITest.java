package com.thoughtworks.tictactoe;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by andreang on 4/28/16.
 */
public class TicTacToeAITest {

    private Board board;
    private TicTacToeAI ai;

    @Before
    public void setUp() {
        board = mock(Board.class);
        ai = new TicTacToeAI(board);
    }

    @Test
    public void shouldPickFirstAvailableSpace() {
        firstAvailableSpaceIsSpaceTwo();

        assertThat(ai.getPlayerMove(), is("2"));
    }

    private void firstAvailableSpaceIsSpaceTwo() {
        when(board.getFirstAvailableSpace()).thenReturn("2");
    }

}