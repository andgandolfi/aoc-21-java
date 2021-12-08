package xyz.gandolfi.aoc21.day08;

import java.util.*;

public class Pattern {
    public static Pattern number0 = new Pattern("abcefg");
    public static Pattern number1 = new Pattern("cf");
    public static Pattern number2 = new Pattern("acdeg");
    public static Pattern number3 = new Pattern("acdfg");
    public static Pattern number4 = new Pattern("bcdf");
    public static Pattern number5 = new Pattern("abdfg");
    public static Pattern number6 = new Pattern("abdefg");
    public static Pattern number7 = new Pattern("acf");
    public static Pattern number8 = new Pattern("abcdefg");
    public static Pattern number9 = new Pattern("abcdfg");

    private final Set<String> segments;

    public Pattern(String segments) {
        this.segments = new HashSet<>(List.of(segments.split("")));
    }

    public Pattern(Set<String> segments) {
        this.segments = segments;
    }

    public Set<String> getSegments() {
        return segments;
    }

    public Pattern removeSegments(Pattern segmentsToBeRemoved) {
        Set<String> newSet = new HashSet<>(segments);
        newSet.removeAll(segmentsToBeRemoved.getSegments());
        return new Pattern(newSet);
    }

    public Pattern intersectSegments(Pattern segmentsToBeIntersected) {
        Set<String> newSet = new HashSet<>(segments);
        newSet.retainAll(segmentsToBeIntersected.getSegments());
        return new Pattern(newSet);
    }

    public Pattern remap(Map<String, String> mapping) {
        Set<String> newSet = new HashSet<>();
        for (String s : segments)
            newSet.add(mapping.get(s));
        return new Pattern(newSet);
    }

    public Integer getValue() {
        int size = segments.size();
        if (size == 2) {
            if (this.equals(Pattern.number1)) return 1;
        } else if (size == 3) {
            if (this.equals(Pattern.number7)) return 7;
        } else if (size == 4) {
            if (this.equals(Pattern.number4)) return 4;
        } else if (size == 7) {
            if (this.equals(Pattern.number8)) return 8;
        } else if (size == 5) {
            if (this.equals(Pattern.number2)) return 2;
            if (this.equals(Pattern.number3)) return 3;
            if (this.equals(Pattern.number5)) return 5;
        } else if (size == 6) {
            if (this.equals(Pattern.number0)) return 0;
            if (this.equals(Pattern.number6)) return 6;
            if (this.equals(Pattern.number9)) return 9;
        }
        return null;
    }

    public String getSortedSegments() {
        List<String> sList = new ArrayList<>(segments);
        sList.sort(String::compareToIgnoreCase);
        return String.join("", sList);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pattern pattern = (Pattern) o;
        return Objects.equals(hashCode(), pattern.hashCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getSortedSegments());
    }

    @Override
    public String toString() {
        return "Pattern{segments=" + getSortedSegments() + '}';
    }
}
