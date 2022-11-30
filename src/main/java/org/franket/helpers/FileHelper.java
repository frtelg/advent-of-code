package org.franket.helpers;

import java.io.*;
import java.util.stream.Stream;

public class FileHelper {
    public static Stream<String> readLines(String fileLocation) {
        var is = FileHelper.class.getClassLoader().getResourceAsStream(fileLocation);

        if (is == null) throw new IllegalStateException("File is null");

        var reader = new InputStreamReader(is);
        var bufferedReader = new BufferedReader(reader);

        return bufferedReader.lines();
    }
}
