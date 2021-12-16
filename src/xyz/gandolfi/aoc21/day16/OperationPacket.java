package xyz.gandolfi.aoc21.day16;

import java.util.ArrayList;
import java.util.List;

public abstract class OperationPacket extends Packet {
    protected final List<Packet> innerPackets;

    protected OperationPacket(int version) {
        super(version);
        innerPackets = new ArrayList<>();
    }

    @Override
    public void parseBits(TransmissionsBits bits) {
        int lengthTypeId = Integer.parseInt(bits.getNBits(1), 2);
        if (lengthTypeId == 0) {
            parseOperatorUsingTotalLength(bits);
        } else {
            parseOperatorUsingNumOfSubpackets(bits);
        }
    }

    private void parseOperatorUsingTotalLength(TransmissionsBits bits) {
        int totalLength = Integer.parseInt(bits.getNBits(15), 2);
        int stopAt = bits.getCursorPosition() + totalLength;
        while (bits.getCursorPosition() < stopAt) {
            Packet ip = parsePacket(bits);
            innerPackets.add(ip);
        }
    }

    private void parseOperatorUsingNumOfSubpackets(TransmissionsBits bits) {
        int numOfSubpackets = Integer.parseInt(bits.getNBits(11), 2);
        for (int i = 0; i < numOfSubpackets; ++i) {
            Packet ip = parsePacket(bits);
            innerPackets.add(ip);
        }
    }

    @Override
    public int getVersionsSum() {
        int sum = version;
        for (Packet p : innerPackets)
            sum += p.getVersionsSum();
        return sum;
    }
}
