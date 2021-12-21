package xyz.gandolfi.aoc21.day21;

public class Dice {
    private int numOfFaces;
    private int next;
    private int rollCount;

    public Dice(int numOfFaces) {
        this.numOfFaces = numOfFaces;
        this.next = 1;
        this.rollCount = 0;
    }

    public int roll() {
        rollCount++;
        int current = next;
        next += 1;
        if (next == numOfFaces + 1)
            next = 1;
        return current;
    }

    public int getRollCount() {
        return rollCount;
    }
}
