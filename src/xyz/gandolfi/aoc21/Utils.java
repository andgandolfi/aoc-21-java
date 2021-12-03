package xyz.gandolfi.aoc21;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Utils {
    public static List<String> getInputFileLines(String resourcesInputFileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        try (InputStream is = classLoader.getResourceAsStream(resourcesInputFileName)) {
            if (is == null) return null;
            try (InputStreamReader isr = new InputStreamReader(is);
                 BufferedReader reader = new BufferedReader(isr)) {
                return reader.lines().toList();
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
