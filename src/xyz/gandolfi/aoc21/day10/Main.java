package xyz.gandolfi.aoc21.day10;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day10.txt");
        assert inputLines != null;

        List<ChunksLine> chunksLines = inputLines.stream().map(ChunksLine::new).toList();

        System.out.print("Day 10a: ");
        int sumFirstIllegalCharsScore = chunksLines.stream()
            .map(ChunksLine::getPointsForFirstIllegalChar)
            .reduce(0, Integer::sum);
        System.out.println(sumFirstIllegalCharsScore);

        System.out.print("Day 10b: ");
        List<Long> incompleteSequencesScores = chunksLines.stream()
            .filter(ChunksLine::isIncomplete)
            .map(ChunksLine::getCompletionScore)
            .toList();
        incompleteSequencesScores = incompleteSequencesScores.stream().sorted().collect(Collectors.toList());
        long midScore = incompleteSequencesScores.get(incompleteSequencesScores.size() / 2);
        System.out.println(midScore);
    }
}
