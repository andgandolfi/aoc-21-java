package xyz.gandolfi.aoc21.day17;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day17.txt");
        assert inputLines != null;

        TargetArea targetArea = new TargetArea(inputLines.get(0));
        Shooter shooter = new Shooter(targetArea);
        shooter.computeTrajectories();

        System.out.print("Day 17a: ");
        System.out.println(shooter.getMaxY());

        System.out.print("Day 17b: ");
        System.out.println(shooter.getInTargetCount());
    }
}
