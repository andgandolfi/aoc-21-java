package xyz.gandolfi.aoc21.day15;

import xyz.gandolfi.aoc21.day15.dijkstraShortestPath.Dijkstra;
import xyz.gandolfi.aoc21.day15.dijkstraShortestPath.Graph;
import xyz.gandolfi.aoc21.day15.dijkstraShortestPath.Node;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RiskLevelMap {
    private int[][] riskValues;
    private Node[][] nodes;
    private Graph graph;

    public RiskLevelMap(List<String> inputLines) {
        riskValues = new int[inputLines.size()][inputLines.get(0).length()];
        for (int y = 0; y < inputLines.size(); ++y)
            for (int x = 0; x < inputLines.get(y).length(); ++x) {
                riskValues[y][x] = Integer.parseInt(String.valueOf(inputLines.get(y).charAt(x)));
            }
    }

    public void expandMap(int times) {
        graph = null;
        nodes = null;

        Map<Integer, int[][]> alreadyComputed = new HashMap<>();
        alreadyComputed.put(0, riskValues);
        int[][] newRiskValues = new int[riskValues.length * times][riskValues[0].length * times];
        for (int y = 0; y < times; ++y) {
            for (int x = 0; x < times; ++x) {
                int[][] matrix = alreadyComputed.get(x + y);
                if (matrix == null)
                    matrix = computeNewMatrix(alreadyComputed, x + y);
                for (int yi = 0; yi < riskValues.length; ++yi)
                    for (int xi = 0; xi < riskValues[yi].length; ++xi)
                        newRiskValues[yi + y * riskValues.length][xi + x * riskValues[0].length] = matrix[yi][xi];
            }
        }
        riskValues = newRiskValues;
    }

    private int[][] computeNewMatrix(Map<Integer, int[][]> alreadyComputed, int add) {
        int[][] matrix = alreadyComputed.get(add);
        if (matrix != null) return matrix;
        matrix = alreadyComputed.get(add - 1);
        if (matrix == null) computeNewMatrix(alreadyComputed, add - 1);
        matrix = alreadyComputed.get(add - 1);
        int[][] newMatrix = new int[matrix.length][matrix[0].length];
        for (int y = 0; y < matrix.length; ++y)
            for (int x = 0; x < matrix[y].length; ++x)
                newMatrix[y][x] = matrix[y][x] == 9 ? 1 : (matrix[y][x] + 1);
        alreadyComputed.put(add, newMatrix);
        return newMatrix;
    }

    private void generateGraph() {
        if (graph != null) return;
        nodes = new Node[riskValues.length][riskValues[0].length];
        graph = new Graph();
        for (int y = 0; y < riskValues.length; ++y)
            for (int x = 0; x < riskValues[y].length; ++x) {
                Node node = new Node("" + x + "," + y);
                nodes[y][x] = node;
                graph.addNode(node);
            }
        for (int y = 0; y < riskValues.length; ++y)
            for (int x = 0; x < riskValues[y].length; ++x) {
                Node node = nodes[y][x];
                if (y - 1 >= 0)
                    node.addDestination(nodes[y - 1][x], riskValues[y - 1][x]);
                if (x - 1 >= 0)
                    node.addDestination(nodes[y][x - 1], riskValues[y][x - 1]);
                if (y + 1 < nodes.length)
                    node.addDestination(nodes[y + 1][x], riskValues[y + 1][x]);
                if (x + 1 < nodes[y].length)
                    node.addDestination(nodes[y][x + 1], riskValues[y][x + 1]);
            }
    }

    public int getLowestRiskPath() {
        generateGraph();
        Dijkstra.calculateShortestPathFromSource(graph, nodes[0][0]);
        return nodes[nodes.length - 1][nodes[0].length - 1].getDistance();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < riskValues.length; ++y) {
            for (int x = 0; x < riskValues[y].length; ++x)
                sb.append(riskValues[y][x]);
            sb.append("\n");
        }
        return sb.toString();
    }
}
