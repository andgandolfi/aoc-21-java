package xyz.gandolfi.aoc21.day24;

import java.util.List;
import java.util.Objects;

public class Runner implements Cloneable {
    private final List<Instruction> instructions;
    private State state;

    private String pathTillHere = "";

    private int cursor;

    public Runner(List<Instruction> instructions) {
        this.instructions = instructions;
        this.state = new State();
        this.cursor = 0;
    }

    public Runner execute(int input) {
        return execute("" + input);
    }

    public Runner execute(String input) {
        execute(new InputProvider(input));
        return this;
    }

    public Runner execute(InputProvider inputProvider) {
        while (cursor < instructions.size()) {
            if (instructions.get(cursor).execute(state, inputProvider))
                cursor++;
            else
                break;
        }
        return this;
    }

    public State getState() {
        return state;
    }

    public boolean hasFinished() {
        return cursor >= instructions.size();
    }

    public String getPathTillHere() {
        return pathTillHere;
    }

    public void addToPathTillHere(int pathPart) {
        this.pathTillHere += pathPart;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Runner runner = (Runner) o;
        return cursor == runner.cursor && Objects.equals(state, runner.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, cursor);
    }

    @Override
    public String toString() {
        return "Runner{" +
                "state=" + state +
                ", cursor=" + cursor +
                '}';
    }

    @Override
    public Runner clone() {
        try {
            Runner clone = (Runner) super.clone();
            clone.state = state.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
