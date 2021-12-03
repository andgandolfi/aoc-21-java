package xyz.gandolfi.aoc21.day02;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static SubmarinePosition runSubmarine(List<String> inputLines, SubmarinePosition initialPosition) {
        for (String cmd: inputLines) {
            initialPosition.applyCommand(new SubmarineCommand(cmd));
        }

        return initialPosition;
    }

    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day02.txt");
        assert inputLines != null;

        System.out.print("Day 02a: ");
        SubmarinePosition finalPositionA = runSubmarine(inputLines, new SubmarineTypeAPosition());
        System.out.println(finalPositionA.horizontalPosition * finalPositionA.verticalPosition);

        System.out.print("Day 02b: ");
        SubmarinePosition finalPositionB = runSubmarine(inputLines, new SubmarineTypeBPosition());
        System.out.println(finalPositionB.horizontalPosition * finalPositionB.verticalPosition);
    }
}
