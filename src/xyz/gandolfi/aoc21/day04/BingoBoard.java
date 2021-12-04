package xyz.gandolfi.aoc21.day04;

import java.util.*;

public class BingoBoard {
    private final List<List<Integer>> matrix;
    private final Map<Integer, int[]> numToPositionMap = new HashMap<>(25);
    private final boolean[][] drawn = new boolean[5][5];
    private boolean hasWon = false;

    public BingoBoard(List<List<Integer>> boardMatrix) {
        this.matrix = boardMatrix;

        for (int h = 0; h < 5; h++)
            for (int v = 0; v < 5; v++)
                numToPositionMap.put(boardMatrix.get(h).get(v), new int[]{h, v});
    }

    public boolean drawNumber(int n) {
        int[] position = numToPositionMap.get(n);
        if (position == null) return false;

        drawn[position[0]][position[1]] = true;
        hasWon = isBingo(position);
        return hasWon;
    }

    private boolean isBingo(int[] lastDrawnPosition) {
        boolean bingo = true;
        for (int i = 0; i < 5; i++)
            if (!drawn[lastDrawnPosition[0]][i]) {
                bingo = false;
                break;
            }
        if (bingo) return true;

        bingo = true;
        for (int i = 0; i < 5; i++)
            if (!drawn[i][lastDrawnPosition[1]]) {
                bingo = false;
                break;
            }
        return bingo;
    }

    public int getSumOfUnmarkedNumbers() {
        int sum = 0;
        for (int h = 0; h < 5; h++)
            for (int v = 0; v < 5; v++)
                if (!drawn[h][v])
                    sum += matrix.get(h).get(v);
        return sum;
    }

    public boolean hasWon() {
        return hasWon;
    }

    @Override
    public String toString() {
        return "BingoBoard{" +
                "boardMatrix=" + matrix +
                '}';
    }
}
