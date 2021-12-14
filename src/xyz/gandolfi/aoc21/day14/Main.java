package xyz.gandolfi.aoc21.day14;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day14.txt");
        assert inputLines != null;

        String inputString = inputLines.get(0);
        List<String> mappingRules = inputLines.stream().skip(2).toList();

        Polymerizer polymerizer = new Polymerizer(mappingRules);
        Map<Character, Long> counts;

        System.out.print("Day 14a: ");
        counts = polymerizer.getCharCountsAtLevel(inputString, 10);
        System.out.println(Polymerizer.getMostCommonElement(counts).getValue() - Polymerizer.getLeastCommonElement(counts).getValue());

        System.out.print("Day 14b: ");
        counts = polymerizer.getCharCountsAtLevel(inputString, 40);
        System.out.println(Polymerizer.getMostCommonElement(counts).getValue() - Polymerizer.getLeastCommonElement(counts).getValue());
    }
}
