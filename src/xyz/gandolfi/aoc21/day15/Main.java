package xyz.gandolfi.aoc21.day15;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day15.txt");
        assert inputLines != null;

        RiskLevelMap riskLevelMap = new RiskLevelMap(inputLines);

        System.out.print("Day 15a: ");
        System.out.println(riskLevelMap.getLowestRiskPath());

        System.out.print("Day 15b: ");
        riskLevelMap.expandMap(5);
        System.out.println(riskLevelMap.getLowestRiskPath());
    }
}
