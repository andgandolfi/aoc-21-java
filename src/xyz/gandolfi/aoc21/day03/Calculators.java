package xyz.gandolfi.aoc21.day03;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Calculators {
    public static int calculateGammaRate(List<String> inputLines) {
        if (inputLines == null || inputLines.isEmpty()) return 0;

        int[] counts = new int[inputLines.get(0).length()];

        for (String val: inputLines) {
            for (int i = 0; i < val.length(); ++i) {
                counts[i] += (val.charAt(i) == '1' ? 1 : -1);
            }
        }
        StringBuilder gamma = new StringBuilder();

        for (int count : counts) {
            gamma.append(count > 0 ? "1" : "0");
        }

        return Integer.parseInt(gamma.toString(), 2);
    }

    public static int calculateEpsilonRate(List<String> inputLines) {
        if (inputLines == null || inputLines.isEmpty()) return 0;

        int[] counts = new int[inputLines.get(0).length()];

        for (String val: inputLines) {
            for (int i = 0; i < val.length(); ++i) {
                counts[i] += (val.charAt(i) == '1' ? 1 : -1);
            }
        }

        StringBuilder epsilon = new StringBuilder();
        for (int count : counts) {
            epsilon.append(count > 0 ? "0" : "1");
        }

        return Integer.parseInt(epsilon.toString(), 2);
    }

    public static int calculateOxygenRating(List<String> inputLines) {
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
            if (count >= 0) {
                if (ones.size() == 0) break;
                nums = ones;
            } else {
                if (zeroes.size() == 0) break;
                nums = zeroes;
            }
        }

        return Integer.parseInt(nums.get(0), 2);
    }

    public static int calculateCo2Rating(List<String> inputLines) {
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
            if (count >= 0) {
                if (zeroes.size() == 0) break;
                nums = zeroes;
            } else {
                if (ones.size() == 0) break;
                nums = ones;
            }
        }

        return Integer.parseInt(nums.get(0), 2);
    }
}
