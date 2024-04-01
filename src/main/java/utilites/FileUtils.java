package utilites;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * Utility class for reading files and streams.
 */
public class FileUtils {

    /**
     * Reads a file from the classpath.
     *
     * @param pathOnClasspath the path of the file on the classpath.
     * @return the content of the file.
     */
    public static String readFileFromClasspath(String pathOnClasspath) {
        try (InputStream is = Thread.currentThread()
                .getContextClassLoader()
                .getResourceAsStream(pathOnClasspath)) {

            noLoadFile(pathOnClasspath, is);

            return readStream(is);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void noLoadFile(String pathOnClasspath, InputStream is) {
        if (is == null) {
            throw new IllegalStateException("Can not load file: " + pathOnClasspath);
        }
    }

    /**
     * Reads the content of an InputStream.
     *
     * @param is the InputStream to read.
     * @return the content of the InputStream.
     */
    public static String readStream(InputStream is) {
        try (Scanner scanner = new Scanner(is, StandardCharsets.UTF_8)) {
            return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
        }
    }
}
