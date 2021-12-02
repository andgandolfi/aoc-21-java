package xyz.gandolfi.aoc21.day02;

public class SubmarineTypeBPosition extends SubmarinePosition {
    int aim = 0;

    public SubmarineTypeBPosition() {
        super();
    }

    public SubmarineTypeBPosition(int horizontalPosition, int verticalPosition, int aim) {
        super(horizontalPosition, verticalPosition);
        this.aim = aim;
    }

    public SubmarineTypeBPosition applyCommand(SubmarineCommand command) {
        this.aim += command.getVerticalCoefficient() * command.getUnits();
        if (command.getDirection() == SubmarineDirection.FORWARD) {
            this.horizontalPosition += command.getHorizontalCoefficient() * command.getUnits();
            this.verticalPosition += this.aim * command.getUnits();
        }
        return this;
    }
}
