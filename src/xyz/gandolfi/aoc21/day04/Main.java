package xyz.gandolfi.aoc21.day04;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day04.txt");
        assert inputLines != null;

        System.out.print("Day 04a: ");
        BingoGame gameA = new BingoGame(inputLines);
        gameA.playGameUntilFirstWin();
        System.out.println(gameA.getLastExtracted() * gameA.getWinningBoard().getSumOfUnmarkedNumbers());

        System.out.print("Day 04b: ");
        BingoGame gameB = new BingoGame(inputLines);
        gameB.playGameUntilAllWin();
        System.out.println(gameB.getLastExtracted() * gameB.getWinningBoard().getSumOfUnmarkedNumbers());
    }
}
