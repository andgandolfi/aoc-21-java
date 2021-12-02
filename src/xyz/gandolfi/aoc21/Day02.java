package xyz.gandolfi.aoc21;

import java.util.Iterator;

public class Day02 {
    enum SubmarineDirection {
        FORWARD, UP, DOWN
    }

    static class SubmarineCommand {
        int horizontalCoefficient = 0;
        int verticalCoefficient = 0;
        int units;
        SubmarineDirection direction;

        public SubmarineCommand(String command) {
            String[] cmdParts = command.split(" ");
            String direction = cmdParts[0];
            units = Integer.parseInt(cmdParts[1]);
            switch (direction) {
                case "forward" -> {
                    horizontalCoefficient = 1;
                    this.direction = SubmarineDirection.FORWARD;
                }
                case "up" -> {
                    verticalCoefficient = -1;
                    this.direction = SubmarineDirection.UP;
                }
                case "down" -> {
                    verticalCoefficient = 1;
                    this.direction = SubmarineDirection.DOWN;
                }
            }
        }
    }

    static class SubmarineAPosition {
        int horizontalPosition = 0;
        int verticalPosition = 0;

        public SubmarineAPosition() {}

        public SubmarineAPosition(int horizontalPosition, int verticalPosition) {
            this.horizontalPosition = horizontalPosition;
            this.verticalPosition = verticalPosition;
        }

        public SubmarineAPosition applyCommand(SubmarineCommand command) {
            this.horizontalPosition += command.horizontalCoefficient * command.units;
            this.verticalPosition += command.verticalCoefficient * command.units;
            return this;
        }

        @Override
        public String toString() {
            return "SubmarineAPosition{horizontal=" + horizontalPosition +
                    ", vertical=" + verticalPosition + '}';
        }
    }

    static class SubmarineBPosition {
        int horizontalPosition = 0;
        int verticalPosition = 0;
        int aim = 0;

        public SubmarineBPosition() {}

        public SubmarineBPosition(int horizontalPosition, int verticalPosition, int aim) {
            this.horizontalPosition = horizontalPosition;
            this.verticalPosition = verticalPosition;
            this.aim = aim;
        }

        public SubmarineBPosition applyCommand(SubmarineCommand command) {
            this.aim += command.verticalCoefficient * command.units;
            if (command.direction == SubmarineDirection.FORWARD) {
                this.horizontalPosition += command.horizontalCoefficient * command.units;
                this.verticalPosition += this.aim * command.units;
            }
            return this;
        }

        @Override
        public String toString() {
            return "SubmarineBPosition{horizontal=" + horizontalPosition +
                    ", vertical=" + verticalPosition + '}';
        }
    }

    public static SubmarineAPosition runSubmarineA(String fileContent) {
        Iterator<String> it = fileContent.lines().iterator();

        SubmarineAPosition position = new SubmarineAPosition();

        while (it.hasNext()) {
            String cmd = it.next();
            position.applyCommand(new SubmarineCommand(cmd));
        }

        return position;
    }

    public static SubmarineBPosition runSubmarineB(String fileContent) {
        Iterator<String> it = fileContent.lines().iterator();

        SubmarineBPosition position = new SubmarineBPosition();

        while (it.hasNext()) {
            String cmd = it.next();
            position.applyCommand(new SubmarineCommand(cmd));
        }

        return position;
    }

    public static void main(String[] args) {
        String fileContent = Utils.readFile("day02.txt");
        assert fileContent != null;

        System.out.print("Day 02a: ");
        SubmarineAPosition finalPositionA = runSubmarineA(fileContent);
        System.out.println(finalPositionA.horizontalPosition * finalPositionA.verticalPosition);

        System.out.print("Day 02b: ");
        SubmarineBPosition finalPositionB = runSubmarineB(fileContent);
        System.out.println(finalPositionB.horizontalPosition * finalPositionB.verticalPosition);
    }
}
