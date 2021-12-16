package xyz.gandolfi.aoc21.day16;

public class ProdOpPacket extends OperationPacket {
    protected ProdOpPacket(int version) {
        super(version);
    }

    @Override
    public long getValue() {
        long prod = 1;
        for (Packet p : innerPackets)
            prod *= p.getValue();
        return prod;
    }
}
