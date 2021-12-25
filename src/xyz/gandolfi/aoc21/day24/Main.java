package xyz.gandolfi.aoc21.day24;

import xyz.gandolfi.aoc21.Utils;

import java.util.*;

public class Main {
    private static Set<Runner> executeRunners(Set<Runner> runners, boolean keepMax) {
        Map<Runner, Runner> uniqueRunners = new HashMap<>();
        for (Runner r : runners)
            for (int i = 9; i > 0; --i) {
                Runner executedRunner = r.clone().execute(i);
                executedRunner.addToPathTillHere(i);
                if (uniqueRunners.containsKey(executedRunner)) {
                    Runner cachedRunner = uniqueRunners.get(executedRunner);
                    if (keepMax && executedRunner.getPathTillHere().compareTo(cachedRunner.getPathTillHere()) > 0)
                        uniqueRunners.put(executedRunner, executedRunner);
                    if (!keepMax && executedRunner.getPathTillHere().compareTo(cachedRunner.getPathTillHere()) < 0)
                        uniqueRunners.put(executedRunner, executedRunner);
                }
                else
                    uniqueRunners.put(executedRunner, executedRunner);
            }
        return new HashSet<>(uniqueRunners.values());
    }

    private static String findMaxModelNumber(Runner initRunner) {
        Set<Runner> runners = new HashSet<>(List.of(initRunner));
        for (int i = 0; i < 14; ++i) {
            runners = executeRunners(runners, true);
        }
        String largestModelNumber = "";
        for (Runner r : runners)
            if (r.getState().getZ() == 0 &&
                    r.getPathTillHere().compareTo(largestModelNumber) > 0)
                largestModelNumber = r.getPathTillHere();
        return largestModelNumber;
    }

    private static String findMinModelNumber(Runner initRunner) {
        Set<Runner> runners = new HashSet<>(List.of(initRunner));
        for (int i = 0; i < 14; ++i) {
            runners = executeRunners(runners, false);
        }
        String smallestModelNumber = "99999999999999";
        for (Runner r : runners)
            if (r.getState().getZ() == 0 &&
                    r.getPathTillHere().compareTo(smallestModelNumber) < 0)
                smallestModelNumber = r.getPathTillHere();
        return smallestModelNumber;
    }

    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day24.txt");
        assert inputLines != null;

        List<Instruction> instructions = inputLines.stream()
            .map(Instruction::parse)
            .toList();

        Runner runner = new Runner(instructions);

        System.out.println("This is gonna take a few minutes, please be patient...\n");

        System.out.print("Day 24a: ");
        System.out.println(findMaxModelNumber(runner));

        System.out.print("Day 24b: ");
        System.out.println(findMinModelNumber(runner));
    }
}
