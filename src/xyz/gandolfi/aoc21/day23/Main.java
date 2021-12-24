package xyz.gandolfi.aoc21.day23;

import xyz.gandolfi.aoc21.Utils;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day23.txt");
        assert inputLines != null;

        List<List<Character>> amphipodsConfig = inputLines.stream()
            .skip(2)
            .takeWhile(s ->! s.contains("#########"))
            .map(s -> s.replace('#', ' ').trim())
            .map(s -> Arrays.stream(s.split(" ")).map(s1 -> s1.charAt(0)).toList())
            .toList();

        System.out.print("Day 23a: ");
        AmphipodsState stateA = new AmphipodsState(amphipodsConfig);
        System.out.println(AmphipodsState.run(stateA));

        System.out.print("Day 23b: ");
        System.out.println();
    }
}
