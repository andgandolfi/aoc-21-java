package xyz.gandolfi.aoc21.day05;

import java.util.ArrayList;
import java.util.List;

public class Diagram {
    private int[][] matrix;
    private List<Line> lines;

    public Diagram(List<String> instructions) {
        lines = new ArrayList<>(instructions.size());
        for (String instr : instructions)
            lines.add(new Line(instr));
    }

    public int findOverlaps() {
        int maxX = 0, maxY = 0;
        for (Line line : lines) {
            maxX = Math.max(maxX, Math.max(line.getStartPoint().getX(), line.getEndPoint().getX()));
            maxY = Math.max(maxY, Math.max(line.getStartPoint().getY(), line.getEndPoint().getY()));
        }
        matrix = new int[maxX+1][maxY+1];
        for (Line line : lines) {
            int startX = line.getStartPoint().getX();
            int startY = line.getStartPoint().getY();
            int endX = line.getEndPoint().getX();
            int endY = line.getEndPoint().getY();
            int deltaX = line.getXDeltaDirection();
            int deltaY = line.getYDeltaDirection();
            for (int x = startX, y = startY; x != endX + deltaX || y != endY + deltaY; x += deltaX, y += deltaY)
                matrix[x][y]++;
        }

        return countOverlaps();
    }

    public void discardDiagonalLines() {
        List<Line> straightLines = new ArrayList<>();
        for (Line line : lines)
            if (!line.isDiagonal())
                straightLines.add(line);
        lines = straightLines;
    }

    private int countOverlaps() {
        int count = 0;
        for (int x = 0; x < matrix.length; x++)
            for (int y = 0; y < matrix[x].length; y++)
                if (matrix[x][y] >= 2)
                    count++;
        return count;
    }
}
