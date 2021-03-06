package xyz.gandolfi.aoc21.day02;

public class SubmarineTypeB extends Submarine {
    int aim = 0;

    public SubmarineTypeB() {
        super();
    }

    public SubmarineTypeB(int horizontalPosition, int verticalPosition, int aim) {
        super(horizontalPosition, verticalPosition);
        this.aim = aim;
    }

    public void applyCommand(SubmarineCommand command) {
        this.aim += command.getVerticalCoefficient() * command.getUnits();
        if (command.getDirection() == SubmarineDirection.FORWARD) {
            this.horizontalPosition += command.getHorizontalCoefficient() * command.getUnits();
            this.verticalPosition += this.aim * command.getUnits();
        }
    }
}
