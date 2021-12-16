package xyz.gandolfi.aoc21.day16;

public class GreaterThanOpPacket extends OperationPacket {
    protected GreaterThanOpPacket(int version) {
        super(version);
    }

    @Override
    public long getValue() {
        assert innerPackets.size() == 2;
        long v1 = innerPackets.get(0).getValue();
        long v2 = innerPackets.get(1).getValue();
        return v1 > v2 ? 1 : 0;
    }
}
