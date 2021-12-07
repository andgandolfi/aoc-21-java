package xyz.gandolfi.aoc21.day07;

import java.util.List;
import java.util.function.Function;

public class CrabsSwarm {
    private final List<Integer> positions;
    private int min;
    private int max;

    public CrabsSwarm(List<Integer> positions) {
        this.positions = positions;
        min = positions.get(0);
        max = positions.get(1);
        for (int p : positions) {
            min = Math.min(min, p);
            max = Math.max(max, p);
        }
    }

    public int getCheapestPosition() {
        return calculateCheapestPosition(this::getFuelNeededAtPosition);
    }

    public int getCheapestPositionWithIncreaseCosts() {
        return calculateCheapestPosition(this::getFuelNeededAtPositionWithIncreaseCosts);
    }

    private int calculateCheapestPosition(Function<Integer, Integer> fuelConsumptionFn) {
        int maxBound = max;
        int minBound = min;
        int midPoint = (max + min) / 2;
        int bestFuel = Integer.MAX_VALUE;

        while (maxBound - minBound >= 0) {
            int fuelMid = fuelConsumptionFn.apply(midPoint);
            int fuelMid_m1 = fuelConsumptionFn.apply(midPoint - 1);
            int fuelMid_p1 = fuelConsumptionFn.apply(midPoint + 1);
            if (fuelMid <= fuelMid_m1 && fuelMid <= fuelMid_p1) {
                bestFuel = fuelMid;
                break;
            }
            if (fuelMid_m1 <= fuelMid) {
                bestFuel = fuelMid_m1;
                maxBound = midPoint - 1;
            } else {
                bestFuel = fuelMid_p1;
                minBound = midPoint + 1;
            }
            midPoint = (maxBound + minBound) / 2;
        }
        return bestFuel;
    }

    private int getFuelNeededAtPosition(int position) {
        int sum = 0;
        for (int p : positions) {
            sum += Math.abs(position - p);
        }
        return sum;
    }

    private int getFuelNeededAtPositionWithIncreaseCosts(int position) {
        int sum = 0;
        for (int p : positions) {
            int n = Math.abs(position - p);
            sum += n * (n + 1) / 2; // Gauss formula for sum
        }
        return sum;
    }
}
