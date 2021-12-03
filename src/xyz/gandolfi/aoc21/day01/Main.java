package xyz.gandolfi.aoc21.day01;

import xyz.gandolfi.aoc21.Utils;

import java.util.LinkedList;
import java.util.List;

public class Main {
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

    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day01.txt");
        assert inputLines != null;
        List<Integer> inputNums = inputLines.stream().map(Integer::parseInt).toList();

        System.out.print("Day 01a: ");
        System.out.println(countIncreases(inputNums, 1));

        System.out.print("Day 01b: ");
        System.out.println(countIncreases(inputNums, 3));
    }
}
