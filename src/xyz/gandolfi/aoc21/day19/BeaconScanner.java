package xyz.gandolfi.aoc21.day19;

import java.util.List;
import java.util.Objects;

public class BeaconScanner {
    private final int id;
    private final List<BeaconPosition> positions;

    public BeaconScanner(int id, List<BeaconPosition> positions) {
        this.id = id;
        this.positions = positions;
    }

    public int getId() {
        return id;
    }

    public List<BeaconPosition> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BeaconScanner scanner = (BeaconScanner) o;
        return id == scanner.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "id=" + id +
                ", positions=" + positions +
                '}';
    }
}
