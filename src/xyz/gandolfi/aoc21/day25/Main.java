package xyz.gandolfi.aoc21.day25;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day25.txt");
        assert inputLines != null;

        SeaMap map = new SeaMap(inputLines);

        System.out.print("Day 25a: ");
        SeaMap next = map;
        SeaMap prev = null;
        int count = 0;
        while (!next.equals(prev)) {
            prev = next;
            next = next.evolve();
            count++;
        }
        System.out.println(count);

        System.out.print("Day 25b: ");
        System.out.println("No part B for this day");
    }
}
