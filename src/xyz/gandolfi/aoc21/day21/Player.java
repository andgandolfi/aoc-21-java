package xyz.gandolfi.aoc21.day21;

public class Player {
    private final int id;
    private int position;
    private int score;
    private final int boardTrackSpaces;

    public Player(int playerId, int startPosition, int boardTrackSpaces) {
        id = playerId;
        position = startPosition - 1;
        score = 0;
        this.boardTrackSpaces = boardTrackSpaces;
    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public int getPosition() {
        return position + 1;
    }

    public void addToScore(int amount) {
        score += amount;
    }

    public void moveForward(int advancePositions) {
        int amount = advancePositions % boardTrackSpaces;
        position += amount;
        position = position % boardTrackSpaces;
        addToScore(getPosition());
    }

    public Player getCopy() {
        return new Player(id, position + 1, boardTrackSpaces);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", position=" + position +
                ", score=" + score +
                '}';
    }
}
