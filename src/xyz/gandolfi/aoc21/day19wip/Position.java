package xyz.gandolfi.aoc21.day19wip;

import java.util.Objects;

public class Position {
    private final int x;
    private final int y;
    private final int z;

    public Position(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Position parsePosition(String csv) {
        String[] parts = csv.split(",");
        return new Position(
            Integer.parseInt(parts[0]),
            Integer.parseInt(parts[1]),
            Integer.parseInt(parts[2])
        );
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public Position distanceFrom(Position other) {
        return new Position(
            x - other.getX(),
            y - other.getY(),
            z - other.getZ()
        );
    }

    public Position add(Position other) {
        return new Position(
            x + other.getX(),
            y + other.getY(),
            z + other.getZ()
        );
    }

    public Position flip() {
        return new Position(-x, -y, -z);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y && z == position.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + "," + z + ")";
    }
}
