package xyz.gandolfi.aoc21;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class Day01 {
    public static int countIncreases(String fileContent) {
        List<Integer> values = fileContent.lines()
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        int increases = 0;
        int prev = 0;
        boolean isTheFirst = true;

        for (int val : values) {
            if (isTheFirst) {
                isTheFirst = false;
                prev = val;
                continue;
            }
            if (val > prev) {
                increases++;
            }
            prev = val;
        }

        return increases;
    }

    public static int countIncreases(String fileContent, int windowSize) {
        Iterator<Integer> it = fileContent.lines().map(Integer::parseInt).iterator();

        int increasesCount = 0;
        int windowSum = 0;
        LinkedList<Integer> queue = new LinkedList<>();

        while (it.hasNext()) {
            int val = it.next();

        }

        return increasesCount;
    }

    public static void main(String[] args) {
        String fileContent = Utils.readFile("day01.txt");

        System.out.print("Day 01a: ");
        System.out.println(countIncreases(fileContent));

        System.out.println("Day 01b:");
    }
}
