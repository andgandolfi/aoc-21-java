package xyz.gandolfi.aoc21.day02;

public abstract class SubmarinePosition {
    protected int horizontalPosition = 0;
    protected int verticalPosition = 0;

    public SubmarinePosition() {}

    public SubmarinePosition(int horizontalPosition, int verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public abstract SubmarinePosition applyCommand(SubmarineCommand command);

    @Override
    public String toString() {
        return "SubmarinePosition{horizontal=" + horizontalPosition + ", vertical=" + verticalPosition + '}';
    }
}
