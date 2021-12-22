package xyz.gandolfi.aoc21.day22;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day22.txt");
        assert inputLines != null;

        List<RebootStep> rebootSteps = inputLines.stream()
            .map(RebootStep::new)
            .toList();

        final Cuboid spaceLimit = new Cuboid(new Point(-50, -50, -50), new Point(50, 50, 50));

        System.out.print("Day 22a: ");
        Reactor reactorA = new Reactor(rebootSteps, spaceLimit);
        System.out.println(reactorA.countActiveCubes());

        System.out.print("Day 22b: ");
        Reactor reactorB = new Reactor(rebootSteps);
        System.out.println(reactorB.countActiveCubes());
    }
}
