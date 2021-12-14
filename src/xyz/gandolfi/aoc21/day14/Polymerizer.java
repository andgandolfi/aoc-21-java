package xyz.gandolfi.aoc21.day14;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Polymerizer {
    private final Map<String, Character> rulesMap;
    private final Map<String, Map<Character, Long>> cache;

    public Polymerizer(List<String> mappingRules) {
        cache = new HashMap<>();
        this.rulesMap = new HashMap<>();
        for (String ruleDesc : mappingRules) {
            String[] parts = ruleDesc.split(" -> ");
            this.rulesMap.put(parts[0], parts[1].charAt(0));
        }
    }

    public Map<Character, Long> getCharCountsAtLevel(String s, int level) {
        Map<Character, Long> total = new HashMap<>();
        for (int i = 0; i < s.length() - 1; ++i) {
            total = mergeCounts(getPairCharCountsAtLevel(s.substring(i, i + 2), level), total);
            if (i > 0) {
                Character c = s.charAt(i);
                total.put(c, total.get(c) - 1);
            }
        }
        return total;
    }

    private Map<Character, Long> getPairCharCountsAtLevel(String p, int level) {
        Map<Character, Long> cached = getFromCache(p, level);
        if (cached != null) return cached;

        char c1 = p.charAt(0);
        char c2 = p.charAt(1);

        Map<Character, Long> counts;
        if (level == 0) {
            counts = new HashMap<>();
            counts.put(c1, 1L);
            counts.put(c2, c2 == c1 ? 2L : 1L);
        } else {
            Character midChar = rulesMap.get(p);
            if (midChar == null)
                counts = getCharCountsAtLevel(p, level - 1);
            else {
                counts = mergeCounts(
                        getCharCountsAtLevel("" + c1 + midChar, level - 1),
                        getCharCountsAtLevel("" + midChar + c2, level - 1)
                );
                counts.put(midChar, counts.get(midChar) - 1);
            }
        }

        saveInCache(p, level, counts);
        return counts;
    }

    private Map<Character, Long> mergeCounts(Map<Character, Long> counts1, Map<Character, Long> counts2) {
        Map<Character, Long> result = new HashMap<>(counts1);
        for (Map.Entry<Character, Long> e : counts2.entrySet())
            result.put(e.getKey(), result.getOrDefault(e.getKey(), 0L) + e.getValue());
        return result;
    }

    private Map<Character, Long> getFromCache(String s, int level) {
        return cache.get(s + level);
    }

    private void saveInCache(String s, int level, Map<Character, Long> counts) {
        cache.put(s + level, counts);
    }

    public static Map.Entry<Character, Long> getLeastCommonElement(Map<Character, Long> counts) {
        return counts.entrySet().stream()
                .max((e1, e2) -> e1.getValue() > e2.getValue() ? -1 : 1)
                .get();
    }

    public static Map.Entry<Character, Long> getMostCommonElement(Map<Character, Long> counts) {
        return counts.entrySet().stream()
                .max((e1, e2) -> e1.getValue() < e2.getValue() ? -1 : 1)
                .get();
    }
}
