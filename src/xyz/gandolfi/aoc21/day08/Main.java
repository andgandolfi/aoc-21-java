package xyz.gandolfi.aoc21.day08;

import xyz.gandolfi.aoc21.Utils;

import java.util.Collection;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day08.txt");
        assert inputLines != null;
        List<Entry> inputEntries = inputLines.stream().map(Entry::new).toList();

        System.out.print("Day 08a: ");
        int sumPartA = inputEntries.stream()
            .map(Entry::getOutputPatterns)
            .flatMap(Collection::stream)
            .map(p -> p.getSegments().size())
            .filter(i -> i == 2 || i == 3 || i == 4 || i == 7)
            .map(p -> 1)
            .reduce(0, Integer::sum);
        System.out.println(sumPartA);

        System.out.print("Day 08b: ");
        int sumPartB = inputEntries.stream()
            .map(Entry::getOutputValue)
            .reduce(0, Integer::sum);
        System.out.println(sumPartB);
    }
}
