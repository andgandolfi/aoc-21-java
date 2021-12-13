package xyz.gandolfi.aoc21.day13;

public class Fold {
    private final char type;
    private final int value;

    public Fold(String inputLine) {
        inputLine = inputLine.replace("fold along ", "");
        String[] parts = inputLine.split("=");
        type = parts[0].charAt(0);
        value = Integer.parseInt(parts[1]);
    }

    public char getType() {
        return type;
    }

    public int getValue() {
        return value;
    }
}
