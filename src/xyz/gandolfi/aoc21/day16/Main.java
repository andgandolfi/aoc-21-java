package xyz.gandolfi.aoc21.day16;

import xyz.gandolfi.aoc21.Utils;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day16.txt");
        assert inputLines != null;

        TransmissionsBits bits = TransmissionsBits.parseHexInput(inputLines.get(0));
        Packet topPacket = Packet.parsePacket(bits);
        assert topPacket != null;

        System.out.print("Day 16a: ");
        System.out.println(topPacket.getVersionsSum());

        System.out.print("Day 16b: ");
        System.out.println(topPacket.getValue());
    }
}
