package xyz.gandolfi.aoc21.day01;

import java.util.LinkedList;
import java.util.List;

public class Counters {
    public static int countIncreases(List<Integer> inputLines, int windowSize) {
        int increasesCount = 0;
        int prevWindowSum = 0;
        int currWindowSum = 0;
        LinkedList<Integer> queue = new LinkedList<>();

        for (Integer val : inputLines) {
            if (queue.size() < windowSize) {
                queue.addLast(val);
                prevWindowSum += val;
                currWindowSum += val;
                continue;
            }
            queue.addLast(val);
            currWindowSum = currWindowSum + val - queue.removeFirst();
            if (currWindowSum > prevWindowSum) increasesCount++;
            prevWindowSum = currWindowSum;
        }

        return increasesCount;
    }
}
