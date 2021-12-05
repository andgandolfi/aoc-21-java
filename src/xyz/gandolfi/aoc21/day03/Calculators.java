package xyz.gandolfi.aoc21.day03;

import java.util.ArrayList;
import java.util.List;

public class Calculators {
    private static int[] countBits(List<String> inputLines) {
        if (inputLines == null || inputLines.isEmpty()) return new int[0];
        int[] counts = new int[inputLines.get(0).length()];

        for (String val: inputLines)
            for (int i = 0; i < val.length(); ++i)
                counts[i] += (val.charAt(i) == '1' ? 1 : -1);

        return counts;
    }

    public static int calculateGammaRate(List<String> inputLines) {
        int[] counts = countBits(inputLines);
        StringBuilder gamma = new StringBuilder();

        for (int count : counts)
            gamma.append(count > 0 ? "1" : "0");

        return Integer.parseInt(gamma.toString(), 2);
    }

    public static int calculateEpsilonRate(List<String> inputLines) {
        int[] counts = countBits(inputLines);
        StringBuilder epsilon = new StringBuilder();

        for (int count : counts)
            epsilon.append(count > 0 ? "0" : "1");

        return Integer.parseInt(epsilon.toString(), 2);
    }

    private static int countBitsBasedOnPrevious(List<String> inputLines, boolean keepOnes) {
        if (inputLines == null || inputLines.isEmpty()) return 0;

        List<String> nums = inputLines;
        int numsLength = nums.get(0).length();

        for (int i = 0; i < numsLength; ++i) {
            int count = 0;
            List<String> ones = new ArrayList<>();
            List<String> zeroes = new ArrayList<>();
            for (String num : nums) {
                if (num.charAt(i) == '1') {
                    count++;
                    ones.add(num);
                } else {
                    count--;
                    zeroes.add(num);
                }
            }
            List<String> listToKeep;
            if (count >= 0)
                listToKeep = keepOnes ? ones : zeroes;
            else
                listToKeep = keepOnes ? zeroes : ones;
            if (listToKeep.size() == 0) break;
            nums = listToKeep;
        }

        return Integer.parseInt(nums.get(0), 2);
    }

    public static int calculateOxygenRating(List<String> inputLines) {
        return countBitsBasedOnPrevious(inputLines, true);
    }

    public static int calculateCo2Rating(List<String> inputLines) {
        return countBitsBasedOnPrevious(inputLines, false);
    }
}
