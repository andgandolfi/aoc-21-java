package xyz.gandolfi.aoc21.day17;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TargetArea {
    private final int minX;
    private final int maxX;
    private final int minY;
    private final int maxY;

    public TargetArea(String targetStringDefinition) {
        Pattern pattern = Pattern.compile("^target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)$");
        Matcher matcher = pattern.matcher(targetStringDefinition);
        boolean matched = matcher.matches();
        assert matched;

        minX = Integer.parseInt(matcher.group(1));
        maxX = Integer.parseInt(matcher.group(2));
        minY = Integer.parseInt(matcher.group(3));
        maxY = Integer.parseInt(matcher.group(4));
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    @Override
    public String toString() {
        return "TargetArea{x=" + minX + ".." + maxX + ", y=" + minY + ".." + maxY + '}';
    }
}
