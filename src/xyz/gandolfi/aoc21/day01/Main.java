package xyz.gandolfi.aoc21.day01;

import xyz.gandolfi.aoc21.Utils;

import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day01.txt");
        assert inputLines != null;
        List<Integer> inputNums = inputLines.stream().map(Integer::parseInt).toList();

        System.out.print("Day 01a: ");
        System.out.println(Counters.countIncreases(inputNums, 1));

        System.out.print("Day 01b: ");
        System.out.println(Counters.countIncreases(inputNums, 3));
    }
}
