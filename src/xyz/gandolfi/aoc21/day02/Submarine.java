package xyz.gandolfi.aoc21.day02;

import java.util.List;

public abstract class Submarine {
    protected int horizontalPosition = 0;
    protected int verticalPosition = 0;

    public Submarine() {}

    public Submarine(int horizontalPosition, int verticalPosition) {
        this.horizontalPosition = horizontalPosition;
        this.verticalPosition = verticalPosition;
    }

    public abstract void applyCommand(SubmarineCommand command);

    public void runSubmarine(List<SubmarineCommand> commands) {
        for (SubmarineCommand cmd: commands)
            this.applyCommand(cmd);
    }

    @Override
    public String toString() {
        return "SubmarinePosition{horizontal=" + horizontalPosition + ", vertical=" + verticalPosition + '}';
    }
}
