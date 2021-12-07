package xyz.gandolfi.aoc21.day07;

import xyz.gandolfi.aoc21.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day07.txt");
        assert inputLines != null;
        List<Integer> inputPositions = Arrays.stream(inputLines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();

        CrabsSwarm swarm = new CrabsSwarm(inputPositions);
        System.out.print("Day 07a: ");
        System.out.println(swarm.getCheapestPosition());

        System.out.print("Day 07b: ");
        System.out.println(swarm.getCheapestPositionWithIncreaseCosts());
    }
}
