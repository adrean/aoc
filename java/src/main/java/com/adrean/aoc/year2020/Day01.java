package com.adrean.aoc.year2020;

import com.adrean.aoc.io.InputReader;

import java.io.BufferedReader;
import java.util.stream.Stream;

public class Day01 {
    public static void main(String[] args) {
        BufferedReader reader = InputReader.getReader("2020/day01.txt");

        // Part 1
        Integer[] values = reader.lines().map(Integer::parseInt).toArray(Integer[]::new);
        for (int i=0; i < values.length - 1; i++ ){
            for (int j=i+1; j < values.length; j++) {
                if (values[i] + values[j] == 2020) {
                    System.out.println(values[i] * values[j]);
                }
            }
        }

        // Part 2
        for (int i=0; i < values.length - 2; i++ ){
            for (int j=i+1; j < values.length - 1; j++) {
                for (int k=j; k < values.length; k++){
                    if (values[i] + values[j] + values[k] == 2020) {
                        System.out.println(values[i] * values[j] * values[k]);
                    }
                }
            }
        }

    }
}