package xyz.gandolfi.aoc21.day25;

import java.util.Arrays;
import java.util.List;

public class SeaMap {
    char[][] map;

    private SeaMap() {}

    private SeaMap(char[][] map) {
        this.map = map;
    }

    public SeaMap(List<String> inputLines) {
        map = new char[inputLines.size()][inputLines.get(0).length()];
        for (int i = 0; i < inputLines.size(); ++i)
            for (int j = 0; j < inputLines.get(i).length(); ++j) {
                char c = inputLines.get(i).charAt(j);
                map[i][j] = c != '.' ? c : 0;
            }
    }

    public SeaMap evolve() {
        char[][] newMap = new char[map.length][map[0].length];

        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                char c = map[i][j];
                int jn = (j + 1) % map[i].length;
                char cn = map[i][jn];
                if (c == '>' && cn == 0)
                    newMap[i][jn] = '>';
                else if (c != 0)
                    newMap[i][j] = map[i][j];
            }
        }

        char[][] newMap2 = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j) {
                char c = newMap[i][j];
                int in = (i + 1) % map.length;
                char cn = newMap[in][j];
                if (c == 'v' && cn == 0)
                    newMap2[in][j] = 'v';
                else if (c != 0)
                    newMap2[i][j] = newMap[i][j];
            }
        }

        return new SeaMap(newMap2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SeaMap seaMap = (SeaMap) o;
        return Arrays.deepEquals(map, seaMap.map);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(map);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder((map.length + 1) * map[0].length);
        for (int i = 0; i < map.length; ++i) {
            for (int j = 0; j < map[i].length; ++j)
                sb.append(map[i][j] == 0 ? '.' : map[i][j]);
            sb.append('\n');
        }
        return sb.toString();
    }
}
