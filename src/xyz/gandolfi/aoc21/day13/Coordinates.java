package xyz.gandolfi.aoc21.day13;

public class Coordinates {
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates(String csv) {
        String[] parts = csv.split(",");
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
