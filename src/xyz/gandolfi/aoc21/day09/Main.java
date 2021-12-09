package xyz.gandolfi.aoc21.day09;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day09.txt");
        assert inputLines != null;

        FloorHeightmap heightmap = new FloorHeightmap(inputLines);

        System.out.print("Day 09a: ");
        int solutionA = heightmap.findLowPoints().stream()
            .map(i -> i + 1)
            .reduce(0, Integer::sum);
        System.out.println(solutionA);

        System.out.print("Day 09b: ");
        int solutionB  = heightmap.findAllBasins().stream()
            .map(Set::size)
            .sorted((i, j) -> j - i)    // Decreasing order
            .limit(3)
            .reduce(1, (i, j) -> i * j);
        System.out.println(solutionB);
    }
}
