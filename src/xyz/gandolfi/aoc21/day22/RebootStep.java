package xyz.gandolfi.aoc21.day22;

public class RebootStep {
    private final boolean isOn;
    private Cuboid cuboid;

    public RebootStep(String inputLine) {
        isOn = inputLine.startsWith("on ");
        cuboid = new Cuboid(inputLine.substring(inputLine.indexOf(" ") + 1));
    }

    public boolean isOn() {
        return isOn;
    }

    public boolean isOff() {
        return !isOn;
    }

    public Cuboid getCuboid() {
        return cuboid;
    }
}
