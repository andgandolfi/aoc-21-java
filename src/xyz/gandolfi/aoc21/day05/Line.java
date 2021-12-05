package xyz.gandolfi.aoc21.day05;

public class Line {
    private final Point startPoint;
    private final Point endPoint;

    public Line(Point start, Point end) {
        startPoint = start;
        endPoint = end;
    }

    public Line(String instruction) {
        String[] parts = instruction.split(" -> ");
        startPoint = new Point(parts[0]);
        endPoint = new Point(parts[1]);
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public int getXDeltaDirection() {
        return Integer.compare(endPoint.getX() - startPoint.getX(), 0);
    }

    public int getYDeltaDirection() {
        return Integer.compare(endPoint.getY() - startPoint.getY(), 0);
    }

    public boolean isDiagonal() {
        return startPoint.getX() != endPoint.getX() && startPoint.getY() != endPoint.getY();
    }
}
