package xyz.gandolfi.aoc21.day02;

public class SubmarineCommand {
    private int horizontalCoefficient = 0;
    private int verticalCoefficient = 0;
    private int units;
    private SubmarineDirection direction;

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

    public int getHorizontalCoefficient() {
        return horizontalCoefficient;
    }

    public int getVerticalCoefficient() {
        return verticalCoefficient;
    }

    public int getUnits() {
        return units;
    }

    public SubmarineDirection getDirection() {
        return direction;
    }
}
