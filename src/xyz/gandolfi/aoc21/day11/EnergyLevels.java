package xyz.gandolfi.aoc21.day11;

import java.util.List;

public class EnergyLevels {
    private int[][] currLevels;
    private int steps = 0;
    private int flashesCount = 0;

    public EnergyLevels(List<String> inputLines) {
        int rows = inputLines.size();
        int columns = inputLines.get(0).length();
        currLevels = new int[rows][columns];
        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                currLevels[r][c] = Integer.parseInt(String.valueOf(inputLines.get(r).charAt(c)));
    }

    public EnergyLevels(int[][] matrix) {
        currLevels = matrix;
    }

    public int evolveOneStep() {
        int[][] currMatrix = currLevels;
        int rows = currMatrix.length;
        int columns = currMatrix[0].length;
        int[][] nextMatrix = new int[rows][columns];

        for (int r = 0; r < rows; ++r)
            for (int c = 0; c < columns; ++c)
                nextMatrix[r][c] = currMatrix[r][c] + 1;

        int stepFlashesCount = 0;
        boolean thereWereFlashes;
        do {
            thereWereFlashes = false;
            for (int r = 0; r < rows; ++r)
                for (int c = 0; c < columns; ++c)
                    if (nextMatrix[r][c] > 9) {
                        nextMatrix[r][c] = 0;
                        thereWereFlashes = true;
                        stepFlashesCount++;
                        for (int dr = Math.max(0, r - 1); dr <= Math.min(rows - 1, r + 1); ++dr)
                            for (int dc = Math.max(0, c - 1); dc <= Math.min(columns - 1, c + 1); ++dc)
                                if ((dr != r || dc != c) && nextMatrix[dr][dc] != 0)
                                    nextMatrix[dr][dc]++;
                    }
        } while (thereWereFlashes);
        currLevels = nextMatrix;
        flashesCount += stepFlashesCount;
        steps++;
        return stepFlashesCount;
    }

    public void evolveNSteps(int numSteps) {
        for (int i = 0; i < numSteps; ++i)
            evolveOneStep();
    }

    public void evolveUntilAllFlash() {
        int target = currLevels.length * currLevels[0].length;
        int stepFlashes;
        do {
            stepFlashes = evolveOneStep();
        } while (stepFlashes != target);
    }

    public int[][] getCurrLevels() {
        return currLevels;
    }

    public int getSteps() {
        return steps;
    }

    public int getFlashesCount() {
        return flashesCount;
    }
}
