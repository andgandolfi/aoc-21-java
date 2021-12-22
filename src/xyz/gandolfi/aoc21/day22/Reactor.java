package xyz.gandolfi.aoc21.day22;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Reactor {
    private final List<RebootStep> rebootSteps;
    private final Cuboid spaceLimit;

    public Reactor(List<RebootStep> rebootSteps) {
        this(rebootSteps, null);
    }

    public Reactor(List<RebootStep> rebootSteps, Cuboid spaceLimit) {
        this.rebootSteps = rebootSteps;
        this.spaceLimit = spaceLimit;
    }

    public long countActiveCubes() {
        Set<Cuboid> cuboids = new HashSet<>();

        for (RebootStep step : rebootSteps) {
            Cuboid stepCuboid = step.getCuboid();
            if (spaceLimit != null)
                stepCuboid = stepCuboid.getOverlapWith(spaceLimit);
            if (stepCuboid == null)
                continue;
            Set<Cuboid> toBeRemoved = new HashSet<>();
            Set<Cuboid> toBeAdded = new HashSet<>();
            for (Cuboid prevCuboid : cuboids) {
                if (prevCuboid.isContainedIn(stepCuboid)) {
                    toBeRemoved.add(prevCuboid);
                } else if (stepCuboid.isContainedIn(prevCuboid)) {
                    toBeRemoved.add(prevCuboid);
                    toBeAdded.addAll(prevCuboid.explode(stepCuboid));
                } else if (stepCuboid.getOverlapWith(prevCuboid) != null) {
                    List<Cuboid> exploded = prevCuboid.explode(stepCuboid);
                    toBeRemoved.add(prevCuboid);
                    Cuboid centerPiece = exploded.remove(exploded.size() - 1);
                    toBeRemoved.add(centerPiece);
                    toBeAdded.addAll(exploded);
                }
            }
            cuboids.addAll(toBeAdded);
            cuboids.removeAll(toBeRemoved);
            if (step.isOn())
                cuboids.add(stepCuboid);
            else
                cuboids.remove(stepCuboid);
        }

        long sum = 0;

        for (Cuboid cuboid : cuboids)
            sum += cuboid.getCuboidSize();

        return sum;
    }
}
