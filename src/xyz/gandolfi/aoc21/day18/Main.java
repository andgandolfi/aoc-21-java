package xyz.gandolfi.aoc21.day18;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day18.txt");
        assert inputLines != null;

        List<Node> nodes = inputLines.stream()
            .map(Node::parseLiteralFormat)
            .toList();

        System.out.print("Day 18a: ");
        System.out.println(Node.sumAllNodes(nodes).getMagnitude());

        System.out.print("Day 18b: ");
        long max = 0;
        for (int i = 0; i < inputLines.size(); ++i)
            for (int j = 0; j < inputLines.size(); ++j)
                if (i != j) {
                    max = Math.max(max,
                        Node.sumNodes(
                                Node.parseLiteralFormat(inputLines.get(i)),
                                Node.parseLiteralFormat(inputLines.get(j))
                        ).getMagnitude());
                }
        System.out.println(max);
    }
}
