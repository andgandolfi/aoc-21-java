package xyz.gandolfi.aoc21.day17;

import java.util.ArrayList;
import java.util.List;

public class Trajectory {
    private final List<Position> positions;
    private final int vx;
    private final int vy;
    private final TargetArea targetArea;

    private boolean tooLow = false;
    private boolean tooHigh = false;
    private boolean inTargetArea = false;

    private int maxYPosition = 0;

    public Trajectory(int vx, int vy, TargetArea targetArea) {
        this.vx = vx;
        this.vy = vy;
        this.targetArea = targetArea;
        positions = new ArrayList<>();
        run();
    }

    public void run() {
        int currVx = vx;
        int currVy = vy;
        Position p = new Position(vx, vy);
        positions.add(p);
        maxYPosition = Math.max(maxYPosition, p.getY());

        while (true) {
            if (p.getX() < targetArea.getMinX() && p.getY() < targetArea.getMinY()) {
                tooLow = true;
                return;
            }
            if (p.getX() > targetArea.getMaxX() && p.getY() > targetArea.getMaxY()) {
                tooHigh = true;
                return;
            }
            if ((p.getX() > targetArea.getMaxX() || currVx == 0) && p.getY() < targetArea.getMinY())
                return;
            if (p.getX() >= targetArea.getMinX() && p.getX() <= targetArea.getMaxX() &&
                 p.getY() >= targetArea.getMinY() && p.getY() <= targetArea.getMaxY()) {
                inTargetArea = true;
                return;
            }

            if (currVx > 0) currVx -= 1;
            else if (currVx < 0) currVx += 1;
            currVy -= 1;
            p = new Position(p.getX() + currVx, p.getY() + currVy);
            positions.add(p);
            maxYPosition = Math.max(maxYPosition, p.getY());
        }
    }

    public boolean isTooLow() {
        return tooLow;
    }

    public boolean isTooHigh() {
        return tooHigh;
    }

    public boolean isInTargetArea() {
        return inTargetArea;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public int getMaxYPosition() {
        return maxYPosition;
    }
}
