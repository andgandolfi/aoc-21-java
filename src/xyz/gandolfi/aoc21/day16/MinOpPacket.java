package xyz.gandolfi.aoc21.day16;

public class MinOpPacket extends OperationPacket {
    protected MinOpPacket(int version) {
        super(version);
    }

    @Override
    public long getValue() {
        long min = Long.MAX_VALUE;
        for (Packet p : innerPackets) {
            long result = p.getValue();
            if (result < min)
                min = result;
        }
        return min;
    }
}
