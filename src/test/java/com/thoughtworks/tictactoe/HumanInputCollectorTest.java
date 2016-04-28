package com.thoughtworks.tictactoe;

import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by andreang on 4/28/16.
 */
public class HumanInputCollectorTest {

    private BufferedReader bufferedReader;
    private HumanInputCollector humanInputCollector;

    @Before
    public void setUp() {
        bufferedReader = mock(BufferedReader.class);
        humanInputCollector = new HumanInputCollector(bufferedReader);
    }

    @Test
    public void shouldReturnOneWhenUserInputsOne() throws IOException {
        userWillSelectOne();

        String userSpaceSelection = humanInputCollector.getPlayerMove();

        assertThat(userSpaceSelection, is("1"));
    }

    private void userWillSelectOne() throws IOException {
        when(bufferedReader.readLine()).thenReturn("1");
    }
}