package xyz.gandolfi.aoc21.day23;

import java.util.*;

public class AmphipodsState {
    private final char[][] diagram;
    private final AmphipodState state;
    private final Cursor cursor;
    private int energy;

    public AmphipodsState(List<List<Character>> initConfig) {
        energy = 0;
        state = AmphipodState.STABLE;
        cursor = new Cursor(0, 0);
        diagram = new char[initConfig.size() + 1][11];
        for (int i = 0; i < initConfig.size(); ++i)
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
    }

    public static int run(AmphipodsState initState) {
        int bestTotalEnergy = Integer.MAX_VALUE;
        PriorityQueue<AmphipodsState> queue = new PriorityQueue<>(Comparator.comparingInt(a -> a.energy));
        Set<AmphipodsState> visited = new HashSet<>(100000);
        queue.add(initState);

        while (!queue.isEmpty()) {
            AmphipodsState state = queue.remove();
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
            queue.addAll(state.evolve());
        }

        return bestTotalEnergy;
    }

    public List<AmphipodsState> evolve() {
        List<AmphipodsState> nextStates = new ArrayList<>();
        switch (this.state) {
            case STABLE -> {
                // MOVE_UP all 8 Amphipods up
                for (int i = 0; i < diagram.length - 1; ++i)
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
        if (!cursor.isInAllowedSpace(diagram.length - 1) || charAt(cursor) == 0)
            return nextStates;
        if (cursor.getY() == 0) {
            Cursor left = cursor.getCursorInDir(-1, 0);
            if (left.isInAllowedSpace(diagram.length - 1) && charAt(left) == 0)
                nextStates.add(new AmphipodsState(this, cursor, left, AmphipodState.MOVE_LEFT));
            Cursor right = cursor.getCursorInDir(1, 0);
            if (right.isInAllowedSpace(diagram.length - 1) && charAt(right) == 0)
                nextStates.add(new AmphipodsState(this, cursor, right, AmphipodState.MOVE_RIGHT));
            return nextStates;
        } else {
            for (int y = 0; y < cursor.getY(); ++y)
                if (diagram[y][cursor.getX()] != 0) // if something blocks it can't move up
                    return nextStates;
            boolean allRightCharBelow = true;
            for (int y = cursor.getY(); y < diagram.length; ++y)
                if (diagram[y][cursor.getX()] != cursor.expectedChar(diagram.length - 1)) {
                    allRightCharBelow = false;
                    break;
                }
            if (allRightCharBelow)
                return nextStates;
        }
        nextStates.add(new AmphipodsState(this, cursor, cursor.getCursorInDir(0, -1), AmphipodState.MOVE_UP));
        return nextStates;
    }

    private List<AmphipodsState> moveLeft(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace(diagram.length - 1) || charAt(cursor) == 0)
            return nextStates;
        Cursor left = cursor.getCursorInDir(-1, 0);
        if (left.isInAllowedSpace(diagram.length - 1) && charAt(left) == 0)
            nextStates.add(new AmphipodsState(this, cursor, left, AmphipodState.MOVE_LEFT));
        if (!cursor.isBlockingSpaceInFrontOfRoom())
            nextStates.add(new AmphipodsState(this, cursor, cursor, AmphipodState.STABLE));
        return nextStates;
    }

    private List<AmphipodsState> moveRight(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace(diagram.length - 1) || charAt(cursor) == 0)
            return nextStates;
        Cursor right = cursor.getCursorInDir(1, 0);
        if (right.isInAllowedSpace(diagram.length - 1) && charAt(right) == 0)
            nextStates.add(new AmphipodsState(this, cursor, right, AmphipodState.MOVE_RIGHT));
        if (!cursor.isBlockingSpaceInFrontOfRoom())
            nextStates.add(new AmphipodsState(this, cursor, cursor, AmphipodState.STABLE));
        return nextStates;
    }

    private List<AmphipodsState> moveBack(Cursor cursor) {
        List<AmphipodsState> nextStates = new ArrayList<>();
        if (!cursor.isInAllowedSpace(diagram.length - 1) || charAt(cursor) == 0)
            return nextStates;

        int targetX = getTargetX(cursor);

        boolean prevWasEmpty = false;
        for (int i = diagram.length - 1; i > cursor.getY() ; --i) {
            if (diagram[i][targetX] != 0) {
                if (prevWasEmpty)
                    return nextStates;
                if (diagram[i][targetX] != charAt(cursor))
                    return nextStates;
            } else
                prevWasEmpty = true;
        }

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
        if (next.isInAllowedSpace(diagram.length - 1) && charAt(next) == 0) {
            nextStates.add(new AmphipodsState(this, cursor, next, AmphipodState.MOVE_BACK));
        } else if (!next.isInAllowedSpace(diagram.length - 1) || charAt(cursor) == charAt(next)) {
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
        char[][] copy = new char[diagram.length][diagram[0].length];
        System.arraycopy(diagram[0], 0, copy[0], 0, diagram[0].length);
        for (int i = 0; i < diagram.length - 1; ++i)
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
            for (int j = 0; j < diagram.length - 1; ++j)
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
        sb.append("#\n");
        sb.append("###");
        for (int i = 0; i < 4; ++i)
            sb.append(diagram[1][(i + 1) * 2] == 0 ? '.' : diagram[1][(i + 1) * 2]).append("#");
        sb.append("##\n");
        for (int j = 2; j < diagram.length; ++j) {
            sb.append("  #");
            for (int i = 0; i < 4; ++i)
                sb.append(diagram[j][(i + 1) * 2] == 0 ? '.' : diagram[j][(i + 1) * 2]).append("#");
            sb.append("  \n");
        }
        sb.append("  #########  ");
        sb.append(state);
        sb.append("  ");
        sb.append(energy);
        return sb.toString();
    }
}
