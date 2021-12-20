package xyz.gandolfi.aoc21.day19;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BeaconScannersOverlap {
    private BeaconScanner scannerA;
    private BeaconScanner scannerB;

    private BeaconRotation rotation;
    private BeaconPosition origin;

    private BeaconScannersOverlap(BeaconScanner scannerA, BeaconScanner scannerB) {
        this.scannerA = scannerA;
        this.scannerB = scannerB;
    }

    public static BeaconScannersOverlap findOverlap(BeaconScanner scannerA, BeaconScanner scannerB) {
        BeaconScannersOverlap result = null;
        for (BeaconRotation rotation : BeaconRotation.rotations) {
            HashMap<BeaconPosition, Set<BeaconPosition>> distances = new HashMap<>();
            for (BeaconPosition pA : scannerA.getPositions()) {
                for (BeaconPosition pB: scannerB.getPositions()) {
                    BeaconPosition pBr = rotation.rotate(pB);
                    BeaconPosition pApBrDistance = pA.distance(pBr);
                    Set<BeaconPosition> distancesSet = distances.getOrDefault(pApBrDistance, new HashSet<>());
                    distancesSet.add(pBr);
                    distances.put(pApBrDistance, distancesSet);
                    if (distancesSet.size() >= 12) {
                        result = new BeaconScannersOverlap(scannerA, scannerB);
                        result.rotation = rotation;
                        result.origin = pApBrDistance;
                        return result;
                    }
                }
            }
        }
        return result;
    }

    public BeaconScanner transformScannerBToScannerARotationAndOrigin() {
        List<BeaconPosition> newPositionsB = scannerB.getPositions().stream()
                .map(p -> rotation.rotate(p))
                .map(p -> p.add(origin))
                .toList();
        return new BeaconScanner(scannerB.getId(), newPositionsB);
    }

    public BeaconPosition getOrigin() {
        return origin;
    }

    @Override
    public String toString() {
        return "BeaconScannersOverlap{" +
                "scannerA=" + scannerA.getId() +
                ", scannerB=" + scannerB.getId() +
                ", rotation=" + rotation +
                ", origin=" + origin +
                '}';
    }
}
