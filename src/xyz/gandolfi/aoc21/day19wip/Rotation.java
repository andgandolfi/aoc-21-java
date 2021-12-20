package xyz.gandolfi.aoc21.day19wip;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class Rotation {
    public static final List<Rotation> rotations = new ArrayList<>(24);

    static {
        rotations.add(new Rotation(
                p -> new Position(p.getX(), p.getY(), p.getZ()),
                p -> new Position(p.getX(), p.getY(), p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(p.getX(), p.getZ(), -p.getY()),
                p -> new Position(p.getX(), -p.getZ(), p.getY())));
        rotations.add(new Rotation(
                p -> new Position(p.getX(), -p.getY(), -p.getZ()),
                p -> new Position(p.getX(), -p.getY(), -p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(p.getX(), -p.getZ(), p.getY()),
                p -> new Position(p.getX(), p.getZ(), -p.getY())));

        rotations.add(new Rotation(
                p -> new Position(p.getY(), -p.getX(), p.getZ()),
                p -> new Position(-p.getY(), p.getX(), p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(p.getY(), p.getZ(), p.getX()),
                p -> new Position(p.getZ(), p.getX(), p.getY())));
        rotations.add(new Rotation(
                p -> new Position(p.getY(), p.getX(), -p.getZ()),
                p -> new Position(p.getY(), p.getX(), -p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(p.getY(), -p.getZ(), -p.getX()),
                p -> new Position(-p.getZ(), p.getX(), -p.getY())));

        rotations.add(new Rotation(
                p -> new Position(-p.getX(), -p.getY(), p.getZ()),
                p -> new Position(-p.getX(), -p.getY(), p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(-p.getX(), -p.getZ(), -p.getY()),
                p -> new Position(-p.getX(), -p.getZ(), -p.getY())));
        rotations.add(new Rotation(
                p -> new Position(-p.getX(), p.getY(), -p.getZ()),
                p -> new Position(-p.getX(), p.getY(), -p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(-p.getX(), p.getZ(), p.getY()),
                p -> new Position(-p.getX(), p.getZ(), p.getY())));

        rotations.add(new Rotation(
                p -> new Position(-p.getY(), p.getX(), p.getZ()),
                p -> new Position(p.getY(), -p.getX(), p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(-p.getY(), -p.getZ(), p.getX()),
                p -> new Position(p.getZ(), -p.getX(), -p.getY())));
        rotations.add(new Rotation(
                p -> new Position(-p.getY(), -p.getX(), -p.getZ()),
                p -> new Position(-p.getY(), -p.getX(), -p.getZ())));
        rotations.add(new Rotation(
                p -> new Position(-p.getY(), p.getZ(), -p.getX()),
                p -> new Position(-p.getZ(), -p.getX(), p.getY())));

        rotations.add(new Rotation(
                p -> new Position(p.getZ(), p.getY(), -p.getX()),
                p -> new Position(-p.getZ(), p.getY(), p.getX())));
        rotations.add(new Rotation(
                p -> new Position(p.getZ(), p.getX(), p.getY()),
                p -> new Position(p.getY(), p.getZ(), p.getX())));
        rotations.add(new Rotation(
                p -> new Position(p.getZ(), -p.getY(), p.getX()),
                p -> new Position(p.getZ(), -p.getY(), p.getX())));
        rotations.add(new Rotation(
                p -> new Position(p.getZ(), -p.getX(), -p.getY()),
                p -> new Position(-p.getY(), -p.getZ(), p.getX())));

        rotations.add(new Rotation(
                p -> new Position(-p.getZ(), -p.getY(), -p.getX()),
                p -> new Position(-p.getZ(), -p.getY(), -p.getX())));
        rotations.add(new Rotation(
                p -> new Position(-p.getZ(), -p.getX(), p.getY()),
                p -> new Position(-p.getY(), p.getZ(), -p.getX())));
        rotations.add(new Rotation(
                p -> new Position(-p.getZ(), p.getY(), p.getX()),
                p -> new Position(p.getZ(), p.getY(), -p.getX())));
        rotations.add(new Rotation(
                p -> new Position(-p.getZ(), p.getX(), -p.getY()),
                p -> new Position(p.getY(), -p.getZ(), -p.getX())));
    }

    private final Function<Position, Position> rotateFn;
    private final Function<Position, Position> rotateBackFn;

    private Rotation(Function<Position, Position> rotateFn, Function<Position, Position> rotateBackFn){
        this.rotateFn = rotateFn;
        this.rotateBackFn = rotateBackFn;
    }

    public Position rotate(Position pos) {
        return rotateFn.apply(pos);
    }

    public Position rotateBack(Position pos) {
        return rotateBackFn.apply(pos);
    }

    public Rotation getReverseRotation() {
        return new Rotation(rotateBackFn, rotateFn);
    }
}
