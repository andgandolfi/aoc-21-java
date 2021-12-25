package xyz.gandolfi.aoc21.day24;

public class InputProvider {
    private final String inputPortion;
    private int cursor;

    public InputProvider(String inputPortion) {
        this.inputPortion = inputPortion;
        this.cursor = 0;
    }

    public Character getNextInput() {
        if (cursor >= inputPortion.length())
            return null;
        return inputPortion.charAt(cursor++);
    }
}
