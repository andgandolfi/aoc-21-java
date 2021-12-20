package xyz.gandolfi.aoc21.day19;

import xyz.gandolfi.aoc21.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day19.txt");
        assert inputLines != null;

        Pattern pattern = Pattern.compile("^--- scanner (\\d+) ---$");
        List<BeaconScanner> scanners = new ArrayList<>();
        int scannerId = 0;
        List<BeaconPosition> positions = new ArrayList<>();
        for (String line : inputLines) {
            if (line.startsWith("---")) {
                Matcher matcher = pattern.matcher(line);
                if (!matcher.matches())
                    throw new RuntimeException("Malformed scanner header");
                scannerId = Integer.parseInt(matcher.group(1));
            } else if (line.isBlank()) {
                scanners.add(new BeaconScanner(scannerId, positions));
                positions = new ArrayList<>();
            } else {
                positions.add(new BeaconPosition(line));
            }
        }
        scanners.add(new BeaconScanner(scannerId, positions));

        System.out.print("Day 19a: ");
        BeaconSpace space = BeaconSpace.buildSpace(scanners);
        Set<BeaconPosition> spacePoints = space.getAlignedScanners().stream()
            .flatMap(scanner -> scanner.getPositions().stream())
            .collect(Collectors.toSet());
        System.out.println(spacePoints.size());

        System.out.print("Day 19b: ");
        int maxOriginsDistance = 0;
        List<BeaconPosition> origins = space.getOrigins().values().stream().toList();
        for (int i = 0; i < origins.size(); ++i)
            for (int j = 0; j < origins.size(); ++j)
                maxOriginsDistance = Math.max(maxOriginsDistance, origins.get(i).getManhattanDistanceFrom(origins.get(j)));
        System.out.println(maxOriginsDistance);
    }
}
