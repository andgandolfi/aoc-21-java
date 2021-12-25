package xyz.gandolfi.aoc21.day24;

import java.util.Objects;

public class State implements Cloneable {
    private int w;
    private int x;
    private int y;
    private int z;

    public State() {}

    public State(int w, int x, int y, int z) {
        this();
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public int getW() {
        return w;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public int getVar(String param) {
        return switch (param) {
            case "w" -> getW();
            case "x" -> getX();
            case "y" -> getY();
            case "z" -> getZ();
            default -> throw new IllegalStateException("Unexpected value: " + param);
        };
    }

    public void setW(int w) {
        this.w = w;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public void setVar(String param, int value) {
        switch (param) {
            case "w" -> setW(value);
            case "x" -> setX(value);
            case "y" -> setY(value);
            case "z" -> setZ(value);
            default -> throw new IllegalStateException("Unexpected value: " + param);
        };
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        State state = (State) o;
        return x == state.x && y == state.y && z == state.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "State{" +
                "w=" + w +
                ", x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    @Override
    public State clone() {
        try {
            return (State) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
