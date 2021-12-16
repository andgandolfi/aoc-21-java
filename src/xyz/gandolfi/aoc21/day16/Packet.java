package xyz.gandolfi.aoc21.day16;

public abstract class Packet {
    protected final Integer version;

    protected Packet(int version) {
        this.version = version;
    }

    public static Packet parsePacket(TransmissionsBits bits) {
        String versionBits = bits.getNBits(3);
        String typeIdBits = bits.getNBits(3);

        if (versionBits == null || typeIdBits == null)
            return null;

        int version = Integer.parseInt(versionBits, 2);
        int typeId = Integer.parseInt(typeIdBits, 2);

        Packet p = switch (typeId) {
            case 0 -> new SumOpPacket(version);
            case 1 -> new ProdOpPacket(version);
            case 2 -> new MinOpPacket(version);
            case 3 -> new MaxOpPacket(version);
            case 4 -> new LiteralValuePacket(version);
            case 5 -> new GreaterThanOpPacket(version);
            case 6 -> new LessThanOpPackage(version);
            case 7 -> new EqualToOpPackage(version);
            default -> throw new IllegalStateException("Unexpected value: " + typeId);
        };
        p.parseBits(bits);

        return p;
    }

    public abstract void parseBits(TransmissionsBits bits);

    public abstract long getValue();

    public abstract int getVersionsSum();
}
