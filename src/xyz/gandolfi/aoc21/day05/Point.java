package xyz.gandolfi.aoc21.day05;

public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(String commaSeparatedPoint){
        String[] parts = commaSeparatedPoint.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
