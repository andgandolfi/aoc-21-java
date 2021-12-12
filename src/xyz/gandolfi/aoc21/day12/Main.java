package xyz.gandolfi.aoc21.day12;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day12.txt");
        assert inputLines != null;

        PassagesGraph graph = new PassagesGraph(inputLines);

        System.out.print("Day 12a: ");
        System.out.println(graph.countAllPaths());

        System.out.print("Day 12b: ");
        System.out.println(graph.countAllPathsSmallTwice());
    }
}
