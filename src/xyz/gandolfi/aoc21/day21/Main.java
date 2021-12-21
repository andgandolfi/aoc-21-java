package xyz.gandolfi.aoc21.day21;

import xyz.gandolfi.aoc21.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        List<String> inputLines = Utils.getInputFileLines("day21.txt");
        assert inputLines != null;

        List<Player> players = new ArrayList<>();
        Pattern pattern = Pattern.compile("^Player (\\d+) starting position: (\\d+)$");
        for (String line : inputLines) {
            Matcher matcher = pattern.matcher(line);
            boolean matches = matcher.matches();
            assert matches;
            players.add(new Player(Integer.parseInt(matcher.group(1)), Integer.parseInt(matcher.group(2)), 10));
        }

        System.out.print("Day 21a: ");
        Dice dice100 = new Dice(100);
        Game game = new Game(players, dice100);
        int winnerId = game.playUntilOnePlayerReaches(1000);
        Player loser = game.getPlayers().stream().filter(p -> p.getId() != winnerId).findFirst().get();
        System.out.println(loser.getScore() * dice100.getRollCount());

        System.out.print("Day 21b: ");
        DiracGame diracGame = DiracGame.getStartingState(players.get(0).getPosition(), players.get(1).getPosition());
        System.out.println(DiracGame.findWinnerInEveryUniverse(diracGame).getWinnerScore());
    }
}
