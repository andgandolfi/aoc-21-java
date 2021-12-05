package xyz.gandolfi.aoc21.day05;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day05.txt");
        assert inputLines != null;

        System.out.print("Day 05a: ");
        Diagram diagramA = new Diagram(inputLines);
        diagramA.discardDiagonalLines();
        System.out.println(diagramA.findOverlaps());

        System.out.print("Day 05b: ");
        Diagram diagramB = new Diagram(inputLines);
        System.out.println(diagramB.findOverlaps());
    }
}
