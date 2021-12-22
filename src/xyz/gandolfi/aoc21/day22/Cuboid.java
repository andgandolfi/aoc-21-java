package xyz.gandolfi.aoc21.day22;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cuboid {
    private int startX;
    private int startY;
    private int startZ;
    private int endX;
    private int endY;
    private int endZ;

    private final static Pattern pattern = Pattern.compile("^x=(-?\\d+)\\.\\.(-?\\d+),y=(-?\\d+)\\.\\.(-?\\d+),z=(-?\\d+)\\.\\.(-?\\d+)$");

    private Cuboid() {}

    public Cuboid(String inputLine) {
        this();
        Matcher matcher = pattern.matcher(inputLine);
        boolean matches = matcher.matches();
        assert matches;
        startX = Integer.parseInt(matcher.group(1));
        endX = Integer.parseInt(matcher.group(2));
        startY = Integer.parseInt(matcher.group(3));
        endY = Integer.parseInt(matcher.group(4));
        startZ = Integer.parseInt(matcher.group(5));
        endZ = Integer.parseInt(matcher.group(6));
    }

    public Cuboid(Point lowerEnd, Point higherEnd) {
        this();
        startX = lowerEnd.getX();
        startY = lowerEnd.getY();
        startZ = lowerEnd.getZ();
        endX = higherEnd.getX();
        endY = higherEnd.getY();
        endZ = higherEnd.getZ();
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getStartZ() {
        return startZ;
    }

    public int getEndX() {
        return endX;
    }

    public int getEndY() {
        return endY;
    }

    public int getEndZ() {
        return endZ;
    }

    public long getCuboidSize() {
        return (long) (endX - startX + 1) * (endY - startY + 1) * (endZ - startZ + 1);
    }

    public Cuboid getOverlapWith(Cuboid other) {
        if (this.startX > other.endX || other.startX > this.endX ||
             this.startY > other.endY || other.startY > this.endY ||
             this.startZ > other.endZ || other.startZ > this.endZ)
            return null;
        Cuboid newCuboid = new Cuboid();
        newCuboid.startX = Math.max(this.startX, other.startX);
        newCuboid.startY = Math.max(this.startY, other.startY);
        newCuboid.startZ = Math.max(this.startZ, other.startZ);
        newCuboid.endX = Math.min(this.endX, other.endX);
        newCuboid.endY = Math.min(this.endY, other.endY);
        newCuboid.endZ = Math.min(this.endZ, other.endZ);
        return newCuboid;
    }

    public boolean isContainedIn(Cuboid container) {
        Cuboid overlap = this.getOverlapWith(container);
        if (overlap == null)
            return false;
        return this.equals(overlap);
    }

    public List<Cuboid> explode(Cuboid reference) {
        List<Cuboid> exploded = new ArrayList<>();
        if (this.getStartX() < reference.getStartX()) {
            if (this.getStartY() < reference.getStartY()) {
                if (this.getStartZ() < reference.getStartZ()) {
                    exploded.add(new Cuboid(new Point(this.getStartX(), this.getStartY(), this.getStartZ()),
                                            new Point(reference.getStartX() - 1, reference.getStartY() -1, reference.getStartZ() - 1)));
                }
                if (this.getEndZ() > reference.getEndZ()) {
                    exploded.add(new Cuboid(new Point(this.getStartX(), this.getStartY(), reference.getEndZ() + 1),
                                            new Point(reference.getStartX() - 1, reference.getStartY() - 1, this.getEndZ())));
                }
                exploded.add(new Cuboid(new Point(this.getStartX(), this.getStartY(), Math.max(this.getStartZ(), reference.getStartZ())),
                                        new Point(reference.getStartX() - 1, reference.getStartY() - 1, Math.min(this.getEndZ(), reference.getEndZ()))));
            }
            if (this.getEndY() > reference.getEndY()) {
                if (this.getStartZ() < reference.getStartZ()) {
                    exploded.add(new Cuboid(new Point(this.getStartX(), reference.getEndY() + 1, this.getStartZ()),
                            new Point(reference.getStartX() - 1, this.getEndY(), reference.getStartZ() - 1)));
                }
                if (this.getEndZ() > reference.getEndZ()) {
                    exploded.add(new Cuboid(new Point(this.getStartX(), reference.getEndY() + 1, reference.getEndZ() + 1),
                            new Point(reference.getStartX() - 1, this.getEndY(), this.getEndZ())));
                }
                exploded.add(new Cuboid(new Point(this.getStartX(), reference.getEndY() + 1, Math.max(this.getStartZ(), reference.getStartZ())),
                        new Point(reference.getStartX() - 1, this.getEndY(), Math.min(this.getEndZ(), reference.getEndZ()))));
            }
            if (this.getStartZ() < reference.getStartZ()) {
                exploded.add(new Cuboid(new Point(this.getStartX(), Math.max(this.getStartY(), reference.getStartY()), this.getStartZ()),
                        new Point(reference.getStartX() - 1, Math.min(this.getEndY(), reference.getEndY()), reference.getStartZ() - 1)));
            }
            if (this.getEndZ() > reference.getEndZ()) {
                exploded.add(new Cuboid(new Point(this.getStartX(), Math.max(this.getStartY(), reference.getStartY()), reference.getEndZ() + 1),
                        new Point(reference.getStartX() - 1, Math.min(this.getEndY(), reference.getEndY()), this.getEndZ())));
            }
            exploded.add(new Cuboid(new Point(this.getStartX(), Math.max(this.getStartY(), reference.getStartY()), Math.max(this.getStartZ(), reference.getStartZ())),
                    new Point(reference.getStartX() - 1, Math.min(this.getEndY(), reference.getEndY()), Math.min(this.getEndZ(), reference.getEndZ()))));
        }
        if (this.getEndX() > reference.getEndX()) {
            if (this.getStartY() < reference.getStartY()) {
                if (this.getStartZ() < reference.getStartZ()) {
                    exploded.add(new Cuboid(new Point(reference.getEndX() + 1, this.getStartY(), this.getStartZ()),
                            new Point(this.getEndX(), reference.getStartY() -1, reference.getStartZ() - 1)));
                }
                if (this.getEndZ() > reference.getEndZ()) {
                    exploded.add(new Cuboid(new Point(reference.getEndX() + 1, this.getStartY(), reference.getEndZ() + 1),
                            new Point(this.getEndX(), reference.getStartY() - 1, this.getEndZ())));
                }
                exploded.add(new Cuboid(new Point(reference.getEndX() + 1, this.getStartY(), Math.max(this.getStartZ(), reference.getStartZ())),
                        new Point(this.getEndX(), reference.getStartY() - 1, Math.min(this.getEndZ(), reference.getEndZ()))));
            }
            if (this.getEndY() > reference.getEndY()) {
                if (this.getStartZ() < reference.getStartZ()) {
                    exploded.add(new Cuboid(new Point(reference.getEndX() + 1, reference.getEndY() + 1, this.getStartZ()),
                            new Point(this.getEndX(), this.getEndY(), reference.getStartZ() - 1)));
                }
                if (this.getEndZ() > reference.getEndZ()) {
                    exploded.add(new Cuboid(new Point(reference.getEndX() + 1, reference.getEndY() + 1, reference.getEndZ() + 1),
                            new Point(this.getEndX(), this.getEndY(), this.getEndZ())));
                }
                exploded.add(new Cuboid(new Point(reference.getEndX() + 1, reference.getEndY() + 1, Math.max(this.getStartZ(), reference.getStartZ())),
                        new Point(this.getEndX(), this.getEndY(), Math.min(this.getEndZ(), reference.getEndZ()))));
            }
            if (this.getStartZ() < reference.getStartZ()) {
                exploded.add(new Cuboid(new Point(reference.getEndX() + 1, Math.max(this.getStartY(), reference.getStartY()), this.getStartZ()),
                        new Point(this.getEndX(), Math.min(this.getEndY(), reference.getEndY()), reference.getStartZ() - 1)));
            }
            if (this.getEndZ() > reference.getEndZ()) {
                exploded.add(new Cuboid(new Point(reference.getEndX() + 1, Math.max(this.getStartY(), reference.getStartY()), reference.getEndZ() + 1),
                        new Point(this.getEndX(), Math.min(this.getEndY(), reference.getEndY()), this.getEndZ())));
            }
            exploded.add(new Cuboid(new Point(reference.getEndX() + 1, Math.max(this.getStartY(), reference.getStartY()), Math.max(this.getStartZ(), reference.getStartZ())),
                    new Point(this.getEndX(), Math.min(this.getEndY(), reference.getEndY()), Math.min(this.getEndZ(), reference.getEndZ()))));
        }
        if (this.getStartY() < reference.getStartY()) {
            if (this.getStartZ() < reference.getStartZ()) {
                exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), this.getStartY(), this.getStartZ()),
                        new Point(Math.min(this.getEndX(), reference.getEndX()), reference.getStartY() -1, reference.getStartZ() - 1)));
            }
            if (this.getEndZ() > reference.getEndZ()) {
                exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), this.getStartY(), reference.getEndZ() + 1),
                        new Point(Math.min(this.getEndX(), reference.getEndX()), reference.getStartY() - 1, this.getEndZ())));
            }
            exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), this.getStartY(), Math.max(this.getStartZ(), reference.getStartZ())),
                    new Point(Math.min(this.getEndX(), reference.getEndX()), reference.getStartY() - 1, Math.min(this.getEndZ(), reference.getEndZ()))));
        }
        if (this.getEndY() > reference.getEndY()) {
            if (this.getStartZ() < reference.getStartZ()) {
                exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), reference.getEndY() + 1, this.getStartZ()),
                        new Point(Math.min(this.getEndX(), reference.getEndX()), this.getEndY(), reference.getStartZ() - 1)));
            }
            if (this.getEndZ() > reference.getEndZ()) {
                exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), reference.getEndY() + 1, reference.getEndZ() + 1),
                        new Point(Math.min(this.getEndX(), reference.getEndX()), this.getEndY(), this.getEndZ())));
            }
            exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), reference.getEndY() + 1, Math.max(this.getStartZ(), reference.getStartZ())),
                    new Point(Math.min(this.getEndX(), reference.getEndX()), this.getEndY(), Math.min(this.getEndZ(), reference.getEndZ()))));
        }
        if (this.getStartZ() < reference.getStartZ()) {
            exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), Math.max(this.getStartY(), reference.getStartY()), this.getStartZ()),
                    new Point(Math.min(this.getEndX(), reference.getEndX()), Math.min(this.getEndY(), reference.getEndY()), reference.getStartZ() - 1)));
        }
        if (this.getEndZ() > reference.getEndZ()) {
            exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), Math.max(this.getStartY(), reference.getStartY()), reference.getEndZ() + 1),
                    new Point(Math.min(this.getEndX(), reference.getEndX()), Math.min(this.getEndY(), reference.getEndY()), this.getEndZ())));
        }
        exploded.add(new Cuboid(new Point(Math.max(this.getStartX(), reference.getStartX()), Math.max(this.getStartY(), reference.getStartY()), Math.max(this.getStartZ(), reference.getStartZ())),
                new Point(Math.min(this.getEndX(), reference.getEndX()), Math.min(this.getEndY(), reference.getEndY()), Math.min(this.getEndZ(), reference.getEndZ()))));
        return exploded;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cuboid cuboid = (Cuboid) o;
        return startX == cuboid.startX && startY == cuboid.startY && startZ == cuboid.startZ && endX == cuboid.endX && endY == cuboid.endY && endZ == cuboid.endZ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(startX, startY, startZ, endX, endY, endZ);
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "x=" + startX +
                "..." + endX +
                ",y=" + startY +
                "..." + endY +
                ",z=" + startZ +
                "..." + endZ +
                '}';
    }
}
