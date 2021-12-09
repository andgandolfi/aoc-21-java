package xyz.gandolfi.aoc21.day09;

public class PointCoordinates {
    private final int x;
    private final int y;

    public PointCoordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PointCoordinates that = (PointCoordinates) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return x * 10 + y;
    }
}
