package xyz.gandolfi.aoc21.day01;

import xyz.gandolfi.aoc21.Utils;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class Main {
    public static int countIncreases(Stream<String> inputLines, int windowSize) {
        Iterator<Integer> it = inputLines.map(Integer::parseInt).iterator();

        int increasesCount = 0;
        int prevWindowSum = 0;
        int currWindowSum = 0;
        LinkedList<Integer> queue = new LinkedList<>();

        while (it.hasNext()) {
            int val = it.next();
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
        Stream<String> inputLines;

        System.out.print("Day 01a: ");
        inputLines = Utils.getInputFileLinesStream("day01.txt");
        assert inputLines != null;
        System.out.println(countIncreases(inputLines, 1));

        System.out.print("Day 01b: ");
        inputLines = Utils.getInputFileLinesStream("day01.txt");
        assert inputLines != null;
        System.out.println(countIncreases(inputLines, 3));
    }
}
