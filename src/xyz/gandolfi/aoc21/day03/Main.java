package xyz.gandolfi.aoc21.day03;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day03.txt");
        assert inputLines != null;

        System.out.print("Day 03a: ");
        int gamma = Calculators.calculateGammaRate(inputLines);
        int epsilon = Calculators.calculateEpsilonRate(inputLines);
        System.out.println(gamma * epsilon);

        System.out.print("Day 03b: ");
        int oxygen = Calculators.calculateOxygenRating(inputLines);
        int co2 = Calculators.calculateCo2Rating(inputLines);
        System.out.println(oxygen * co2);
    }
}
