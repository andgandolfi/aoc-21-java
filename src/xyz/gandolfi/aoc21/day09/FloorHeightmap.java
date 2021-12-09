package xyz.gandolfi.aoc21.day09;

import java.util.*;

public class FloorHeightmap {
    private final int[][] heightmap;

    public FloorHeightmap(List<String> inputLines) {
        heightmap = new int[inputLines.size()][inputLines.get(0).length()];
        int x = 0;
        for (String line : inputLines) {
            int y = 0;
            for (String c : line.split("")) {
                heightmap[x][y++] = Integer.parseInt(c);
            }
            ++x;
        }
    }

    private List<PointCoordinates> findLowPointsCoordinates() {
        List<PointCoordinates> coordinates = new ArrayList<>();
        for (int x = 0; x < heightmap.length; ++x)
            for (int y = 0; y < heightmap[x].length; ++y)
                if ((x == 0 || heightmap[x-1][y] > heightmap[x][y]) &&
                        (x == heightmap.length - 1 || heightmap[x+1][y] > heightmap[x][y]) &&
                        (y == 0 || heightmap[x][y-1] > heightmap[x][y]) &&
                        (y == heightmap[x].length - 1 || heightmap[x][y+1] > heightmap[x][y]))
                    coordinates.add(new PointCoordinates(x, y));
        return coordinates;
    }

    public List<Integer> findLowPoints() {
        List<Integer> lowPoints = new ArrayList<>();
        for (PointCoordinates coordinates : findLowPointsCoordinates())
            lowPoints.add(heightmap[coordinates.getX()][coordinates.getY()]);
        return lowPoints;
    }

    public List<Set<PointCoordinates>> findAllBasins() {
        List<Set<PointCoordinates>> basins = new ArrayList<>();
        List<PointCoordinates> lowestPoints = findLowPointsCoordinates();

        for (PointCoordinates lowPoint : lowestPoints) {
            boolean lowPointPartOfExistingBasin = false;
            for (Set<PointCoordinates> basin : basins) {
                if  (basin.contains(lowPoint)) {
                    lowPointPartOfExistingBasin = true;
                    break;
                }
            }
            if (lowPointPartOfExistingBasin) continue;

            Set<PointCoordinates> newBasin = new HashSet<>();

            Stack<PointCoordinates> stack = new Stack<>();
            stack.push(lowPoint);
            while (!stack.isEmpty()) {
                PointCoordinates point = stack.pop();
                if (heightmap[point.getX()][point.getY()] == 9) continue;
                if (newBasin.contains(point)) continue;
                newBasin.add(point);

                if (point.getX() > 0)
                    stack.push(new PointCoordinates(point.getX() - 1, point.getY()));
                if (point.getX() < heightmap.length - 1)
                    stack.push(new PointCoordinates(point.getX() + 1, point.getY()));
                if (point.getY() > 0)
                    stack.push(new PointCoordinates(point.getX(), point.getY() - 1));
                if (point.getY() < heightmap[point.getX()].length - 1)
                    stack.push(new PointCoordinates(point.getX(), point.getY() + 1));
            }

            basins.add(newBasin);
        }

        return basins;
    }
}
