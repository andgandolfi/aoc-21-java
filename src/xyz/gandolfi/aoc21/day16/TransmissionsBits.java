package xyz.gandolfi.aoc21.day16;

public class TransmissionsBits {
    private final String bits;
    private int cursor = 0;

    public TransmissionsBits(String bits) {
        this.bits = bits;
    }

    public TransmissionsBits(String bits, int cursorPosition) {
        this.bits = bits;
        this.cursor = cursorPosition;
    }

    public static TransmissionsBits parseHexInput(String hexInputString) {
        StringBuilder sb = new StringBuilder(hexInputString.length() * 4);
        for (char c : hexInputString.toCharArray()) {
            int intVal = Integer.parseInt(String.valueOf(c), 16);
            String binStr = Integer.toBinaryString(intVal);
            sb.append(String.format("%4s", binStr).replace(' ', '0'));
        }
        return new TransmissionsBits(sb.toString());
    }

    public String getNBits(int n) {
        if (cursor >= bits.length()) return null;
        if (getBitsLeftToRead() < n) return null;
        String part = bits.substring(cursor, cursor + n);
        cursor += n;
        return part;
    }

    public int getBitsLeftToRead() {
        return bits.length() - cursor;
    }

    public int getCursorPosition() {
        return cursor;
    }
}
