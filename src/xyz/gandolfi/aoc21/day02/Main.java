package xyz.gandolfi.aoc21.day02;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day02.txt");
        assert inputLines != null;
        List<SubmarineCommand> commands = inputLines.stream().map(SubmarineCommand::new).toList();

        System.out.print("Day 02a: ");
        Submarine submarineA = new SubmarineTypeA();
        submarineA.runSubmarine(commands);
        System.out.println(submarineA.horizontalPosition * submarineA.verticalPosition);

        System.out.print("Day 02b: ");
        Submarine submarineB = new SubmarineTypeB();
        submarineB.runSubmarine(commands);
        System.out.println(submarineB.horizontalPosition * submarineB.verticalPosition);
    }
}
