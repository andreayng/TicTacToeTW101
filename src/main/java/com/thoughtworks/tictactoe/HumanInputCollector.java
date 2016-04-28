package com.thoughtworks.tictactoe;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Created by andreang on 4/28/16.
 */
public class HumanInputCollector implements InputCollector {

    private BufferedReader bufferedReader;

    public HumanInputCollector(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String getPlayerMove() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
