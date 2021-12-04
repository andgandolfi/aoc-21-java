package xyz.gandolfi.aoc21.day04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class BingoGame {
    private final List<Integer> extractedNumbers;
    private final List<BingoBoard> boards;

    private boolean alreadyPlayed = false;
    private Integer winningBoard = null;
    private Integer lastExtracted = null;

    public BingoGame(List<String> inputLines) {
        extractedNumbers = Arrays.stream(inputLines.get(0).split(","))
                .map(Integer::parseInt)
                .toList();

        boards = new ArrayList<>();
        int i = 2;
        while (i < inputLines.size()) {
            List<List<Integer>> boardMatrix = new LinkedList<>();
            for (int j = 0; j < 5; j++) {
                List<Integer> boardLine = Arrays
                        .stream(inputLines.get(i + j).strip().split("\\s+"))
                        .map(Integer::parseInt)
                        .toList();
                boardMatrix.add(boardLine);
            }
            boards.add(new BingoBoard(boardMatrix));
            i += 6;
        }
    }

    public void playGameUntilFirstWin() {
        if (alreadyPlayed) return;
        alreadyPlayed = true;

        for (int num : extractedNumbers)
            for (int i = 0; i < boards.size(); i++) {
                BingoBoard board = boards.get(i);
                if (board.drawNumber(num)) {
                    winningBoard = i;
                    lastExtracted = num;
                    return;
                }
            }
    }

    public void playGameUntilAllWin() {
        if (alreadyPlayed) return;
        alreadyPlayed = true;

        for (int num : extractedNumbers) {
            boolean allWon = true;
            Integer lastBoardToWin = null;
            Integer lastNumExtracted = null;
            for (int i = 0; i < boards.size(); i++) {
                BingoBoard board = boards.get(i);
                if (board.hasWon()) continue;
                if (board.drawNumber(num)) {
                    lastBoardToWin = i;
                    lastNumExtracted = num;
                } else {
                    allWon = false;
                }
            }

            if (allWon) {
                winningBoard = lastBoardToWin;
                lastExtracted = lastNumExtracted;
                return;
            }
        }
    }

    public Integer getLastExtracted() {
        if (!alreadyPlayed) return null;
        return lastExtracted;
    }

    public BingoBoard getWinningBoard() {
        if (!alreadyPlayed) return null;
        return boards.get(winningBoard);
    }
}
