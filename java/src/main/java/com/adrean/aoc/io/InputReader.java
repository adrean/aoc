package com.adrean.aoc.io;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputReader {

    public static InputStream getInput(String day) {
        InputStream input = InputReader.class.getClassLoader().getResourceAsStream(day);
        return input;
    }

    public static BufferedReader getReader(String day) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(getInput(day)));
        return reader;
    }
}