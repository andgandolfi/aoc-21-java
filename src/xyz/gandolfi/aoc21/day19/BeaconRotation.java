package xyz.gandolfi.aoc21.day19;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class BeaconRotation {
    public static final List<BeaconRotation> rotations = new ArrayList<>(24);

    static {
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getX(), p.getY(), p.getZ()),
                p -> new BeaconPosition(p.getX(), p.getY(), p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getX(), p.getZ(), -p.getY()),
                p -> new BeaconPosition(p.getX(), -p.getZ(), p.getY())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getX(), -p.getY(), -p.getZ()),
                p -> new BeaconPosition(p.getX(), -p.getY(), -p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getX(), -p.getZ(), p.getY()),
                p -> new BeaconPosition(p.getX(), p.getZ(), -p.getY())));

        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getY(), -p.getX(), p.getZ()),
                p -> new BeaconPosition(-p.getY(), p.getX(), p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getY(), p.getZ(), p.getX()),
                p -> new BeaconPosition(p.getZ(), p.getX(), p.getY())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getY(), p.getX(), -p.getZ()),
                p -> new BeaconPosition(p.getY(), p.getX(), -p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getY(), -p.getZ(), -p.getX()),
                p -> new BeaconPosition(-p.getZ(), p.getX(), -p.getY())));

        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getX(), -p.getY(), p.getZ()),
                p -> new BeaconPosition(-p.getX(), -p.getY(), p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getX(), -p.getZ(), -p.getY()),
                p -> new BeaconPosition(-p.getX(), -p.getZ(), -p.getY())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getX(), p.getY(), -p.getZ()),
                p -> new BeaconPosition(-p.getX(), p.getY(), -p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getX(), p.getZ(), p.getY()),
                p -> new BeaconPosition(-p.getX(), p.getZ(), p.getY())));

        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getY(), p.getX(), p.getZ()),
                p -> new BeaconPosition(p.getY(), -p.getX(), p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getY(), -p.getZ(), p.getX()),
                p -> new BeaconPosition(p.getZ(), -p.getX(), -p.getY())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getY(), -p.getX(), -p.getZ()),
                p -> new BeaconPosition(-p.getY(), -p.getX(), -p.getZ())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getY(), p.getZ(), -p.getX()),
                p -> new BeaconPosition(-p.getZ(), -p.getX(), p.getY())));

        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getZ(), p.getY(), -p.getX()),
                p -> new BeaconPosition(-p.getZ(), p.getY(), p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getZ(), p.getX(), p.getY()),
                p -> new BeaconPosition(p.getY(), p.getZ(), p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getZ(), -p.getY(), p.getX()),
                p -> new BeaconPosition(p.getZ(), -p.getY(), p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(p.getZ(), -p.getX(), -p.getY()),
                p -> new BeaconPosition(-p.getY(), -p.getZ(), p.getX())));

        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getZ(), -p.getY(), -p.getX()),
                p -> new BeaconPosition(-p.getZ(), -p.getY(), -p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getZ(), -p.getX(), p.getY()),
                p -> new BeaconPosition(-p.getY(), p.getZ(), -p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getZ(), p.getY(), p.getX()),
                p -> new BeaconPosition(p.getZ(), p.getY(), -p.getX())));
        rotations.add(new BeaconRotation(
                p -> new BeaconPosition(-p.getZ(), p.getX(), -p.getY()),
                p -> new BeaconPosition(p.getY(), -p.getZ(), -p.getX())));
    }

    private final Function<BeaconPosition, BeaconPosition> rotateFn;
    private final Function<BeaconPosition, BeaconPosition> rotateBackFn;

    private BeaconRotation(Function<BeaconPosition, BeaconPosition> rotateFn,
                     Function<BeaconPosition, BeaconPosition> rotateBackFn){
        this.rotateFn = rotateFn;
        this.rotateBackFn = rotateBackFn;
    }

    public BeaconPosition rotate(BeaconPosition pos) {
        return rotateFn.apply(pos);
    }

    public BeaconPosition rotateBack(BeaconPosition pos) {
        return rotateBackFn.apply(pos);
    }

    public BeaconRotation getReverseRotation() {
        return new BeaconRotation(rotateBackFn, rotateFn);
    }
}
