package xyz.gandolfi.aoc21.day19;

import java.util.*;

public class BeaconSpace {
    private Map<Integer, BeaconScanner> scanners;
    private Map<Integer, BeaconPosition> origins;

    private BeaconSpace() {}

    public static BeaconSpace buildSpace(List<BeaconScanner> allScanners) {
        BeaconSpace space = new BeaconSpace();
        space.origins = new HashMap<>(allScanners.size());
        space.scanners = new HashMap<>(allScanners.size());
        for (BeaconScanner s : allScanners)
            space.scanners.put(s.getId(), s);
        space.origins.put(0, new BeaconPosition(0, 0, 0));
        space.alignAllScanners();
        return space;
    }

    private void alignAllScanners() {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Integer> toBeVisited = new LinkedList<>();
        visited.add(0);
        toBeVisited.addLast(0);
        while (!toBeVisited.isEmpty()) {
            int scannerId = toBeVisited.removeFirst();
            BeaconScanner scannerA = scanners.get(scannerId);
            for (BeaconScanner scannerB : scanners.values()) {
                if (scannerB.getId() == scannerId || visited.contains(scannerB.getId()))
                    continue;
                BeaconScannersOverlap overlap = BeaconScannersOverlap.findOverlap(scannerA, scannerB);
                if (overlap != null) {
                    visited.add(scannerB.getId());
                    toBeVisited.addLast(scannerB.getId());
                    scanners.put(scannerB.getId(), overlap.transformScannerBToScannerARotationAndOrigin());
                    origins.put(scannerB.getId(), overlap.getOrigin());
                }
            }
        }
    }

    public List<BeaconScanner> getAlignedScanners() {
        return scanners.values().stream().toList();
    }

    public Map<Integer, BeaconPosition> getOrigins() {
        return origins;
    }
}
