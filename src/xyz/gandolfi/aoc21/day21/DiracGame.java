package xyz.gandolfi.aoc21.day21;

import java.util.*;

public class DiracGame {
    private int player1Position;
    private int player2Position;
    private int player1Score;
    private int player2Score;
    private boolean player1Turn;

    /*
     * DP problem cache
     * variables:
     *   playerTurn = [1,2]
     *   player1Score = [0...21]
     *   player2Score = [0...21]
     *   player1TrackPosition = [1...10]
     *   player2TrackPosition = [1...10]
     *   = 86200 without dice var
     */
    private static final Map<DiracGame, DiracGameWinnerTimes> cache = new HashMap<>(86200);

    private DiracGame() {}

    public static DiracGame getStartingState(int player1StartingPosition, int player2StartingPosition) {
        DiracGame game = new DiracGame();
        game.player1Position = player1StartingPosition;
        game.player2Position = player2StartingPosition;
        game.player1Score = 0;
        game.player2Score = 0;
        game.player1Turn = true;
        return game;
    }

    public DiracGame evolve(int diceRollNum) {
        DiracGame newGame = new DiracGame();
        if (this.player1Turn) {
            newGame.player1Position = ((this.player1Position - 1 + diceRollNum) % 10) + 1;
            newGame.player1Score = this.player1Score + newGame.player1Position;
            newGame.player2Position = this.player2Position;
            newGame.player2Score = this.player2Score;
            newGame.player1Turn = false;
        } else {
            newGame.player2Position = ((this.player2Position - 1 + diceRollNum) % 10) + 1;
            newGame.player2Score = this.player2Score + newGame.player2Position;
            newGame.player1Position = this.player1Position;
            newGame.player1Score = this.player1Score;
            newGame.player1Turn = true;
        }
        return newGame;
    }

    public static DiracGameWinnerTimes findWinnerInEveryUniverse(DiracGame game) {
        DiracGameWinnerTimes winnerTimes = new DiracGameWinnerTimes();
        if (game.hasPlayer1Won()) {
            winnerTimes.addPlayer1WonTimes(1);
        } else if (game.hasPlayer2Won()) {
            winnerTimes.addPlayer2WonTimes(1);
        } else {
            for (int dice1 = 1; dice1 <= 3; ++dice1)
                for (int dice2 = 1; dice2 <= 3; ++dice2)
                    for (int dice3 = 1; dice3 <= 3; ++dice3) {
                        DiracGame nextGame = game.evolve(dice1 + dice2 + dice3);
                        if (cache.containsKey(nextGame))
                            winnerTimes = winnerTimes.mergeWith(cache.get(nextGame));
                        else
                            winnerTimes = winnerTimes.mergeWith(findWinnerInEveryUniverse(nextGame));
                    }
        }

        cache.put(game, winnerTimes);
        return winnerTimes;
    }

    public boolean hasPlayer1Won() {
        return player1Score >= 21;
    }

    public boolean hasPlayer2Won() {
        return player2Score >= 21;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiracGame diracGame = (DiracGame) o;
        return player1Position == diracGame.player1Position && player2Position == diracGame.player2Position &&
                player1Score == diracGame.player1Score && player2Score == diracGame.player2Score &&
                player1Turn == diracGame.player1Turn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(player1Position, player2Position, player1Score, player2Score, player1Turn);
    }

    @Override
    public String toString() {
        return "DiracGame{" +
                "p1Pos=" + player1Position +
                ", p2Pos=" + player2Position +
                ", p1Score=" + player1Score +
                ", p2Score=" + player2Score +
                ", p1Turn=" + player1Turn +
                '}';
    }
}
