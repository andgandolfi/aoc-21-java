package xyz.gandolfi.aoc21.day10;

import java.util.List;
import java.util.Stack;

public class ChunksLine {
    private final String line;
    private boolean incomplete;
    private boolean corrupted;

    private int firstIllegalCharPosition;
    Stack<Character> pairingParens;
    private long completionScore;

    public ChunksLine(String inputLine) {
        line = inputLine;
        pairingParens = new Stack<>();
        check();
    }

    private void check() {
        for (var i = 0; i < line.length(); ++i) {
            char ch = line.charAt(i);
            if (ch == '(' || ch == '[' || ch == '{' || ch == '<') {
                pairingParens.push(ch);
            } else {
                Character openParen = getMatchingChar(ch);
                if (pairingParens.isEmpty() || openParen == null || openParen != pairingParens.pop()) {
                    firstIllegalCharPosition = i;
                    corrupted = true;
                    return;
                }
            }
        }
        if (!pairingParens.isEmpty())
            incomplete = true;
    }

    private Character getMatchingChar(char closingChar) {
        if (closingChar == ')') return '(';
        if (closingChar == ']') return '[';
        if (closingChar == '}') return '{';
        if (closingChar == '>') return '<';
        return null;
    }

    public boolean isIncomplete() {
        return incomplete;
    }

    public Character getFirstIllegalChar() {
        if (!corrupted) return null;
        return line.charAt(firstIllegalCharPosition);
    }

    public int getPointsForFirstIllegalChar() {
        Character firstIllegalChar = getFirstIllegalChar();
        if (firstIllegalChar == null) return 0;
        if (firstIllegalChar == ')') return 3;
        if (firstIllegalChar == ']') return 57;
        if (firstIllegalChar == '}') return 1197;
        if (firstIllegalChar == '>') return 25137;
        return 0;
    }

    public long getCompletionScore() {
        if (!isIncomplete()) return 0;
        if (completionScore > 0) return completionScore;

        // Cloning the stack inside a list to make it work in concurrent environments
        List<Character> missingParens = pairingParens.stream().toList();
        long sum = 0;
        for (int i = missingParens.size() - 1; i >= 0; --i) {
            Character p = missingParens.get(i);
            int points = switch (p) {
                case '(' -> 1;
                case '[' -> 2;
                case '{' -> 3;
                case '<' -> 4;
                default -> 0;
            };
            sum = sum * 5 + points;
        }
        completionScore = sum;
        return sum;
    }
}
