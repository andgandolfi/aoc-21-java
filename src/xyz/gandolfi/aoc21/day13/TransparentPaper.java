package xyz.gandolfi.aoc21.day13;

import java.util.List;

public class TransparentPaper {
    private int[][] dots;

    public TransparentPaper(List<Coordinates> dots) {
        int maxX = 0, maxY = 0;
        for (Coordinates d : dots) {
            if (d.getX() > maxX) maxX = d.getX();
            if (d.getY() > maxY) maxY = d.getY();
        }
        this.dots = new int[maxY + 1][maxX + 1];
        for (Coordinates d : dots)
            this.dots[d.getY()][d.getX()] = 1;
    }

    public void applyFold(List<Fold> folds) {
        for (Fold fold : folds)
            applyFold(fold);
    }

    public void applyFold(Fold fold) {
        if (fold.getType() == 'y')
            applyFoldY(fold.getValue());
        if (fold.getType() == 'x')
            applyFoldX(fold.getValue());
    }

    private void applyFoldY(int line) {
        int[][] newDots = new int[line][dots[0].length];
        for (int y = 0; y < line; ++y) {
            int newY = line - y + line;
            for (int x = 0; x < dots[0].length; ++x) {
                if (newY >= dots.length)
                    newDots[y][x] = dots[y][x];
                else
                    newDots[y][x] = Math.max(dots[y][x], dots[newY][x]);
            }
        }
        dots = newDots;
    }

    private void applyFoldX(int line) {
        int[][] newDots = new int[dots.length][line];
        for (int y = 0; y < dots.length; ++y) {
            for (int x = 0; x < line; ++x) {
                int newX = line - x + line;
                if (newX >= dots[0].length)
                    newDots[y][x] = dots[y][x];
                else
                    newDots[y][x] = Math.max(dots[y][x], dots[y][newX]);
            }
        }
        dots = newDots;
    }

    public int countVisibleDots() {
        int sum = 0;
        for (int y = 0; y < dots.length; ++y)
            for (int x = 0; x < dots[y].length; ++x)
                sum += dots[y][x];
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int y = 0; y < dots.length; ++y) {
            for (int x = 0; x < dots[y].length; ++x)
                sb.append(dots[y][x] == 1 ? "#" : ".");
            sb.append("\n");
        }
        return sb.toString();
    }
}
