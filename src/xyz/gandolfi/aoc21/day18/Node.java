package xyz.gandolfi.aoc21.day18;

import java.util.LinkedList;
import java.util.List;

public class Node {
    private Node left;
    private Node right;

    private Node parent;
    private Integer value;

    private Node() { }

    private Node(int value) {
        this.value = value;
    }

    private Node(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public Node getParent() {
        return parent;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isValueNode() {
        return value != null;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static Node parseLiteralFormat(String inputString) {
        LinkedList<Character> chars = new LinkedList<>();
        for (char c : inputString.toCharArray())
            chars.addLast(c);
        return parseLiteralFormat(chars, null);
    }

    private static Node parseLiteralFormat(LinkedList<Character> chars, Node parent) {
        char c = chars.removeFirst();
        Node n = new Node();
        if (c == '[') {
            n.setLeft(parseLiteralFormat(chars, n));
            c = chars.removeFirst();
            assert c == ',';
            n.setRight(parseLiteralFormat(chars, n));
            c = chars.removeFirst();
            assert c == ']';
        } else {
            n.setValue(Integer.parseInt(String.valueOf(c)));
        }
        n.setParent(parent);
        return n;
    }

    public static Node sumAllNodes(List<Node> nodes) {
        Node currNode = null;
        for (Node node : nodes) {
            currNode = currNode == null ? node : sumOnlyNodes(currNode, node);
            explodeAndSplitUntilStable(currNode);
        }
        return currNode;
    }

    public static Node sumNodes(Node node1, Node node2) {
        return explodeAndSplitUntilStable(sumOnlyNodes(node1, node2));
    }

    private static Node sumOnlyNodes(Node node1, Node node2) {
        Node newParent =  new Node(node1, node2);
        node1.parent = newParent;
        node2.parent = newParent;
        return newParent;
    }

    private static Node explodeAndSplitUntilStable(Node node) {
        boolean anyOperationPerformed = true;
        while (anyOperationPerformed) {
            anyOperationPerformed = explode(node);
            if (!anyOperationPerformed)
                anyOperationPerformed = split(node);
        }
        return node;
    }

    private static boolean explode(Node node) {
        return explode(node, 0);
    }

    private static boolean explode(Node node, int nestingLevel) {
        if (node.isValueNode())
            return false;
        if (node.getLeft().isValueNode() && node.getRight().isValueNode()) {
            if (nestingLevel < 4)
                return false;
            Node prev = getPreviousNode(node);
            Node next = getNextNode(node);
            if (prev != null)
                prev.value += node.left.value;
            if (next != null)
                next.value += node.right.value;
            if (node.parent.left == node) {
                node.parent.left = new Node(0);
                node.parent.left.parent = node.parent;
                node.parent = null;
            } else {
                node.parent.right = new Node(0);
                node.parent.right.parent = node.parent;
                node.parent = null;
            }
            return true;
        } else {
            if(explode(node.left, nestingLevel + 1))
                return true;
            return explode(node.right, nestingLevel + 1);
        }
    }

    private static boolean split(Node node) {
        if (node.isValueNode()) {
            if (node.value < 10)
                return false;
            node.left = new Node(node.value / 2);
            node.right = new Node((int) Math.ceil(node.value / 2.0));
            node.left.parent = node;
            node.right.parent = node;
            node.value = null;
            return true;
        }
        if (split(node.left))
            return true;
        return split(node.right);
    }

    private static Node getPreviousNode(Node node) {
        if (node == null || node.parent == null)
            return null;
        if (node == node.parent.left)
            return getPreviousNode(node.parent);
        else // node == node.parent.right
            return getLastNode(node.parent.left);
    }

    private static Node getNextNode(Node node) {
        if (node == null || node.parent == null)
            return null;
        if (node == node.parent.right)
            return getNextNode(node.parent);
        else // node == node.parent.left
            return getFirstNode(node.parent.right);
    }

    private static Node getLastNode(Node node) {
        if (node.isValueNode())
            return node;
        return getLastNode(node.right);
    }

    private static Node getFirstNode(Node node) {
        if (node.isValueNode())
            return node;
        return getFirstNode(node.left);
    }

    public long getMagnitude() {
        if (isValueNode())
            return value;
        return 3 * left.getMagnitude() + 2 * right.getMagnitude();
    }

    @Override
    public String toString() {
        if (this.value != null)
            return String.valueOf(value);

        return "[" +
                left.toString() +
                "," +
                right.toString() +
                "]";
    }
}
