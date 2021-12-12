package xyz.gandolfi.aoc21.day12;

import java.util.*;

public class PassagesGraph {
    private final Map<String, Set<String>> graph;

    public PassagesGraph(List<String> inputLines) {
        graph = new HashMap<>();
        for (String line: inputLines) {
            String[] parts = line.split("-");
            if (!graph.containsKey(parts[0]))
                graph.put(parts[0], new HashSet<>());
            if (!graph.containsKey(parts[1]))
                graph.put(parts[1], new HashSet<>());
            graph.get(parts[0]).add(parts[1]);
            graph.get(parts[1]).add(parts[0]);
        }
    }

    public int countAllPaths() {
        return countAllPaths("start", new HashSet<>());
    }

    public int countAllPathsSmallTwice() {
        return countAllPathsSmallTwice("start", new HashSet<>(), null);
    }

    private int countAllPaths(String vertex, Set<String> smallsAlreadyVisited) {
        if (vertex.equals("end"))
            return 1;
        if (smallsAlreadyVisited.contains(vertex))
            return 0;
        int sum = 0;

        for (String neighbor : graph.getOrDefault(vertex, new HashSet<>(0))) {
            Set<String> smallsAlreadyVisitedCopy = new HashSet<>(smallsAlreadyVisited);

            if (vertex.toLowerCase().equals(vertex))
                smallsAlreadyVisitedCopy.add(vertex);
            sum += countAllPaths(neighbor, smallsAlreadyVisitedCopy);
        }

        return sum;
    }

    private int countAllPathsSmallTwice(String vertex, Set<String> smallsAlreadyVisited,
                                        String smallCaveAlreadyVisitedTwice) {
        if (vertex.equals("start") && !smallsAlreadyVisited.isEmpty())
            return 0;
        if (vertex.equals("end"))
            return 1;
        if (smallCaveAlreadyVisitedTwice != null) {
            if (vertex.equals(smallCaveAlreadyVisitedTwice))
                return 0;
            if (smallsAlreadyVisited.contains(vertex))
                return 0;
        }

        int sum = 0;
        for (String neighbor : graph.getOrDefault(vertex, new HashSet<>(0))) {
            Set<String> smallsAlreadyVisitedCopy = new HashSet<>(smallsAlreadyVisited);
            String smallCaveAlreadyVisitedTwiceCopy = smallCaveAlreadyVisitedTwice;

            if (vertex.toLowerCase().equals(vertex)) {
                if (smallsAlreadyVisitedCopy.contains(vertex)) {
                    assert smallCaveAlreadyVisitedTwiceCopy == null;
                    smallCaveAlreadyVisitedTwiceCopy = vertex;
                } else {
                    smallsAlreadyVisitedCopy.add(vertex);
                }
            }

            sum += countAllPathsSmallTwice(neighbor, smallsAlreadyVisitedCopy,
                                           smallCaveAlreadyVisitedTwiceCopy);
        }
        return sum;
    }

    @Override
    public String toString() {
        return graph.toString();
    }
}
