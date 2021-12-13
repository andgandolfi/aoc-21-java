package xyz.gandolfi.aoc21.day13;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day13.txt");
        assert inputLines != null;

        List<Coordinates> dots = inputLines.stream()
            .takeWhile(s -> s.trim().length() > 0)
            .map(Coordinates::new)
            .toList();

        List<Fold> folds = inputLines.stream()
            .dropWhile(s -> s.trim().length() > 0)
            .skip(1)
            .map(Fold::new)
            .toList();

        System.out.print("Day 13a: ");
        TransparentPaper paperA = new TransparentPaper(dots);
        paperA.applyFold(folds.get(0));
        System.out.println(paperA.countVisibleDots());

        System.out.print("Day 13b: ");
        TransparentPaper paperB = new TransparentPaper(dots);
        paperB.applyFold(folds);
        System.out.println();
        System.out.println(paperB);
    }
}
