package xyz.gandolfi.aoc21.day06;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LanternfishRunner {
    private final List<Integer> initialAges;
    private final Map<String, Long> cache;

    public LanternfishRunner(List<Integer> inputAges) {
        initialAges = inputAges;
        cache = new HashMap<>();
    }

    public long countAfterDays(int days) {
        long result = 0;
        for (int age : initialAges)
            result += evolve(age, days, 1);
        return result;
    }

    private long evolve(int currAge, int daysToAdvance, int addsUp) {
        if (daysToAdvance <= currAge)
            return addsUp;
        String key = currAge + "-" + daysToAdvance + "-" + addsUp;
        if (cache.containsKey(key))
            return cache.get(key);
        long result = addsUp
                + evolve(6, daysToAdvance - currAge - 1, 0)
                + evolve(8, daysToAdvance - currAge - 1, 1);
        cache.put(key, result);
        return result;
    }
}
