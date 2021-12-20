package xyz.gandolfi.aoc21.day19wip;

import java.util.Objects;
import java.util.Set;

public class Scanner {
    private final int id;
    private final Set<Position> positions;

    public Scanner(int id, Set<Position> positions) {
        this.id = id;
        this.positions = positions;
    }

    public int getId() {
        return id;
    }

    public Set<Position> getPositions() {
        return positions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scanner scanner = (Scanner) o;
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
