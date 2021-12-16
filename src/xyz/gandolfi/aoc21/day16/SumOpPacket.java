package xyz.gandolfi.aoc21.day16;

public class SumOpPacket extends OperationPacket {
    protected SumOpPacket(int version) {
        super(version);
    }

    @Override
    public long getValue() {
        long sum = 0;
        for (Packet p : innerPackets)
            sum += p.getValue();
        return sum;
    }
}
