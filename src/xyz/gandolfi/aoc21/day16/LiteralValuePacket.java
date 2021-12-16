package xyz.gandolfi.aoc21.day16;

public class LiteralValuePacket extends Packet {
    private long value;

    protected LiteralValuePacket(int version) {
        super(version);
    }

    @Override
    public void parseBits(TransmissionsBits bits) {
        StringBuilder valueBits = new StringBuilder();
        String group;
        do {
            group = bits.getNBits(5);
            valueBits.append(group.substring(1));
        } while (group.charAt(0) != '0');
        value = Long.parseLong(valueBits.toString(), 2);
    }

    @Override
    public long getValue() {
        return value;
    }

    @Override
    public int getVersionsSum() {
        return version;
    }
}
