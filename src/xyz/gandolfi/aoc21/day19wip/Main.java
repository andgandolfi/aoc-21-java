package xyz.gandolfi.aoc21.day19wip;

import xyz.gandolfi.aoc21.Utils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day19.txt");
        assert inputLines != null;

        Pattern pattern = Pattern.compile("^--- scanner (\\d+) ---$");
        List<Scanner> scanners = new ArrayList<>();
        int scannerId = 0;
        Set<Position> positions = new HashSet<>();
        for (String line : inputLines) {
            if (line.startsWith("---")) {
                Matcher matcher = pattern.matcher(line);
                matcher.matches();
                scannerId = Integer.parseInt(matcher.group(1));
            } else if (line.isBlank()) {
                scanners.add(new Scanner(scannerId, positions));
                positions = new HashSet<>();
            } else {
                positions.add(Position.parsePosition(line));
            }
        }
        scanners.add(new Scanner(scannerId, positions));

        System.out.print("Day 19a: ");
        BeaconsMatcher matcher = new BeaconsMatcher(scanners);
        matcher.findScannersWithCommonBeacons();
        System.out.println(matcher.getResultFor(4));

        System.out.print("Day 19b: ");
        System.out.println();
    }
}
