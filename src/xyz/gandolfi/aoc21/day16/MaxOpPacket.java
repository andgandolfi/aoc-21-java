package xyz.gandolfi.aoc21.day16;

public class MaxOpPacket extends OperationPacket {
    protected MaxOpPacket(int version) {
        super(version);
    }

    @Override
    public long getValue() {
        long max = Long.MIN_VALUE;
        for (Packet p : innerPackets) {
            long result = p.getValue();
            if (result > max)
                max = result;
        }
        return max;
    }
}
