package xyz.gandolfi.aoc21.day24;

import xyz.gandolfi.aoc21.Utils;

import java.util.*;

public class Main {
    private static Set<Runner> executeRunners(Set<Runner> runners) {
        Map<Runner, Runner> uniqueRunners = new HashMap<>();
        for (Runner r : runners)
            for (int i = 9; i > 0; --i) {
                Runner executedRunner = r.clone().execute(i);
                executedRunner.addToMinPathTillHere(i);
                executedRunner.addToMaxPathTillHere(i);
                if (uniqueRunners.containsKey(executedRunner)) {
                    Runner cachedRunner = uniqueRunners.get(executedRunner);
                    if (executedRunner.getMaxPathTillHere().compareTo(cachedRunner.getMaxPathTillHere()) > 0)
                        cachedRunner.setMaxPathTillHere(executedRunner.getMaxPathTillHere());
                    if (executedRunner.getMinPathTillHere().compareTo(cachedRunner.getMinPathTillHere()) < 0)
                        cachedRunner.setMinPathTillHere(executedRunner.getMinPathTillHere());
                }
                else
                    uniqueRunners.put(executedRunner, executedRunner);
            }
        return new HashSet<>(uniqueRunners.values());
    }

    private static Set<Runner> executeRunnerTillEnd(Runner initRunner) {
        Set<Runner> runners = new HashSet<>(List.of(initRunner));
        for (int i = 1; i <= 14; ++i) {
            runners = executeRunners(runners);
            System.out.println(i + " input position of 14 processed");
        }
        return runners;
    }

    private static String findMaxModelNumber(Set<Runner> runners) {
        String largestModelNumber = "";
        for (Runner r : runners)
            if (r.getState().getZ() == 0 &&
                    r.getMaxPathTillHere().compareTo(largestModelNumber) > 0)
                largestModelNumber = r.getMaxPathTillHere();
        return largestModelNumber;
    }

    private static String findMinModelNumber(Set<Runner> runners) {
        String smallestModelNumber = "99999999999999";
        for (Runner r : runners)
            if (r.getState().getZ() == 0 &&
                    r.getMinPathTillHere().compareTo(smallestModelNumber) < 0)
                smallestModelNumber = r.getMinPathTillHere();
        return smallestModelNumber;
    }

    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day24.txt");
        assert inputLines != null;

        List<Instruction> instructions = inputLines.stream()
            .map(Instruction::parse)
            .toList();

        System.out.println("This is gonna take a few minutes, please be patient...");
        Set<Runner> finalRunners = executeRunnerTillEnd(new Runner(instructions));

        System.out.print("Day 24a: ");
        System.out.println(findMaxModelNumber(finalRunners));

        System.out.print("Day 24b: ");
        System.out.println(findMinModelNumber(finalRunners));
    }
}
