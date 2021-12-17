package xyz.gandolfi.aoc21.day17;

public class Shooter {
    private final TargetArea targetArea;
    private int maxY = 0;
    private long inTargetCount = 0;

    public Shooter(TargetArea targetArea) {
        this.targetArea = targetArea;
    }

    public void computeTrajectories() {
        Trajectory prevT;
        Trajectory currT;
        for (int vy = targetArea.getMinY(); vy < maxY - targetArea.getMaxY(); ++vy) {
            currT = null;
            prevT = null;
            for (int vx = 1; vx <= targetArea.getMaxX(); ++vx) {
                prevT = currT;
                currT = new Trajectory(vx, vy, targetArea);
                if (currT.isTooHigh())
                    break;
                if (currT.isInTargetArea()) {
                    maxY = Math.max(maxY, currT.getMaxYPosition());
                    ++inTargetCount;
                }
            }
            if ((prevT == null || prevT.isTooLow()) && currT.isTooHigh())
                break;
        }
    }

    public int getMaxY() {
        return maxY;
    }

    public long getInTargetCount() {
        return inTargetCount;
    }
}
