package xyz.gandolfi.aoc21.day21;

public class DiracGameWinnerTimes {
    private long player1WonTimes;
    private long player2WonTimes;

    public DiracGameWinnerTimes() {
        player1WonTimes = 0;
        player2WonTimes = 0;
    }

    public DiracGameWinnerTimes(long player1WonTimes, long player2WonTimes) {
        this.player1WonTimes = player1WonTimes;
        this.player2WonTimes = player2WonTimes;
    }

    public DiracGameWinnerTimes mergeWith(DiracGameWinnerTimes otherCounter) {
        return new DiracGameWinnerTimes(
                player1WonTimes + otherCounter.player1WonTimes,
                player2WonTimes + otherCounter.player2WonTimes);
    }

    public long getPlayer1WonTimes() {
        return player1WonTimes;
    }

    public long getPlayer2WonTimes() {
        return player2WonTimes;
    }

    public long getWinnerScore() {
        return Math.max(player1WonTimes, player2WonTimes);
    }

    public void addPlayer1WonTimes(long qty) {
        player1WonTimes += qty;
    }

    public void addPlayer2WonTimes(long qty) {
        player2WonTimes += qty;
    }

    @Override
    public String toString() {
        return "DiracGameWinnerTimes{" +
                "player1WonTimes=" + player1WonTimes +
                ", player2WonTimes=" + player2WonTimes +
                '}';
    }
}
