package xyz.gandolfi.aoc21.day02;

public class SubmarineTypeAPosition extends SubmarinePosition {
    public SubmarineTypeAPosition applyCommand(SubmarineCommand command) {
        this.horizontalPosition += command.getHorizontalCoefficient() * command.getUnits();
        this.verticalPosition += command.getVerticalCoefficient() * command.getUnits();
        return this;
    }
}
