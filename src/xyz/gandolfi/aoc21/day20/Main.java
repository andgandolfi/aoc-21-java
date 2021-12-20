package xyz.gandolfi.aoc21.day20;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day20.txt");
        assert inputLines != null;

        String imgEnhancementAlgo = inputLines.get(0);
        List<String> inputImageLines = inputLines.stream().skip(2).toList();
        Image image = new Image(imgEnhancementAlgo, inputImageLines);

        System.out.print("Day 20a: ");
        System.out.println(image.enhanceNTimes(2).getPixels().size());

        System.out.print("Day 20b: ");
        System.out.println(image.enhanceNTimes(50).getPixels().size());
    }
}
