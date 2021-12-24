package xyz.gandolfi.aoc21.day23;

import java.util.Objects;

public class Cursor {
    private final int x;
    private final int y;

    public Cursor(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Cursor getCursorInDir(int x, int y) {
        return new Cursor(this.x + x, this.y + y);
    }

    public boolean isInAllowedSpace(int slotsHeight) {
        return (y == 0 && x >= 0 && x < 11) || (y >= 1 && y <= slotsHeight && (x == 2 || x == 4 || x == 6 || x == 8));
    }

    public boolean isBlockingSpaceInFrontOfRoom() {
        return y == 0 && (x == 2 || x == 4 || x == 6 || x == 8);
    }

    public char expectedChar(int slotsHeight) {
        if (y < 1 && y > slotsHeight)
            return 0;

        return switch (x) {
            case 2 -> 'A';
            case 4 -> 'B';
            case 6 -> 'C';
            case 8 -> 'D';
            default -> 0;
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cursor cursor = (Cursor) o;
        return x == cursor.x && y == cursor.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Cursor{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
