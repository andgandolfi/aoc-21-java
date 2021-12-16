package xyz.gandolfi.aoc21.day16;

import java.util.ArrayList;
import java.util.List;

public class Packet {
    private Integer version;
    private Integer typeId;

    private Long value;

    private final List<Packet> innerPackets;

    private Packet() {
        innerPackets = new ArrayList<>();
    }

    private Packet(int version, int typeId) {
        this();
        this.version = version;
        this.typeId = typeId;
    }

    public static Packet parseBits(TransmissionsBits bits) {
        String versionBits = bits.getNBits(3);
        String typeIdBits = bits.getNBits(3);

        if (versionBits == null || typeIdBits == null)
            return null;

        int version = Integer.parseInt(versionBits, 2);
        int typeId = Integer.parseInt(typeIdBits, 2);
        Packet p = new Packet(version, typeId);

        if (typeId == 4) {
            parseLiteralValue(p, bits);
        } else {
            parseOperator(p, bits);
        }

        return p;
    }

    private static void parseLiteralValue(Packet p, TransmissionsBits bits) {
        StringBuilder valueBits = new StringBuilder();
        String group;
        do {
            group = bits.getNBits(5);
            valueBits.append(group.substring(1));
        } while (group.charAt(0) != '0');
        p.value = Long.parseLong(valueBits.toString(), 2);
    }

    private static void parseOperator(Packet p, TransmissionsBits bits) {
        int lengthTypeId = Integer.parseInt(bits.getNBits(1), 2);
        if (lengthTypeId == 0) {
            parseOperatorUsingTotalLength(p, bits);
        } else {
            parseOperatorUsingNumOfSubpackets(p, bits);
        }
    }

    private static void parseOperatorUsingTotalLength(Packet p, TransmissionsBits bits) {
        int totalLength = Integer.parseInt(bits.getNBits(15), 2);
        int stopAt = bits.getCursorPosition() + totalLength;
        while (bits.getCursorPosition() < stopAt) {
            Packet ip = parseBits(bits);
            p.innerPackets.add(ip);
        }
    }

    private static void parseOperatorUsingNumOfSubpackets(Packet p, TransmissionsBits bits) {
        int numOfSubpackets = Integer.parseInt(bits.getNBits(11), 2);
        for (int i = 0; i < numOfSubpackets; ++i) {
            Packet ip = parseBits(bits);
            p.innerPackets.add(ip);
        }
    }

    public int sumAllVersionNumbers() {
        int sum = version;
        for (Packet p : innerPackets)
            sum += p.sumAllVersionNumbers();
        return sum;
    }

    public long runOperations() {
        return switch (typeId) {
            case 0 -> sumInnerPackets();
            case 1 -> multiplyInnerPackets();
            case 2 -> minInnerPackets();
            case 3 -> maxInnerPackets();
            case 4 -> value;
            case 5 -> greaterThanInnerPackets();
            case 6 -> lessThanInnerPackets();
            case 7 -> equalToInnerPackets();
            default -> 0;
        };
    }

    private long sumInnerPackets() {
        long sum = 0;
        for (Packet p : innerPackets)
            sum += p.runOperations();
        return sum;
    }

    private long multiplyInnerPackets() {
        long prod = 1;
        for (Packet p : innerPackets)
            prod *= p.runOperations();
        return prod;
    }

    private long minInnerPackets() {
        long min = Long.MAX_VALUE;
        for (Packet p : innerPackets) {
            long result = p.runOperations();
            if (result < min)
                min = result;
        }
        return min;
    }

    private long maxInnerPackets() {
        long max = Long.MIN_VALUE;
        for (Packet p : innerPackets) {
            long result = p.runOperations();
            if (result > max)
                max = result;
        }
        return max;
    }

    private long greaterThanInnerPackets() {
        assert innerPackets.size() == 2;
        long v1 = innerPackets.get(0).runOperations();
        long v2 = innerPackets.get(1).runOperations();
        return v1 > v2 ? 1 : 0;
    }

    private long lessThanInnerPackets() {
        assert innerPackets.size() == 2;
        long v1 = innerPackets.get(0).runOperations();
        long v2 = innerPackets.get(1).runOperations();
        return v1 < v2 ? 1 : 0;
    }

    private long equalToInnerPackets() {
        assert innerPackets.size() == 2;
        long v1 = innerPackets.get(0).runOperations();
        long v2 = innerPackets.get(1).runOperations();
        return v1 == v2 ? 1 : 0;
    }

    @Override
    public String toString() {
        return "Packet{" +
                "version=" + version +
                ", typeId=" + typeId +
                ", value=" + value +
                ", innerPackets=" + innerPackets +
                '}';
    }
}
