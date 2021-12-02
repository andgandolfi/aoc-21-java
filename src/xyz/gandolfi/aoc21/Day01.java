package xyz.gandolfi.aoc21;

import java.util.Iterator;
import java.util.LinkedList;

public class Day01 {
    public static int countIncreases(String fileContent, int windowSize) {
        Iterator<Integer> it = fileContent.lines().map(Integer::parseInt).iterator();

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
        String fileContent = Utils.readFile("day01.txt");
        assert fileContent != null;

        System.out.print("Day 01a: ");
        System.out.println(countIncreases(fileContent, 1));

        System.out.print("Day 01b: ");
        System.out.println(countIncreases(fileContent, 3));
    }
}
