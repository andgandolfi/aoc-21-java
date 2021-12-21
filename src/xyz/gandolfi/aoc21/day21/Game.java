package xyz.gandolfi.aoc21.day21;

import java.util.Comparator;
import java.util.List;

public class Game {
    private final List<Player> players;
    private final Dice dice;

    public Game(List<Player> players, Dice dice) {
        this.players = players.stream().map(Player::getCopy).sorted(Comparator.comparingInt(Player::getId)).toList();
        this.dice = dice;
    }

    public int playUntilOnePlayerReaches(int targetScore) {
        int idx = 0;
        while (true) {
            Player player = players.get(idx);
            int dice1 = dice.roll();
            int dice2 = dice.roll();
            int dice3 = dice.roll();
            int moveForwardAmount = dice1 + dice2 + dice3;
            player.moveForward(moveForwardAmount);
            if (player.getScore() >= 1000)
                return player.getId();
            idx = (idx + 1) % players.size();
        }
    }

    public List<Player> getPlayers() {
        return players;
    }
}
