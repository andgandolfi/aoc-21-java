package xyz.gandolfi.aoc21.day06;

import xyz.gandolfi.aoc21.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day06.txt");
        assert inputLines != null;
        List<Integer> inputAges = Arrays.stream(inputLines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();

        LanternfishRunner runner = new LanternfishRunner(inputAges);

        System.out.print("Day 06a: ");
        System.out.println(runner.countAfterDays(80));

        System.out.print("Day 06b: ");
        System.out.println(runner.countAfterDays(256));
    }
}