package xyz.gandolfi.aoc21.day08;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Entry {
    private final List<Pattern> uniqueSignalPatterns;
    private final List<Pattern> outputPatterns;

    private final Map<String, String> correctWiring;

    public Entry(String inputLine) {
        String[] lineParts = inputLine.split(" \\| ");
        uniqueSignalPatterns = Arrays.stream(lineParts[0].split(" "))
                .map(Pattern::new)
                .collect(Collectors.toList());
        outputPatterns = Arrays.stream(lineParts[1].split(" "))
                .map(Pattern::new)
                .collect(Collectors.toList());
        correctWiring = findCorrectWiring();
    }

    private Map<String, String> findCorrectWiring() {
        /*
                 aaaa
                b    c
                b    c
                 dddd
                e    f
                e    f
                 gggg

        segments:
        0 -> 6 segments
        1 -> 2 segments
        2 -> 5 segments
        3 -> 5 segments
        4 -> 4 segments
        5 -> 5 segments
        6 -> 6 segments
        7 -> 3 segments
        8 -> 7 segments
        9 -> 6 segments

        analysis:
        2 segments -> 1
        3 segments -> 7 (reveals aaa)
        4 segments -> 4
        7 segments -> 8 (useless)
        --> I know +/- where ccc and fff are
        --> I know where aaa is
        --> I know +/- where bbb and ddd are
        --> Left, 3 with 6 segments, 3 with 5 segments
        5 segments -> 2, 3, 5 (reveal ggg, (bbb, ddd))
        6 segments -> 0, 6, 9 (reveal ccc, fff)
         */

        List<Pattern> segments2 = getPatternWithNSegments(2);
        List<Pattern> segments3 = getPatternWithNSegments(3);
        List<Pattern> segments4 = getPatternWithNSegments(4);
        List<Pattern> segments5 = getPatternWithNSegments(5);
        List<Pattern> segments6 = getPatternWithNSegments(6);
        List<Pattern> segments7 = getPatternWithNSegments(7);

        assert segments2.size() == 1;
        assert segments3.size() == 1;
        assert segments4.size() == 1;
        assert segments5.size() == 3;
        assert segments6.size() == 3;
        assert segments7.size() == 1;

        Pattern num1 = segments2.get(0);
        Pattern num7 = segments3.get(0);
        Pattern num4 = segments4.get(0);
        Pattern num8 = segments7.get(0);

        Pattern segmentA = num7.removeSegments(num1);
        assert segmentA.getSegments().size() == 1;

        Pattern segmentWithoutBE = segments5.get(0)
                .intersectSegments(segments5.get(1));

        Pattern segmentG = segmentWithoutBE
                .removeSegments(num7)
                .removeSegments(num4);
        assert segmentG.getSegments().size() == 1;

        Pattern segmentD = segmentWithoutBE
                .removeSegments(num7)
                .removeSegments(segmentG);
        assert segmentD.getSegments().size() == 1;

        Pattern segmentB = num4
                .removeSegments(num7)
                .removeSegments(segmentD);
        assert segmentB.getSegments().size() == 1;

        Pattern segmentF = segments6.get(0)
                .intersectSegments(segments6.get(1))
                .intersectSegments(segments6.get(2))
                .intersectSegments(num1);
        assert segmentF.getSegments().size() == 1;

        Pattern segmentC = num1
                .removeSegments(segmentF);
        assert segmentC.getSegments().size() == 1;

        Pattern segmentE = num8
                .removeSegments(num7)
                .removeSegments(num4)
                .removeSegments(segmentG);
        assert segmentE.getSegments().size() == 1;

        Map<String, String> mapping = new HashMap<>();
        mapping.put(segmentA.getSortedSegments(), "a");
        mapping.put(segmentB.getSortedSegments(), "b");
        mapping.put(segmentC.getSortedSegments(), "c");
        mapping.put(segmentD.getSortedSegments(), "d");
        mapping.put(segmentE.getSortedSegments(), "e");
        mapping.put(segmentF.getSortedSegments(), "f");
        mapping.put(segmentG.getSortedSegments(), "g");
        return mapping;
    }

    private List<Pattern> getPatternWithNSegments(int numSegments) {
        return uniqueSignalPatterns.stream()
                .filter(p -> p.getSegments().size() == numSegments)
                .collect(Collectors.toList());
    }

    public List<Pattern> getUniqueSignalPatterns() {
        return uniqueSignalPatterns;
    }

    public List<Pattern> getOutputPatterns() {
        return outputPatterns;
    }

    public int getOutputValue() {
        int result = 0;

        for (Pattern p : outputPatterns) {
            Pattern remappedPattern = p.remap(correctWiring);
            int val = remappedPattern.getValue();
            result = (result * 10) + val;
        }

        return result;
    }
}
