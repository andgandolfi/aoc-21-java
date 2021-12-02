package xyz.gandolfi.aoc21.day02;

import xyz.gandolfi.aoc21.Utils;

import java.util.Iterator;
import java.util.stream.Stream;

public class Main {
    public static SubmarineTypeAPosition runSubmarineA(Stream<String> inputLines) {
        Iterator<String> it = inputLines.iterator();

        SubmarineTypeAPosition position = new SubmarineTypeAPosition();

        while (it.hasNext()) {
            String cmd = it.next();
            position.applyCommand(new SubmarineCommand(cmd));
        }

        return position;
    }

    public static SubmarineTypeBPosition runSubmarineB(Stream<String> inputLines) {
        Iterator<String> it = inputLines.iterator();

        SubmarineTypeBPosition position = new SubmarineTypeBPosition();

        while (it.hasNext()) {
            String cmd = it.next();
            position.applyCommand(new SubmarineCommand(cmd));
        }

        return position;
    }

    public static void main(String[] args) {
        Stream<String> inputLines;

        System.out.print("Day 02a: ");
        inputLines = Utils.getInputFileLinesStream("day02.txt");
        assert inputLines != null;
        SubmarineTypeAPosition finalPositionA = runSubmarineA(inputLines);
        System.out.println(finalPositionA.horizontalPosition * finalPositionA.verticalPosition);

        System.out.print("Day 02b: ");
        inputLines = Utils.getInputFileLinesStream("day02.txt");
        assert inputLines != null;
        SubmarineTypeBPosition finalPositionB = runSubmarineB(inputLines);
        System.out.println(finalPositionB.horizontalPosition * finalPositionB.verticalPosition);
    }
}
