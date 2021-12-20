package xyz.gandolfi.aoc21.day20;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Image {
    private final String imgEnhancementAlgo;
    private final Set<Pixel> pixels;
    private final Pixel topLeftCorner;
    private final Pixel bottomRightCorner;
    private boolean isSurroundingSpaceEmpty;

    public Image(String imgEnhancementAlgo, Set<Pixel> pixels) {
        this(imgEnhancementAlgo, pixels, true);
    }

    public Image(String imgEnhancementAlgo, Set<Pixel> pixels, boolean isSurroundingSpaceEmpty) {
        this.imgEnhancementAlgo = imgEnhancementAlgo;
        this.isSurroundingSpaceEmpty = isSurroundingSpaceEmpty;
        this.pixels = pixels;
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (Pixel p : pixels) {
            minX = Math.min(minX, p.getX());
            maxX = Math.max(maxX, p.getX());
            minY = Math.min(minY, p.getY());
            maxY = Math.max(maxY, p.getY());
        }
        this.topLeftCorner = new Pixel(minX, minY);
        this.bottomRightCorner = new Pixel(maxX, maxY);
    }

    public Image(String imgEnhancementAlgo, List<String> inputImageLines) {
        this(imgEnhancementAlgo, inputImageLines, true);
    }

    public Image(String imgEnhancementAlgo, List<String> inputImageLines, boolean isSurroundingSpaceEmpty) {
        this.imgEnhancementAlgo = imgEnhancementAlgo;
        this.isSurroundingSpaceEmpty = isSurroundingSpaceEmpty;
        this.pixels = new HashSet<>();
        int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
        for (int y = 0; y < inputImageLines.size(); ++y) {
            String line = inputImageLines.get(y);
            for (int x = 0; x < line.length(); ++x)
                if (line.charAt(x) == '#') {
                    this.pixels.add(new Pixel(x, y));
                    minX = Math.min(minX, x);
                    maxX = Math.max(maxX, x);
                    minY = Math.min(minY, y);
                    maxY = Math.max(maxY, y);
                }
        }
        this.topLeftCorner = new Pixel(minX, minY);
        this.bottomRightCorner = new Pixel(maxX, maxY);
    }

    public Image enhanceNTimes(int n) {
        Image img = this;
        for (int i = 0; i < n; ++i)
            img = img.enhanceOnce();
        return img;
    }

    public Image enhanceOnce() {
        Set<Pixel> newPixels = new HashSet<>();
        for (int y = topLeftCorner.getY() - 2; y <= bottomRightCorner.getY() + 2; ++y)
            for (int x = topLeftCorner.getX() - 2; x <= bottomRightCorner.getX() + 2; ++x)
                if (isEnhanced(x, y))
                    newPixels.add(new Pixel(x, y));
        return new Image(imgEnhancementAlgo, newPixels, !newPixels.contains(new Pixel(topLeftCorner.getX() - 2, topLeftCorner.getY() - 2)));
    }

    private boolean isEnhanced(int px, int py) {
        StringBuilder binaryNum = new StringBuilder();
        for (int y = py - 1; y <= py + 1; ++y)
            for (int x = px - 1; x <= px + 1; ++x) {
                if (x >= topLeftCorner.getX() && x <= bottomRightCorner.getX() &&
                     y >= topLeftCorner.getY() && y <= bottomRightCorner.getY()) {
                    binaryNum.append(pixels.contains(new Pixel(x, y)) ? "1" : "0");
                } else {
                    binaryNum.append(isSurroundingSpaceEmpty ? "0" : "1");
                }
            }
        int pos = Integer.parseInt(binaryNum.toString(), 2);
        return imgEnhancementAlgo.charAt(pos) == '#';
    }

    public Set<Pixel> getPixels() {
        return pixels;
    }

    @Override
    public String toString() {
        StringBuilder img = new StringBuilder();
        for (int y = topLeftCorner.getY(); y <= bottomRightCorner.getY(); ++y) {
            for (int x = topLeftCorner.getX(); x <= bottomRightCorner.getX(); ++x)
                img.append(pixels.contains(new Pixel(x, y)) ? "#" : ".");
            img.append('\n');
        }
        img.append("Space around: ");
        img.append(isSurroundingSpaceEmpty ? '.' : '#');
        img.append("\n");
        return img.toString();
    }
}
