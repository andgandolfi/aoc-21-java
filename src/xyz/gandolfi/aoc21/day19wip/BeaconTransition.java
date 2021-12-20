package xyz.gandolfi.aoc21.day19wip;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class BeaconTransition {
    private Scanner scannerStart;
    private Scanner scannerEnd;
    private Rotation rotation;

    private Position translation;

    public BeaconTransition(Scanner scannerStart, Scanner scannerEnd, Rotation rotation) {
        this.scannerStart = scannerStart;
        this.scannerEnd = scannerEnd;
        this.rotation = rotation;
    }

    public void findMatches() {
        HashMap<Position, Set<Position>> distances = new HashMap<>();

        for (Position p0 : scannerStart.getPositions())
            for (Position p1 : scannerEnd.getPositions()) {
                Position p1r = rotation.rotate(p1);
                Position p0p1rDistance = p0.distanceFrom(p1r);
                Set<Position> distancesSet = distances.getOrDefault(p0p1rDistance, new HashSet<>());
                distancesSet.add(p0);
                distances.put(p0p1rDistance, distancesSet);
                if (distancesSet.size() >= 12) {
                    translation = p0p1rDistance;
                }
            }
    }

    public Scanner getScannerStart() {
        return scannerStart;
    }

    public Scanner getScannerEnd() {
        return scannerEnd;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public Position getTranslation() {
        return translation;
    }

    public boolean isMatching() {
        return translation != null;
    }

    public BeaconTransition flip() {
        BeaconTransition flippedTransition = new BeaconTransition(scannerEnd, scannerStart, rotation.getReverseRotation());
        flippedTransition.translation = translation.flip();
        return flippedTransition;
    }
}
