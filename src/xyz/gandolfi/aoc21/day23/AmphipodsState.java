package xyz.gandolfi.aoc21.day23;

import java.util.*;

public class AmphipodsState {
    private char[][] diagram;
    private AmphipodState state;
    private Cursor cursor;
    private int energy;

    // TODO: cache for DP?

    private AmphipodsState() {}

    public AmphipodsState(List<List<Character>> initConfig) {
        energy = 0;
        state = AmphipodState.STABLE;
        cursor = new Cursor(0, 0);
        diagram = new char[3][11];
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 4; ++j)
                diagram[i+1][(j+1)*2] = initConfig.get(i).get(j);
    }

    private AmphipodsState(AmphipodsState prevState, Cursor cursor, Cursor next, AmphipodState newState) {
        diagram = prevState.getDiagramCopy();
        state = newState;
        this.cursor = next;
        energy = prevState.energy;

        if (cursor.equals(next))
            return;

        diagram[next.getY()][next.getX()] = diagram[cursor.getY()][cursor.getX()];
        diagram[cursor.getY()][cursor.getX()] = 0;
        energy += switch (diagram[next.getY()][next.getX()]) {
            case 'A' -> 1;
            case 'B' -> 10;
            case 'C' -> 100;
            case 'D' -> 1000;
            default -> throw new IllegalStateException("Unexpected value: " + diagram[next.getY()][next.getX()]);
        };
//        System.out.println(this);
//        System.out.println("energy: " + energy);
//        System.out.println("state: " + state);
//        System.out.println();
    }

    public static int run(AmphipodsState initState) {
        int bestTotalEnergy = Integer.MAX_VALUE;
        PriorityQueue<AmphipodsState> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.energy));
        Set<AmphipodsState> visited = new HashSet<>(100000);
        queue.add(initState);

        while (!queue.isEmpty()) {
            AmphipodsState state = queue.remove();
//            if (AmphipodsState.isMyCase(state))
//                System.out.println(state);
            if (visited.contains(state))
                continue;
            if (state.isStable())
                visited.add(state);
            if (state.getEnergy() >= bestTotalEnergy)
                continue;
            if (state.isStable() && state.isOrganizedCorrectly()) {
                bestTotalEnergy = state.getEnergy();
                continue;
            }
            var nextStates = state.evolve();
            queue.addAll(nextStates);
        }

        return bestTotalEnergy;
    }

    private static boolean isMyCase(AmphipodsState state) {
        if (state.state != AmphipodState.MOVE_BACK) return false;
        if (!new Cursor(4, 2).equals(state.cursor)) return false;

        char[][] mystate = new char[][]{
                new char[]{ 0 , 0 , 0 , 0 , 0 ,'D', 0 , 0 , 0 , 0 , 0 },
                new char[]{ 0 , 0 ,'B', 0 , 0 , 0 ,'C', 0 ,'D', 0 , 0 },
                new char[]{ 0 , 0 ,'A', 0 ,'B', 0 ,'C', 0 ,'A', 0 , 0 }
        };
        for (int y = 0; y < 3; ++y)
            for (int x = 0; x < 11; ++x)
                if (mystate[y][x] != state.diagram[y][x])
                    return false;
        return true;
    }

    public List<AmphipodsState> evolve() {
        List<AmphipodsState> nextStates = new ArrayList<>();
        switch (this.state) {
            case STABLE -> {
                // MOVE_UP all 8 Amphipods up
                for (int i = 0; i < 2; ++i)
                    for (int j = 0; j < 4; ++j)
                        nextStates.addAll(moveUp(new Cursor((j + 1) * 2, i + 1)));

                // MOVE_BACK any amphipod in the hallway
                for (int i = 0; i < 11; ++i)
                    if (diagram[0][i] != 0)
                        nextStates.addAll(moveBack(new Cursor(i, 0)));
            }
            case MOVE_UP -> nextStates.addAll(moveUp(cursor));
            case MOVE_LEFT -> nextStates.addAll(moveLeft(cursor));
            case MOVE_RIGHT -> nextStates.addAll(moveRight(cursor));
            case MOVE_BACK -> nextStates.addAll(moveBack(cursor));
        }
        return nextStates;
    }

    private List<AmphipodsState> moveUp(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace() || charAt(cursor) == 0)
            return nextStates;
        if (cursor.getY() == 0) {
            Cursor left = cursor.getCursorInDir(-1, 0);
            if (left.isInAllowedSpace() && charAt(left) == 0)
                nextStates.add(new AmphipodsState(this, cursor, left, AmphipodState.MOVE_LEFT));
            Cursor right = cursor.getCursorInDir(1, 0);
            if (right.isInAllowedSpace() && charAt(right) == 0)
                nextStates.add(new AmphipodsState(this, cursor, right, AmphipodState.MOVE_RIGHT));
            return nextStates;
        }
        if (cursor.getY() == 2) {
            if (cursor.expectedChar() == diagram[2][cursor.getX()])
                return nextStates;
            if (diagram[1][cursor.getX()] != 0)
                return nextStates;
        }
        if (cursor.getY() == 1) {
            if (diagram[1][cursor.getX()] == cursor.expectedChar() &&
                    charAt(cursor.getCursorInDir(0, 1)) == cursor.expectedChar())
                return nextStates;
            if (diagram[0][cursor.getX()] != 0)
                return nextStates;
        }
        nextStates.add(new AmphipodsState(this, cursor, cursor.getCursorInDir(0, -1), AmphipodState.MOVE_UP));
        return nextStates;
    }

    private List<AmphipodsState> moveLeft(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace() || charAt(cursor) == 0)
            return nextStates;
        Cursor left = cursor.getCursorInDir(-1, 0);
        if (left.isInAllowedSpace() && charAt(left) == 0)
            nextStates.add(new AmphipodsState(this, cursor, left, AmphipodState.MOVE_LEFT));
        nextStates.add(new AmphipodsState(this, cursor, cursor, AmphipodState.STABLE));
        return nextStates;
    }

    private List<AmphipodsState> moveRight(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace() || charAt(cursor) == 0)
            return nextStates;
        Cursor right = cursor.getCursorInDir(1, 0);
        if (right.isInAllowedSpace() && charAt(right) == 0)
            nextStates.add(new AmphipodsState(this, cursor, right, AmphipodState.MOVE_RIGHT));
        nextStates.add(new AmphipodsState(this, cursor, cursor, AmphipodState.STABLE));
        return nextStates;
    }

    private List<AmphipodsState> moveBack(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace() || charAt(cursor) == 0)
            return nextStates;

        int targetX = getTargetX(cursor);

        if (diagram[2][targetX] != 0 && diagram[2][targetX] != charAt(cursor))
            return nextStates;
        if (diagram[1][targetX] != 0 && diagram[1][targetX] != charAt(cursor))
            return nextStates;

        Cursor next = null;

        if (cursor.getX() < targetX)
            next = cursor.getCursorInDir(1, 0); // moving right
        else if (cursor.getX() > targetX)
            next = cursor.getCursorInDir(-1, 0); // moving left

        if (next != null) {
            if (charAt(next) == 0)
                nextStates.add(new AmphipodsState(this, cursor, next, AmphipodState.MOVE_BACK));
            return nextStates;
        }

        next = cursor.getCursorInDir(0, 1); // moving down
        if (next.isInAllowedSpace() && charAt(next) == 0) {
            nextStates.add(new AmphipodsState(this, cursor, next, AmphipodState.MOVE_BACK));
        } else if (!next.isInAllowedSpace() || charAt(cursor) == charAt(next)) {
            nextStates.add(new AmphipodsState(this, cursor, cursor, AmphipodState.STABLE));
        }

        return nextStates;
    }

    private char charAt(Cursor cursor) {
        return diagram[cursor.getY()][cursor.getX()];
    }

    private int getTargetX(Cursor cursor) {
        char charAtCursor = charAt(cursor);
        return switch (charAtCursor) {
            case 'A' -> 2;
            case 'B' -> 4;
            case 'C' -> 6;
            case 'D' -> 8;
            default -> throw new IllegalStateException("Unexpected value: " + charAtCursor);
        };
    }

    private char[][] getDiagramCopy() {
        char[][] copy = new char[3][11];
        System.arraycopy(diagram[0], 0, copy[0], 0, 11);
        for (int i = 0; i < 2; ++i)
            for (int j = 0; j < 4; ++j)
                copy[i+1][(j+1)*2] = diagram[i+1][(j+1)*2];
        return copy;
    }

    public boolean isStable() {
        return state == AmphipodState.STABLE;
    }

    public int getEnergy() {
        return energy;
    }

    public boolean isOrganizedCorrectly() {
        for (int i = 0; i < 4; ++i)
            for (int j = 0; j < 2; ++j)
                if (diagram[j+1][(i+1)*2] != 'A' + i)
                    return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AmphipodsState state = (AmphipodsState) o;
        return Arrays.deepEquals(diagram, state.diagram);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(diagram);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("#############\n#");
        for (int i = 0; i < 11; ++i)
            sb.append(diagram[0][i] == 0 ? "." : diagram[0][i]);
        sb.append("#\n###");
        for (int i = 0; i < 4; ++i)
            sb.append(diagram[1][(i + 1) * 2] == 0 ? '.' : diagram[1][(i + 1) * 2]).append("#");
        sb.append("##\n  #");
        for (int i = 0; i < 4; ++i)
            sb.append(diagram[2][(i + 1) * 2] == 0 ? '.' : diagram[2][(i + 1) * 2]).append("#");
        sb.append("  \n  #########");
        return sb.toString();
    }
}
