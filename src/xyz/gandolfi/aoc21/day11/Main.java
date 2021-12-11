package xyz.gandolfi.aoc21.day11;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day11.txt");
        assert inputLines != null;

        System.out.print("Day 11a: ");
        EnergyLevels evolverA = new EnergyLevels(inputLines);
        evolverA.evolveNSteps(100);
        System.out.println(evolverA.getFlashesCount());

        System.out.print("Day 11b: ");
        EnergyLevels evolverB = new EnergyLevels(inputLines);
        evolverB.evolveUntilAllFlash();
        System.out.println(evolverB.getSteps());
    }
}
