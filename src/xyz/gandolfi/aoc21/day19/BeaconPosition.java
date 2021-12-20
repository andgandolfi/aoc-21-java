package xyz.gandolfi.aoc21.day19;

import java.util.Objects;

public class BeaconPosition {
    private final int x;
    private final int y;
    private final int z;

    public BeaconPosition(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public BeaconPosition(String csv) {
        String[] parts = csv.split(",");
        this.x = Integer.parseInt(parts[0]);
        this.y = Integer.parseInt(parts[1]);
        this.z = Integer.parseInt(parts[2]);
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

    /*
    * Distance that I have to remove from "other" to get to "this" position
    */
    public BeaconPosition distance(BeaconPosition other) {
        return new BeaconPosition(
            x - other.getX(),
            y - other.getY(),
            z - other.getZ()
        );
    }

    public BeaconPosition add(BeaconPosition other) {
        return new BeaconPosition(
            x + other.getX(),
            y + other.getY(),
            z + other.getZ()
        );
    }

    public int getManhattanDistanceFrom(BeaconPosition other) {
        BeaconPosition dist = this.distance(other);
        return Math.abs(dist.getX()) + Math.abs(dist.getY()) + Math.abs(dist.getZ());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeaconPosition position = (BeaconPosition) o;
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
