package xyz.gandolfi.aoc21.day19wip;

import java.util.*;
import java.util.stream.Collectors;

public class BeaconsMatcher {
    private final Map<Integer, Scanner> scanners;
    private final Map<Integer, List<BeaconTransition>> transitionsTo;

    public BeaconsMatcher(List<Scanner> scanners) {
        this.scanners = new HashMap<>();
        for (Scanner s : scanners)
            this.scanners.put(s.getId(), s);
        this.transitionsTo = new HashMap<>();
    }

    public void findScannersWithCommonBeacons() {
        for (int i = 0; i < scanners.size(); ++i) {
            for (int j = i + 1; j < scanners.size(); ++j) {
                boolean foundRotation = false;
                for (int r = 0; r < Rotation.rotations.size() && !foundRotation; ++r) {
                    BeaconTransition transition = new BeaconTransition(scanners.get(i), scanners.get(j), Rotation.rotations.get(r));
                    transition.findMatches();
                    if (transition.isMatching()) {
                        var transitionsToJ = transitionsTo.getOrDefault(j, new ArrayList<>());
                        var transitionsToI = transitionsTo.getOrDefault(i, new ArrayList<>());
                        transitionsToJ.add(transition);
                        transitionsToI.add(transition.flip());
                        transitionsTo.put(j, transitionsToJ);
                        transitionsTo.put(i, transitionsToI);
                    }
                }
            }
        }
    }

    public Map<Integer, BeaconTransition> getTransitionsDependency() {
        Map<Integer, BeaconTransition> next = new HashMap<>();
        LinkedList<Integer> toBeVisited = new LinkedList<>();
        next.put(0, null);
        toBeVisited.addLast(0);
        while (!toBeVisited.isEmpty()) {
            int curr = toBeVisited.removeFirst();
            List<BeaconTransition> transitionsToCurr = transitionsTo.get(curr);
            for (BeaconTransition trans : transitionsToCurr) {
                int fromId = trans.getScannerStart().getId();
                if (next.containsKey(fromId))
                    continue;
                next.put(fromId, trans);
                toBeVisited.addLast(fromId);
            }
        }
        return next;
    }

    public Set<Position> getResultForAllScanners() {
        Set<Position> positions = new HashSet<>();
        for (int id :  scanners.keySet())
            positions.addAll(getResultFor(id));
        return positions;
    }

    public Set<Position> getResultFor(int scannerId) {
        Scanner scanner = scanners.get(scannerId);
        Set<Position> positions = scanner.getPositions();
        Map<Integer, BeaconTransition> transitionMap = getTransitionsDependency();
        BeaconTransition trans = transitionMap.get(scannerId);
        while (trans != null) {
            BeaconTransition ttt = trans;
            positions = positions.stream()
                    .map(p -> ttt.getRotation().rotate(p).distanceFrom(ttt.getTranslation()))
                    .collect(Collectors.toSet());
            trans = transitionMap.get(trans.getScannerEnd().getId());
        }
        return positions;
    }


//    private Set<Position> match() {
//        matches = new HashSet<>();
//        matchesBackRotations = new HashMap<>();
//        scannersPosition = new HashMap<>();
//        scannersPosition.put(0, new Position(0, 0, 0));
//
//        for (int i = 0; i < scanners.size(); ++i) {
//            for (int j = i + 1; j < scanners.size(); ++j) {
//                boolean foundRotation = false;
//                for (int r = 0; r < Rotation.rotations.size() && !foundRotation; ++r) {
//                    Pair<Position, Set<Position>> match = scannerMatch(scanners.get(i), scanners.get(j), Rotation.rotations.get(r));
//                    if (match != null) {
//                        matchesBackRotations.put(j, new int[] {i,  r});
//                        Pair<Position, Set<Position>> newMatch = rotateMatchesBackTo0(match, i);
//                        if (newMatch != null) {
//                            Position prevPosition = scannersPosition.get(i);
//                            Position newPosition = newMatch.getFirst().add(prevPosition);
//                            scannersPosition.put(j, newPosition);
//                            Set<Position> newMatches = newMatch.getSecond().stream().map(p -> prevPosition.add(p)).collect(Collectors.toSet());
//                            matches.addAll(newMatches);
//                            foundRotation = true;
//                        }
//                    }
//                }
//            }
//        }
//
//        return matches;
//    }
//
//    private Pair<Position, Set<Position>> rotateMatchesBackTo0(Pair<Position, Set<Position>> matches, int scanner) {
//        while (scanner != 0) {
//            if (!matchesBackRotations.containsKey(scanner))
//                return null;
//            int nextScanner = matchesBackRotations.get(scanner)[0];
//            int rotationIdx = matchesBackRotations.get(scanner)[1];
//
//            matches = new Pair<>(
//                    Arrays.stream(new Position[] {matches.getFirst()}).map(Rotation.rotationsBack.get(rotationIdx)).findFirst().get(),
//                    matches.getSecond().stream().map(Rotation.rotationsBack.get(rotationIdx)).collect(Collectors.toSet())
//            );
//
//            scanner = nextScanner;
//        }
//        return matches;
//    }
//
//    private Pair<Position, Set<Position>> scannerMatch(Scanner scanner0, Scanner scanner1, Function<Position, Position> rotation) {
//        HashMap<Position, Set<Position>> distances = new HashMap<>();
//        Pair<Position, Set<Position>> matchingPositions = null;
//
//        for (Position p0: scanner0.getPositions()) {
//            for (Position p1 : scanner1.getPositions()) {
//                Position p1r = rotation.apply(p1);
//                Position dist = p0.distanceFrom(p1r);
//                Set<Position> distSet = distances.getOrDefault(dist, new HashSet<>());
//                distSet.add(p0);
//                distances.put(dist, distSet);
//                if (distSet.size() >= 12)
//                    matchingPositions = new Pair<>(dist, distSet);
//            }
//        }
//
//        return matchingPositions;
//    }
}
