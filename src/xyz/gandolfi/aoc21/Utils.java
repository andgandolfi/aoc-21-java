package xyz.gandolfi.aoc21;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Stream;

public class Utils {

    // TODO: Improve this class/method so that stream returned is automatically closed at the end (ideally in a try block)
    public static Stream<String> getInputFileLinesStream(String resourcesInputFileName) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream is = classLoader.getResourceAsStream(resourcesInputFileName);
        if (is == null) return null;
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader reader = new BufferedReader(isr);
        return reader.lines();
    }
}
